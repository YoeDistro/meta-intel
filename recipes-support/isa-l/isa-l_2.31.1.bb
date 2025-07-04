SUMMARY  = "Intelligent Storage Acceleration Library"
DESCRIPTION = "ISA-L is a collection of optimized low-level functions \
targeting storage applications."
LICENSE  = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2476688d0948d4a3f7c69473a603540d"
SECTION = "lib"

inherit autotools pkgconfig

SRCREV = "c387163fcbca62701d646149564c550c78a4f985"
SRC_URI = "git://github.com/intel/isa-l.git;branch=master;protocol=https"

DEPENDS = "nasm-native"
AS[unexport] = "1"

TARGET_CC_ARCH += "${LDFLAGS}"

COMPATIBLE_HOST = '(x86_64).*-linux'
