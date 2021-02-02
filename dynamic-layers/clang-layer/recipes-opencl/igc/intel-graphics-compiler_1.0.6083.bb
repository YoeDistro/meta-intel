SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;beginline=1;endline=25;md5=4abf1738ff96b18e34186eb763e28eeb \
                    file://NOTICES.txt;md5=b12e73994de4fbe0f688cf0bc91512a0"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https; \
           file://0001-skip-execution-of-ElfPackager.patch \
           file://link-to-LLVMGenXIntrinsics.patch \
           file://improve_src_package_reproducibility.patch \
          "

SRCREV = "f6ec355e7e275f87e0756576cd7a390d2365ed48"

# Used to replace with relative path in reproducibility patch
export B

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

DEPENDS += " flex-native bison-native clang opencl-clang vc-intrinsics"
DEPENDS_append_class-target = " clang-cross-x86_64"

RDEPENDS_${PN} += "opencl-clang"

LLVM_COMPAT_VERSION = "${@bb.utils.contains('LLVMVERSION', '10.0.1', '10.0.0', '11.1.0', d)}"
EXTRA_OECMAKE = "-DIGC_PREFERRED_LLVM_VERSION=${LLVM_COMPAT_VERSION} -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 -DINSTALL_SPIRVDLL=0 -DIGC_BUILD__VC_ENABLED=OFF"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES_${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "
