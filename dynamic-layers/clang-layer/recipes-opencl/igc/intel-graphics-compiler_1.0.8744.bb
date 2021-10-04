SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;beginline=1;endline=23;md5=4a985f2545dd5a846e205b1e60a51cd9 \
                    file://NOTICES.txt;md5=db621145dfb627436bc90ad600386801"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https;name=igc \
           git://github.com/intel/vc-intrinsics.git;protocol=https;destsuffix=git/vc-intrinsics;name=vc \
           file://0001-llvm_deps.cmake-don-t-copy-header-file-when-building.patch \
           file://0003-Improve-Reproducibility-for-src-package.patch \
           file://0004-find-external-llvm-tblgen.patch \
           "

SRCREV_igc = "3ba8dde8c414a0e47df58b1bba12a64f8ba2089e"
SRCREV_vc = "e5ad7e02aa4aa21a3cd7b3e5d1f3ec9b95f58872"

# Used to replace with relative path in reproducibility patch
export B

S = "${WORKDIR}/git"

inherit cmake

CXXFLAGS:append = " -Wno-error=nonnull"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += " flex-native bison-native clang opencl-clang"
DEPENDS:append:class-target = " clang-cross-x86_64 intel-graphics-compiler-native"

RDEPENDS:${PN} += "opencl-clang"

EXTRA_OECMAKE = " \
                  -DIGC_OPTION__LLVM_PREFERRED_VERSION=${LLVMVERSION} \
                  -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 \
                  -DVC_INTRINSICS_SRC="${S}/vc-intrinsics" \
                  -DIGC_OPTION__LLVM_MODE=Prebuilds \
                  -DIGC_BUILD__VC_ENABLED=OFF \
                  "

do_install:append:class-native () {
    install -d ${D}${bindir}
    install ${B}/IGC/Release/elf_packager ${D}${bindir}/
}

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES:${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "
