SUMMARY  = "Intel(R) Implicit SPMD Program Compiler"
DESCRIPTION = "ispc is a compiler for a variant of the C programming language, \
with extensions for single program, multiple data programming."
HOMEPAGE = "https://github.com/ispc/ispc"

LICENSE  = "BSD-3-Clause & Apache-2.0-with-LLVM-exception"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=da5ecffdd210b3cf776b32b41c182e87 \
                    file://third-party-programs.txt;md5=853c6beec84e8f9b2b56d7ad9ad7a424"

inherit cmake python3native ptest

# Main ispc source
SRC_URI = "git://github.com/ispc/ispc.git;protocol=https;nobranch=1;name=ispc;destsuffix=git/ispc \
           file://0001-superbuild-forward-ISPCRT_BUILD_TASK_MODEL.patch \
           file://0003-GenerateBuiltins-Add-sysroot-for-builtin-compilation.patch;patchdir=${UNPACKDIR}/git/ispc \
           file://0004-Fix-QA-Issues.patch;patchdir=${UNPACKDIR}/git/ispc \
           file://run-ptest \
           "

# Bundled LLVM 20.1 and dependencies for superbuild
SRC_URI += "git://github.com/llvm/llvm-project.git;protocol=https;nobranch=1;name=llvm;destsuffix=git/llvm-project \
            git://github.com/intel/vc-intrinsics.git;protocol=https;nobranch=1;name=vc;destsuffix=git/vc-intrinsics \
            git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;nobranch=1;name=spirv;destsuffix=git/SPIRV-LLVM-Translator \
            file://0002-llvm-update-CMake-policy-CMP0116-to-NEW.patch;patchdir=${UNPACKDIR}/git/llvm-project \
            "

SRCREV_ispc = "dba3a5196219f2419da2dabbd863870c46cddd80"
SRCREV_llvm = "87f0227cb60147a26a1eeb4fb06e3b505e9c7261"
SRCREV_vc = "b16218b8a00c5c1d4db32085dfab4d5eb9a03ad7"
SRCREV_spirv = "d1c69c3365dffed67124eb1692cb941cbae5bb2e"

SRCREV_FORMAT = "ispc_llvm_vc_spirv"

S = "${UNPACKDIR}/git/ispc"

COMPATIBLE_HOST = '(x86_64).*-linux'

# Skip QA check for file-rdeps since superbuild stage2 links against build host libraries
# This is acceptable for a build tool that runs on the build host
# Skip buildpaths since LLVM 20.1 stage2 build embeds debug paths in binaries
INSANE_SKIP:${PN} += "file-rdeps buildpaths"
INSANE_SKIP:${PN}-dbg += "buildpaths"

# Only need native tools for building, LLVM 20.1 is bundled
# ncurses needed for libedit (LLVM dependency)
DEPENDS = "bison-native flex-native ninja-native ncurses"
RDEPENDS:${PN}-ptest += "bash python3-multiprocessing"

PACKAGECONFIG ??= ""
# Default to Threads tasking model; override with TBB if enabled
PACKAGECONFIG[tbb] = "-DISPCRT_BUILD_TASK_MODEL=TBB, , tbb"

do_compile:prepend() {
    # Clean OE-specific flags from toolchain files for bundled LLVM build
    for toolchain in stage1-toolchain.cmake stage2-toolchain.cmake; do
        [ -f ${B}/${toolchain} ] || continue
        sed -i -e 's|set(CMAKE_C\(XX\)\?_FLAGS.*|set(CMAKE_C\1_FLAGS "")|g' \
               -e 's|set(CMAKE_\(EXE\|SHARED\|MODULE\)_LINKER_FLAGS.*|set(CMAKE_\1_LINKER_FLAGS "")|g' \
               ${B}/${toolchain}
    done
    
    # Use native compilers for stage1
    [ -f ${B}/stage1-toolchain.cmake ] && sed -i \
        -e "s|set(CMAKE_C_COMPILER   cc)|set(CMAKE_C_COMPILER   ${BUILD_CC})|" \
        -e "s|set(CMAKE_CXX_COMPILER c++)|set(CMAKE_CXX_COMPILER ${BUILD_CXX})|" \
        ${B}/stage1-toolchain.cmake && \
        echo "set(CMAKE_BUILD_WITH_INSTALL_RPATH TRUE)" >> ${B}/stage1-toolchain.cmake
}



# Use superbuild with bundled LLVM 20.1
OECMAKE_SOURCEPATH = "${S}/superbuild"

EXTRA_OECMAKE += " \
                  --preset os \
                  -G Ninja \
                  -DLLVM_VERSION=20.1 \
                  -DLLVM_DISABLE_ASSERTIONS=ON \
                  -DLLVM_URL=${UNPACKDIR}/git/llvm-project \
                  -DVC_INTRINSICS_URL=${UNPACKDIR}/git/vc-intrinsics \
                  -DVC_INTRINSICS_SHA=${SRCREV_vc} \
                  -DSPIRV_TRANSLATOR_URL=${UNPACKDIR}/git/SPIRV-LLVM-Translator \
                  -DSPIRV_TRANSLATOR_SHA=${SRCREV_spirv} \
                  -DSPIRV_TRANSLATOR_BRANCH=llvm_release_201 \
                  -DISPC_CORPUS_URL=null \
                  -DISPC_CROSS=ON \
                  -DISPCRT_BUILD_TASK_MODEL=Threads \
                  -DISPC_INCLUDE_TESTS=OFF \
                  -DISPC_INCLUDE_EXAMPLES=OFF \
                  -DISPC_INCLUDE_RT=OFF \
                  -DISPC_INCLUDE_DPCPP=OFF \
                  -DISPC_PREPARE_PACKAGE=OFF \
                  -DARM_ENABLED=OFF \
                  -DISPC_ANDROID_TARGET=OFF \
                  -DISPC_FREEBSD_TARGET=OFF \
                  -DISPC_WINDOWS_TARGET=OFF \
                  -DISPC_IOS_TARGET=OFF \
                  -DISPC_PS_TARGET=OFF \
                  -DXE_DEPS=OFF \
                  "

do_compile() {
    # Unset OE-specific flags for stage1 LLVM build
    export CFLAGS_FOR_BUILD=""
    export CXXFLAGS_FOR_BUILD=""
    export LDFLAGS_FOR_BUILD=""
    
    bbnote "Building ispc with bundled LLVM 20.1"
    cmake --build "${B}" --target all -- ${EXTRA_OECMAKEBUILD}
}

# Superbuild installs ispc to build/ispc-stage2 instead of ${D}
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/ispc-stage2/bin/ispc ${D}${bindir}/
    install -m 0755 ${B}/ispc-stage2/bin/check_isa ${D}${bindir}/
}

do_install:append:class-target() {
    # Install tests for gio validation (ispc-test package)
    install -d ${D}${libdir}/ispc/gio
    install -m 0755 ${S}/scripts/run_tests.py ${D}${libdir}/ispc/gio/
    install -m 0644 ${S}/scripts/common.py ${D}${libdir}/ispc/gio/
    cp -r ${S}/tests ${D}${libdir}/ispc/gio/
    cp ${S}/test_static.isph ${D}${libdir}/ispc/gio/ || true
    cp ${S}/fail_db.txt ${D}${libdir}/ispc/gio/ || true
    cp ${S}/test_static.cpp ${D}${libdir}/ispc/gio/ || true
}

do_install_ptest:class-target() {
    install -d ${D}${PTEST_PATH}
    install -m 0755 ${S}/scripts/run_tests.py ${D}${PTEST_PATH}/
    install -m 0644 ${S}/scripts/common.py ${D}${PTEST_PATH}/
    cp -r ${S}/tests ${D}${PTEST_PATH}/
    cp ${S}/test_static.isph ${D}${PTEST_PATH}/ || true
    cp ${S}/fail_db.txt ${D}${PTEST_PATH}/ || true
    cp ${S}/test_static.cpp ${D}${PTEST_PATH}/ || true
}

PACKAGES:prepend:class-target = "${PN}-test "
FILES:${PN}-test:class-target = "${libdir}/ispc/gio/*"
RDEPENDS:${PN}-test:class-target += "bash python3-multiprocessing ${PN}"

pkg_postinst:${PN}() {
    #!/bin/sh
    # Create /lib64 symlink if it doesn't exist (needed for ispc binary built with /lib64 interpreter path)
    if [ -z "$D" ]; then
        # Runtime installation
        if [ ! -e /lib64/ld-linux-x86-64.so.2 ] && [ -e /lib/ld-linux-x86-64.so.2 ]; then
            mkdir -p /lib64
            ln -sf /lib/ld-linux-x86-64.so.2 /lib64/ld-linux-x86-64.so.2
        fi
    else
        # Image creation time
        if [ ! -e $D/lib64/ld-linux-x86-64.so.2 ] && [ -e $D/lib/ld-linux-x86-64.so.2 ]; then
            mkdir -p $D/lib64
            ln -sf /lib/ld-linux-x86-64.so.2 $D/lib64/ld-linux-x86-64.so.2
        fi
    fi
}

# ISPC compiler - also available as native/nativesdk for build-time use
BBCLASSEXTEND = "native nativesdk"
