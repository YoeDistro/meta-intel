SUMMARY  = "Collection of high-performance ray tracing kernels"
DESCRIPTION = "A collection of high-performance ray tracing kernels \
intended to graphics application engineers that want to improve the \
performance of their application."
HOMEPAGE = "https://github.com/embree/embree"

LICENSE  = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third-party-programs.txt;md5=f989f5b74cfff0d45d3ccf0e1366cbdc \
                    file://common/math/transcendental.h;beginline=6;endline=8;md5=73380bb2ab6613b30b8464f114bd0ca8"

inherit pkgconfig cmake

SRC_URI = "git://github.com/embree/embree.git;protocol=https;branch=master"
SRCREV = "5730b150471602d6dc02d9b7d8a4a6ce9ceffe16"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS = "tbb jpeg libpng ispc-native"

EXTRA_OECMAKE += " \
                  -DEMBREE_IGNORE_CMAKE_CXX_FLAGS=OFF  \
                  -DEMBREE_MAX_ISA=DEFAULT  \
                  -DEMBREE_ISPC_SUPPORT=ON  \
                  -DEMBREE_ZIP_MODE=OFF  \
                  "
# When tutorials are enabled, glvnd needs to be enabled:
# DISTRO_FEATURES += "glvnd"
PACKAGECONFIG[tutorial] = "-DEMBREE_TUTORIALS=ON,-DEMBREE_TUTORIALS=OFF,glfw"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"
