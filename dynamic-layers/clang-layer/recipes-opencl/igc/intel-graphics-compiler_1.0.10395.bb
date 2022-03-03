SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;beginline=1;endline=23;md5=4a985f2545dd5a846e205b1e60a51cd9 \
                    file://NOTICES.txt;md5=7f4fbc3eb2c34807465e63b1ec3c9d1a"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https;name=igc;branch=master \
           git://github.com/intel/vc-intrinsics.git;protocol=https;destsuffix=git/vc-intrinsics;name=vc;branch=master \
           git://github.com/KhronosGroup/SPIRV-Tools.git;protocol=https;destsuffix=SPIRV-Tools;name=spirv-tools;branch=master \
           git://github.com/KhronosGroup/SPIRV-Headers.git;protocol=https;destsuffix=SPIRV-Headers;name=spirv-headers;branch=master \
           file://0001-llvm_deps.cmake-don-t-copy-header-file-when-building.patch \
           file://0003-Improve-Reproducibility-for-src-package.patch \
           file://0004-find-external-llvm-tblgen.patch \
           file://0001-BiF-CMakeLists.txt-remove-opt-from-DEPENDS.patch \
           file://0001-llvm-link-external.patch \
           "

SRCREV_igc = "775a850f9b0c2d7249503b47ad6bd39a4eb9b3d7"
SRCREV_vc = "5066d947985dd0c5107765daec5f24f735f3259a"
SRCREV_spirv-tools = "eeb973f5020a5f0e92ad6da879bc4df9f5985a1c"
SRCREV_spirv-headers = "ae217c17809fadb232ec94b29304b4afcd417bb4"

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
PACKAGECONFIG[vc] = "-DIGC_BUILD__VC_ENABLED=ON -DIGC_OPTION__LINK_KHRONOS_SPIRV_TRANSLATOR=ON -DIGC_OPTION__USE_KHRONOS_SPIRV_TRANSLATOR_IN_VC=ON -DIGC_OPTION__SPIRV_TRANSLATOR_MODE=Prebuilds,-DIGC_BUILD__VC_ENABLED=OFF,"

EXTRA_OECMAKE = " \
                  -DIGC_OPTION__LLVM_PREFERRED_VERSION=${LLVMVERSION} \
                  -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 \
                  -DVC_INTRINSICS_SRC="${S}/vc-intrinsics" \
                  -DIGC_OPTION__LLVM_MODE=Prebuilds \
                  "

do_install:append:class-native () {
    install -d ${D}${bindir}
    install ${B}/IGC/Release/elf_packager ${D}${bindir}/
    if ${@bb.utils.contains('PACKAGECONFIG', 'vc', 'true', 'false', d)}; then
        install ${B}/IGC/Release/CMCLTranslatorTool ${D}${bindir}/
    fi
}

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES:${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "
