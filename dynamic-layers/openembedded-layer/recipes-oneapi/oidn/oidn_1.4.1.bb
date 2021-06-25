SUMMARY = "Intel® Open Image Denoise"
DESCRIPTION = "Intel Open Image Denoise is an open source library of \
high-performance, high-quality denoising filters for images \
rendered with ray tracing. Intel Open Image Denoise is part \
of the Intel® oneAPI Rendering Toolkit"
HOMEPAGE = "https://www.openimagedenoise.org/"

LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://mkl-dnn/LICENSE;md5=3c6ff4426dbd618bcfd552ac4a7c1c56 \
                    file://mkl-dnn/src/cpu/x64/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
                    file://mkl-dnn/src/cpu/x64/jit_utils/jitprofiling/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb \
                    file://weights/LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/OpenImageDenoise/${BPN}/releases/download/v${PV}/${BP}.src.tar.gz"
SRC_URI[sha256sum] = "9088966685a78adf24b8de075d66e4c0019bd7b2b9d29c6e45aaf35d294e3f6f"

inherit cmake

DEPENDS += "tbb ispc-native"
