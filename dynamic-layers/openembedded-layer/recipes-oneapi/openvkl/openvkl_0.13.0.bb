SUMMARY  = "Intel(R) Open Volume Kernel Library"
DESCRIPTION = "Intel(R) Open Volume Kernel Library (Intel(R) Open VKL) is a \
collection of high-performance volume computation kernels. The target users \
of Open VKL are graphics application engineers who want to improve the \
performance of their volume rendering applications by leveraging Open VKL’s \
performance-optimized kernels, which include volume traversal and sampling \
functionality for a variety of volumetric data formats. The kernels are optimized \
for the latest Intel(R) processors with support for SSE, AVX, AVX2, and AVX-512 \
instructions."
HOMEPAGE = "https://www.openvkl.org/"

LICENSE  = "Apache-2.0 & BSD-3-Clause & MIT & Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third-party-programs.txt;md5=90d62b467dd4fdf3c7d3d897fbac7437 \
                    file://testing/external/half.hpp;beginline=1;endline=17;md5=cc1d65765aef5c4f6a37e7833173835a"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/openvkl/openvkl.git;protocol=https \
            file://0001-half.hpp-Fixed-missing-include-for-F16C-intrinsics.patch \
            "
SRCREV = "84b9d78ead12f369f37cee77d985da9d13c07ae1"

COMPATIBLE_HOST = '(x86_64).*-linux'

DEPENDS = "ispc-native rkcommon embree"

EXTRA_OECMAKE += " \
                  -DISPC_EXECUTABLE=${STAGING_BINDIR_NATIVE}/ispc  \
                  "
PACKAGES =+ "${PN}-examples"
FILES:${PN}-examples = "\
                     ${bindir} \
                     "
