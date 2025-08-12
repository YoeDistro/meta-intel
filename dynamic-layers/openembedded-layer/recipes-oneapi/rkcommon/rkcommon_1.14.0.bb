SUMMARY  = "rkcommon - C++/CMake infrastructure"
DESCRIPTION = "A common set of C++ infrastructure and CMake utilities \
used by various components of Intel® oneAPI Rendering Toolkit."
HOMEPAGE = "https://github.com/ospray/rkcommon"

LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pkgconfig cmake

SRC_URI = "git://github.com/ospray/rkcommon.git;protocol=https;branch=master \
           file://0001-use-fully-qualified-rkcommon-math-rsqrt-to-avoid-ove.patch \
            "
SRCREV = "4a00047ae5a3ac705b6b33b4a7574588d91e7953"

DEPENDS = "tbb"

EXTRA_OECMAKE += " -DBUILD_TESTING=OFF"
