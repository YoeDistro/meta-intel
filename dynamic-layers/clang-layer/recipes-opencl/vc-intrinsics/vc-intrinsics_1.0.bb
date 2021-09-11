SUMMARY = "VC Intrinsics"
DESCRIPTION = "VC Intrinsics project contains a set of new intrinsics on \
top of core LLVM IR instructions that represent SIMD semantics of a program \
targeting GPU"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Readme.md;beginline=1;endline=25;md5=27387c4e2f27cccd2b07ac3c98f5f820"

SRC_URI = "git://github.com/intel/vc-intrinsics.git;protocol=https; \
          "
SRCREV = "7a46e7e3ea7eef37cc1a77043fd1bf6a3cab1d9e"

S = "${WORKDIR}/git"

inherit cmake

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += "  clang"

EXTRA_OECMAKE = "-DLLVM_DIR=${STAGING_LIBDIR}"

BBCLASSEXTEND = "native nativesdk"
