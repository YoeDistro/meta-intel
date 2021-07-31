SUMMARY = "VC Intrinsics"
DESCRIPTION = "VC Intrinsics project contains a set of new intrinsics on \
top of core LLVM IR instructions that represent SIMD semantics of a program \
targeting GPU"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Readme.md;beginline=1;endline=25;md5=607994952928db68a8a86574f661b914"

SRC_URI = "git://github.com/intel/vc-intrinsics.git;protocol=https; \
          "
SRCREV = "efec192d98e6d8ad611025d2cefd7a31cbc1d44b"

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += "  clang"

EXTRA_OECMAKE = "-DLLVM_DIR=${STAGING_LIBDIR}"

BBCLASSEXTEND = "native nativesdk"
