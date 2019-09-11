SUMMARY = "nGraph Compiler Stack"
DESCRIPTION = "nGraph Compiler aims to accelerate developing AI workloads using \
any deep learning framework and deploying to a variety of \
hardware targets."
HOMEPAGE = "https://www.ngraph.ai"

SRC_URI = "git://github.com/NervanaSystems/ngraph.git;protocol=https;branch=r0.22 \
           file://0001-ngraph-compile-for-DLDT-R2.patch;striplevel=2 \
           "

SRCREV = "6818cc5fae57a46d8558c9fd112e04c57a35e0fa"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

S = "${WORKDIR}/git/src"

inherit cmake

OECMAKE_GENERATOR = "Unix Makefiles"

FILES_${PN}-dev = "${includedir}"
FILES_${PN} += "${libdir}/libngraph.so"
