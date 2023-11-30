SUMMARY = "Intel® Open Image Denoise"
DESCRIPTION = "Intel Open Image Denoise is an open source library of \
high-performance, high-quality denoising filters for images \
rendered with ray tracing. Intel Open Image Denoise is part \
of the Intel® oneAPI Rendering Toolkit"
HOMEPAGE = "https://www.openimagedenoise.org/"

LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://external/mkl-dnn/LICENSE;md5=b48e3de3bfd47c27882a0d85b20823f5 \
                    file://external/mkl-dnn/src/cpu/x64/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
                    file://external/mkl-dnn/src/common/ittnotify/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb \
                    file://weights/LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/OpenImageDenoise/${BPN}/releases/download/v${PV}/${BP}.src.tar.gz\
           "
SRC_URI[sha256sum] = "ce144ba582ff36563d9442ee07fa2a4d249bc85aa93e5b25fc527ff4ee755ed6"

inherit cmake

DEPENDS += "tbb ispc-native"

UPSTREAM_CHECK_URI = "https://github.com/OpenImageDenoise/oidn/releases"
