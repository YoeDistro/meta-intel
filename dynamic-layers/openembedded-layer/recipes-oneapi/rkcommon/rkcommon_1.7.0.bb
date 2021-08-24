SUMMARY  = "rkcommon - C++/CMake infrastructure"
DESCRIPTION = "A common set of C++ infrastructure and CMake utilities \
used by various components of Intel® oneAPI Rendering Toolkit."
HOMEPAGE = "https://github.com/ospray/rkcommon"

LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ospray/rkcommon.git;protocol=https \
            "
SRCREV = "8d76e418568a928401e35049a4e17019970df23a"

DEPENDS = "tbb"

EXTRA_OECMAKE += " -DBUILD_TESTING=OFF"
