SUMMARY = "Intel® Open Image Denoise"
DESCRIPTION = "Intel Open Image Denoise is an open source library of \
high-performance, high-quality denoising filters for images \
rendered with ray tracing. Intel Open Image Denoise is part \
of the Intel® oneAPI Rendering Toolkit"
HOMEPAGE = "https://www.openimagedenoise.org/"

LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://mkl-dnn/LICENSE;md5=8e17c0f9656ebaf0c380d9b22707c846 \
                    file://mkl-dnn/src/cpu/x64/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
                    file://mkl-dnn/src/common/ittnotify/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb \
                    file://weights/LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/OpenImageDenoise/${BPN}/releases/download/v${PV}/${BP}.src.tar.gz\
           file://0001-remove-redundant-RPATH.patch \
           "
SRC_URI[sha256sum] = "3276e252297ebad67a999298d8f0c30cfb221e166b166ae5c955d88b94ad062a"

inherit cmake

DEPENDS += "tbb ispc-native"

UPSTREAM_CHECK_URI = "https://github.com/OpenImageDenoise/oidn/releases"
