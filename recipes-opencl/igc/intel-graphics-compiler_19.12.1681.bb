SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://third_party/opencl_headers/LICENSE;md5=dcefc90f4c3c689ec0c2489064e7273b \
                    file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;md5=554b051aebc99c20a0d2b6d695dfe4af"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https \
           file://0001-skip-execution-of-ElfPackager.patch \
           "

SRCREV = "81d467882a26d57da18e7f6d8aa3a28cda842642"

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

DEPENDS_class-target = " flex-native bison-native clang clang-cross-x86_64"

EXTRA_OECMAKE = "-DIGC_PREFERRED_LLVM_VERSION=8.0.0 -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python2"
