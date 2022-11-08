SUMMARY  = "Intel Metrics Discovery Application Programming Interface"
DESCRIPTION = "This software is a user mode library that provides access to \
GPU performance data."
LICENSE  = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=8c5c9ac8ffd04a5614befdf63fba6ba8"
SECTION = "lib"

inherit pkgconfig cmake

S = "${WORKDIR}/git"
SRCREV = "24a1726b50aae8bb689381b9c293a617f4d7b0da"
SRC_URI = "git://github.com/intel/metrics-discovery.git;branch=master;protocol=https \
          "

EXTRA_OECMAKE += "-DMD_PLATFORM=linux"
EXTRA_OECMAKE += "-DMD_LIBDRM_SRC=${STAGING_INCDIR}"

DEPENDS = "libdrm"
