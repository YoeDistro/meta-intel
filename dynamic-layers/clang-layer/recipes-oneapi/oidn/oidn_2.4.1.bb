SUMMARY = "Intel® Open Image Denoise"
DESCRIPTION = "Intel Open Image Denoise is an open source library of \
high-performance, high-quality denoising filters for images \
rendered with ray tracing. Intel Open Image Denoise is part \
of the Intel® oneAPI Rendering Toolkit"
HOMEPAGE = "https://www.openimagedenoise.org/"

LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://external/cutlass/LICENSE.txt;md5=ce85e3722fa981b4aef41101c60ed4a4 \
                    file://external/composable_kernel/LICENSE;md5=2049c7351121e693518087ea93a76da2 \
                    file://weights/LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/RenderKit/${BPN}/releases/download/v${PV}/${BPN}-${PV}.src.tar.gz\
           "
SRC_URI[sha256sum] = "9c7c77ae0d57e004479cddb7aaafd405c2cc745153bed4805413c21be610e17b"

S = "${UNPACKDIR}/${BPN}-${PV}"

inherit cmake

# ISPC generates headers with embedded build paths
INSANE_SKIP:${PN}-src += "buildpaths"
INSANE_SKIP:${PN}-dbg += "buildpaths"

DEPENDS += "tbb ispc-native"

do_install:append() {
        chrpath -d ${D}${bindir}/* ${D}${libdir}/*${SOLIBS}
}

UPSTREAM_CHECK_URI = "https://github.com/RenderKit/oidn/releases"
