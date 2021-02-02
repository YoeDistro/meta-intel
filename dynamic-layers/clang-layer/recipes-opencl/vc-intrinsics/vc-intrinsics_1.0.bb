SUMMARY = "VC Intrinsics"
DESCRIPTION = "VC Intrinsics project contains a set of new intrinsics on \
top of core LLVM IR instructions that represent SIMD semantics of a program \
targeting GPU"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://License.md;md5=c18ea6bb4786a26bf4eee88a7424a408"

SRC_URI = "git://github.com/intel/vc-intrinsics.git;protocol=https; \
          "
SRCREV = "77974f5a32e19475125531483bede9ee23935257"

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

DEPENDS += "  clang"

EXTRA_OECMAKE = "-DLLVM_DIR=${STAGING_LIBDIR}"

BBCLASSEXTEND = "native nativesdk"
