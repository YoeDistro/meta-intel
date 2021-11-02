SUMMARY = "VC Intrinsics"
DESCRIPTION = "VC Intrinsics project contains a set of new intrinsics on \
top of core LLVM IR instructions that represent SIMD semantics of a program \
targeting GPU"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Readme.md;beginline=1;endline=7;md5=3b2db19c3b0877bb312b7adbcb815adc"

SRC_URI = "git://github.com/intel/vc-intrinsics.git;protocol=https;;branch=master \
          "
SRCREV = "a2f2f10dc61c8161c57cf33ed606c8e3ccf3a921"

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += "  clang"

EXTRA_OECMAKE = "-DLLVM_DIR=${STAGING_LIBDIR}"

BBCLASSEXTEND = "native nativesdk"
