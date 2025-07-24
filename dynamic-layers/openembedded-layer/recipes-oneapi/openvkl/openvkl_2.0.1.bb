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
                    file://third-party-programs.txt;md5=69ec7caf49616c471161b921f53d5ec0 \
                    file://testing/external/half.hpp;beginline=1;endline=17;md5=4b60058493630c3bd0ef145470f04a7b"

inherit pkgconfig cmake

SRC_URI = "git://github.com/openvkl/openvkl.git;protocol=https;branch=master \
           "
SRCREV = "8c6ba526813b871a624cb9d73d4cbb689ac7f4ce"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS = "ispc ispc-native rkcommon embree"

EXTRA_OECMAKE += " \
                  -DISPC_EXECUTABLE=${STAGING_BINDIR_NATIVE}/ispc  \
                  -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
                  "
EXTRA_OECMAKE:intel-corei7-64 += " \
                  -DOPENVKL_ISA_AVX=OFF  \
                  -DOPENVKL_ISA_AVX2=OFF  \
                  -DOPENVKL_ISA_AVX512SKX=OFF  \
                  "

PACKAGES =+ "${PN}-examples"
FILES:${PN}-examples = "\
                     ${bindir} \
                     "
