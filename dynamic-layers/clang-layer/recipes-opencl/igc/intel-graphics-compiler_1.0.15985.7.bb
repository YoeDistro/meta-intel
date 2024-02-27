SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://LICENSE.md;md5=488d74376edf2765f6e78d271543dde3 \
                    file://NOTICES.txt;md5=7f4fbc3eb2c34807465e63b1ec3c9d1a"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https;name=igc;branch=releases/igc-1.0.15985 \
           git://github.com/intel/vc-intrinsics.git;protocol=https;destsuffix=git/vc-intrinsics;name=vc;nobranch=1 \
           git://github.com/KhronosGroup/SPIRV-Tools.git;protocol=https;destsuffix=git/SPIRV-Tools;name=spirv-tools;branch=main \
           git://github.com/KhronosGroup/SPIRV-Headers.git;protocol=https;destsuffix=git/SPIRV-Headers;name=spirv-headers;branch=main \
           file://0003-Improve-Reproducibility-for-src-package.patch \
           file://0001-BiF-CMakeLists.txt-remove-opt-from-DEPENDS.patch \
           file://0001-external-SPIRV-Tools-change-path-to-tools-and-header.patch \
           "

SRC_URI:append:class-native = " file://0001-fix-tblgen.patch"

SRCREV_igc = "6cc111d262e1c3abcf4bc6b6d8a589ebf821a5c0"
SRCREV_vc = "da892e1982b6c25b9a133f85b4ac97142d8a3def"
SRCREV_spirv-tools = "f0cc85efdbbe3a46eae90e0f915dc1509836d0fc"
SRCREV_spirv-headers = "cca08c63cefa129d082abca0302adcb81610b465"

SRCREV_FORMAT = "igc_vc_spirv-tools_spirv-headers"

# Used to replace with relative path in reproducibility patch
export B

S = "${WORKDIR}/git"

inherit cmake pkgconfig qemu python3native

CXXFLAGS:append = " -Wno-error=nonnull"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += " flex-native bison-native clang clang-cross-x86_64 opencl-clang qemu-native python3-mako-native"

RDEPENDS:${PN} += "opencl-clang"

PACKAGECONFIG ??= "vc"
PACKAGECONFIG[vc] = "-DIGC_BUILD__VC_ENABLED=ON -DIGC_OPTION__LINK_KHRONOS_SPIRV_TRANSLATOR=ON -DIGC_OPTION__SPIRV_TRANSLATOR_MODE=Prebuilds,-DIGC_BUILD__VC_ENABLED=OFF,"

EXTRA_OECMAKE = " \
                  -DIGC_OPTION__LLVM_PREFERRED_VERSION=${LLVMVERSION} \
                  -DVC_INTRINSICS_SRC="${S}/vc-intrinsics" \
                  -DIGC_OPTION__LLVM_MODE=Prebuilds \
                  -DLLVM_TABLEGEN=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
                  -DLLVM_LINK_EXE=${STAGING_BINDIR_NATIVE}/llvm-link \
                  -DCLANG_EXE=${STAGING_BINDIR_NATIVE}/clang \
                  -DCMAKE_CROSSCOMPILING_EMULATOR=${WORKDIR}/qemuwrapper \
                  "

do_configure:prepend:class-target () {
    # Write out a qemu wrapper that will be used by cmake.
    qemu_binary="${@qemu_wrapper_cmdline(d, d.getVar('STAGING_DIR_HOST'), [d.expand('${STAGING_DIR_HOST}${libdir}'),d.expand('${STAGING_DIR_HOST}${base_libdir}')])}"
    cat > ${WORKDIR}/qemuwrapper << EOF
#!/bin/sh
$qemu_binary "\$@"
EOF
    chmod +x ${WORKDIR}/qemuwrapper
}

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES:${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "

# libigc.so contains buildpaths
INSANE_SKIP:${PN} += "buildpaths"
