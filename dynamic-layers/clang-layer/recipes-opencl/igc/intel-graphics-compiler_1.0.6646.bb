SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;beginline=1;endline=23;md5=8b19c5999abc484f18232b0905367f9f \
                    file://NOTICES.txt;md5=b12e73994de4fbe0f688cf0bc91512a0"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https; \
           file://0001-skip-execution-of-ElfPackager.patch \
           file://link-to-LLVMGenXIntrinsics.patch \
           file://improve_src_package_reproducibility.patch \
           file://0001-Fix-build-with-LLVM-12.patch \
           file://0002-Review-fixes-for-LLVM-12-phase-1.patch \
           file://0003-Review-fixes-for-LLVM-12-phase-2.patch \
          "

SRCREV = "535aaaef03ce338e05e6162118082e6e007e8c10"

# Used to replace with relative path in reproducibility patch
export B

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

DEPENDS += " flex-native bison-native clang opencl-clang vc-intrinsics"
DEPENDS_append_class-target = " clang-cross-x86_64"

RDEPENDS_${PN} += "opencl-clang"

EXTRA_OECMAKE = "-DIGC_OPTION__LLVM_PREFERRED_VERSION=${LLVMVERSION} -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 -DIGC_BUILD__VC_ENABLED=OFF -DIGC_BUILD__USE_KHRONOS_SPIRV_TRANSLATOR=ON"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES_${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "
