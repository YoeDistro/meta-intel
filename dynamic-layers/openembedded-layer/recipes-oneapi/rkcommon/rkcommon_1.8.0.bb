SUMMARY  = "rkcommon - C++/CMake infrastructure"
DESCRIPTION = "A common set of C++ infrastructure and CMake utilities \
used by various components of Intel® oneAPI Rendering Toolkit."
HOMEPAGE = "https://github.com/ospray/rkcommon"

LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ospray/rkcommon.git;protocol=https;branch=master \
            "
SRCREV = "27a18810ab7a8832a75bb136ade3ea9c32583e16"

DEPENDS = "tbb"

EXTRA_OECMAKE += " -DBUILD_TESTING=OFF"
