SUMMARY  = "Intelligent Storage Acceleration Library"
DESCRIPTION = "ISA-L is a collection of optimized low-level functions \
targeting storage applications."
LICENSE  = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6c7d82ad7dd512687b0991a159a1ca9"
SECTION = "lib"

inherit autotools pkgconfig

S = "${WORKDIR}/git"
SRCREV = "2df39cf5f1b9ccaa2973f6ef273857e4dc46f0cf"
SRC_URI = "git://github.com/intel/isa-l.git;branch=master;protocol=https"

DEPENDS = "nasm-native"
AS[unexport] = "1"

COMPATIBLE_HOST = '(x86_64).*-linux'
