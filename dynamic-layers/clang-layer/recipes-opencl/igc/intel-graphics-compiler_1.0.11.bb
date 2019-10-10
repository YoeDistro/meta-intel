SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;beginline=1;endline=25;md5=4abf1738ff96b18e34186eb763e28eeb"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https \
           file://0001-skip-execution-of-ElfPackager.patch \
           file://0002-comment-out-dump-functions.patch \
           file://0003-Fix-for-buildbreak-when-using-clang-9-compiler.patch \
          "

SRCREV = "5f96c6db35ffa238fd84314c99a53e6708322d65"

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

DEPENDS += " flex-native bison-native clang opencl-clang"
DEPENDS_append_class-target = " clang-cross-x86_64"

RDEPENDS_${PN} += "opencl-clang"

EXTRA_OECMAKE = "-DIGC_PREFERRED_LLVM_VERSION=9.0.0 -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"
