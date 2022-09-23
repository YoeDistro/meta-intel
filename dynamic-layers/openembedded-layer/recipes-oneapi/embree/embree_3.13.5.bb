SUMMARY  = "Collection of high-performance ray tracing kernels"
DESCRIPTION = "A collection of high-performance ray tracing kernels \
intended to graphics application engineers that want to improve the \
performance of their application."
HOMEPAGE = "https://github.com/embree/embree"

LICENSE  = "Apache-2.0 & syrah"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third-party-programs.txt;md5=86f6028ba2138fe41ac5c121d0a3518e \
                    file://common/math/transcendental.h;beginline=6;endline=8;md5=73380bb2ab6613b30b8464f114bd0ca8"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/embree/embree.git;protocol=https;branch=devel3"
SRCREV = "698442324ccddd11725fb8875275dc1384f7fb40"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS = "tbb jpeg libpng glfw ispc-native"

EXTRA_OECMAKE += " \
                  -DEMBREE_ISPC_SUPPORT=OFF  \
                  -DEMBREE_IGNORE_CMAKE_CXX_FLAGS=OFF  \
                  -DEMBREE_MAX_ISA=DEFAULT  \
                  -DEMBREE_TUTORIALS=OFF  \
                  -DEMBREE_ISPC_SUPPORT=ON  \
                  "
