SUMMARY  = "Intel Metrics Discovery Application Programming Interface"
DESCRIPTION = "This software is a user mode library that provides access to \
GPU performance data."
LICENSE  = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=195912d57508b573e068231099eba64c"
SECTION = "lib"

inherit pkgconfig cmake

S = "${WORKDIR}/git"
SRCREV = "65ffef89cf79d704f618768bcd3d95f4dd6d875b"
SRC_URI = "git://github.com/intel/metrics-discovery.git;branch=master;protocol=https \
          "

EXTRA_OECMAKE += "-DMD_PLATFORM=linux"
EXTRA_OECMAKE += "-DMD_LIBDRM_SRC=${STAGING_INCDIR}"

DEPENDS = "libdrm"
