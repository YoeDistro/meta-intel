SUMMARY  = "Intel(R) Implicit SPMD Program Compiler"
DESCRIPTION = "ispc is a compiler for a variant of the C programming language, \
with extensions for single program, multiple data programming."
HOMEPAGE = "https://github.com/ispc/ispc"

LICENSE  = "BSD-3-Clause & Apache-2.0-with-LLVM-exception"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=da5ecffdd210b3cf776b32b41c182e87 \
                    file://third-party-programs.txt;md5=3cd6f8a7c3bd9d2bb898fcb27c75221a"

inherit cmake python3native

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ispc/ispc.git;protocol=https;branch=main \
           file://0001-CMakeLists.txt-link-with-libclang-cpp-library-instea.patch \
           file://0002-cmake-don-t-build-for-32-bit-targets.patch \
           file://0001-Fix-QA-Issues.patch \
           file://6a1b2ffae0cc12467838bc671e3b089924de90a6.patch \
           file://ec35a6f8e60ba77e59a6f2bfec27011e0ab34dda.patch \
           "
SRCREV = "f7ec3aa173c816377c215d83196b5c7c3a88db1c"

COMPATIBLE_HOST = '(x86_64).*-linux'

DEPENDS += " clang-native bison-native flex-native"
RDEPENDS:${PN} += " clang-libllvm clang clang-libclang-cpp"

YFLAGS='-d -t -v -y --file-prefix-map=${WORKDIR}=/usr/src/debug/${PN}/${EXTENDPE}${PV}-${PR}'

do_configure:prepend() {
        sed -i -e 's#\${BISON_EXECUTABLE}.*#\${BISON_EXECUTABLE} ${YFLAGS} #g' ${S}/CMakeLists.txt
        sed -i -e 's#\${FLEX_EXECUTABLE}.*#\${FLEX_EXECUTABLE} \-L #g' ${S}/CMakeLists.txt
}

EXTRA_OECMAKE += " \
                  -DISPC_INCLUDE_TESTS=OFF  \
                  -DISPC_INCLUDE_EXAMPLES=OFF  \
                  -DISPC_NO_DUMPS=ON  \
                  -DARM_ENABLED=OFF  \
                  -DISPC_CROSS=ON  \
                  -DISPC_ANDROID_TARGET=OFF  \
                  -DISPC_FREEBSD_TARGET=OFF  \
                  -DISPC_WINDOWS_TARGET=OFF  \
                  -DISPC_IOS_TARGET=OFF  \
                  -DISPC_PS4_TARGET=OFF  \
                  -DSYSROOT_DIR=${STAGING_DIR_NATIVE}  \
                  "

BBCLASSEXTEND = "native nativesdk"
