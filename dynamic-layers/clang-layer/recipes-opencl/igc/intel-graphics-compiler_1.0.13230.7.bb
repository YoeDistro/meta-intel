SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://LICENSE.md;md5=488d74376edf2765f6e78d271543dde3 \
                    file://NOTICES.txt;md5=7f4fbc3eb2c34807465e63b1ec3c9d1a"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https;name=igc;branch=releases/igc-1.0.13230 \
           git://github.com/intel/vc-intrinsics.git;protocol=https;destsuffix=git/vc-intrinsics;name=vc;nobranch=1 \
           git://github.com/KhronosGroup/SPIRV-Tools.git;protocol=https;destsuffix=git/SPIRV-Tools;name=spirv-tools;branch=main \
           git://github.com/KhronosGroup/SPIRV-Headers.git;protocol=https;destsuffix=git/SPIRV-Headers;name=spirv-headers;branch=main \
           file://0003-Improve-Reproducibility-for-src-package.patch \
           file://0001-BiF-CMakeLists.txt-remove-opt-from-DEPENDS.patch \
           file://0001-external-SPIRV-Tools-change-path-to-tools-and-header.patch \
           "

SRC_URI:append:class-native = " file://0001-fix-tblgen.patch"

SRCREV_igc = "0e001e2814fd4e0e56275ab45adecea8f79e9ee8"
SRCREV_vc = "dd72efa3b4aafdbbf599e6f3c6f8c55450e348de"
SRCREV_spirv-tools = "eb0a36633d2acf4de82588504f951ad0f2cecacb"
SRCREV_spirv-headers = "85a1ed200d50660786c1a88d9166e871123cce39"

SRCREV_FORMAT = "igc_vc_spirv-tools_spirv-headers"

# Used to replace with relative path in reproducibility patch
export B

S = "${WORKDIR}/git"

inherit cmake pkgconfig

CXXFLAGS:append = " -Wno-error=nonnull"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += " flex-native bison-native clang opencl-clang"
DEPENDS:append:class-target = " clang-cross-x86_64 intel-graphics-compiler-native"

RDEPENDS:${PN} += "opencl-clang"

PACKAGECONFIG ??= "vc"
PACKAGECONFIG[vc] = "-DIGC_BUILD__VC_ENABLED=ON -DIGC_OPTION__LINK_KHRONOS_SPIRV_TRANSLATOR=ON -DIGC_OPTION__SPIRV_TRANSLATOR_MODE=Prebuilds,-DIGC_BUILD__VC_ENABLED=OFF,"

EXTRA_OECMAKE = " \
                  -DIGC_OPTION__LLVM_PREFERRED_VERSION=${LLVMVERSION} \
                  -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 \
                  -DVC_INTRINSICS_SRC="${S}/vc-intrinsics" \
                  -DIGC_OPTION__LLVM_MODE=Prebuilds \
                  -DLLVM_TABLEGEN=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
                  -DLLVM_LINK_EXE=${STAGING_BINDIR_NATIVE}/llvm-link \
                  -DCLANG_EXE=${STAGING_BINDIR_NATIVE}/clang \
                  "

do_install:append:class-native () {
    install -d ${D}${bindir}
    install ${B}/IGC/Release/elf_packager ${D}${bindir}/
    if ${@bb.utils.contains('PACKAGECONFIG', 'vc', 'true', 'false', d)}; then
        install ${B}/IGC/Release/CMCLTranslatorTool ${D}${bindir}/
        install ${B}/IGC/Release/vcb ${D}${bindir}/
    fi
}

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES:${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "

# libigc.so contains buildpaths
INSANE_SKIP:${PN} += "buildpaths"
