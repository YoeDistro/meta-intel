SUMMARY = "Common clang is a thin wrapper library around clang"
DESCRIPTION = "Common clang has OpenCL-oriented API and is capable \
 to compile OpenCL C kernels to SPIR-V modules."

LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8a15bf1416762a09ece07e44c79118c"

SRC_URI = "git://github.com/intel/opencl-clang.git;branch=${BRANCH};protocol=https \
           "
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "clang"
DEPENDS:append:class-target = " opencl-clang-native"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

BBCLASSEXTEND = "native nativesdk"
