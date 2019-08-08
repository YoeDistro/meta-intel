SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;md5=4e9a7f0d710a0546cbf5581e1565a986"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https \
           file://0001-skip-execution-of-ElfPackager.patch \
           file://0001-Fix-for-the-gcc-9-issue.patch \
           "

SRCREV = "ebfc688126900a821e407a96417800919b793447"

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

DEPENDS_class-target = " flex-native bison-native clang clang-cross-x86_64"

EXTRA_OECMAKE = "-DIGC_PREFERRED_LLVM_VERSION=8.0.0 -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python2"
EXTRA_OECMAKE += "-DCOMMON_CLANG_LIBRARY_NAME=common_clang"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"
