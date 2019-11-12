SUMMARY = "OpenVINO Model Optimzer"
DESCRIPTION = "Model Optimizer is a cross-platform command-line tool that \
facilitates the transition between the training and deployment \
environment, performs static model analysis, and adjusts deep \
learning models for optimal execution on end-point target devices."
HOMEPAGE = "https://01.org/openvinotoolkit"

SRC_URI = "git://github.com/opencv/dldt.git;protocol=git;branch=2019 \
           "
SRCREV = "fe3f978b98c86eaeed3cbdc280e1ffd0bc50d278"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

CVE_PRODUCT = "intel:openvino"

S = "${WORKDIR}/git"

do_install() {
        mkdir -p ${D}${datadir}/openvino/model-optimizer
        cp -r model-optimizer ${D}${datadir}/openvino/
}

RDEPENDS_${PN} += " \
                    python3-numpy \
                    python3-protobuf \
                    python3-defusedxml \
                    python3-networkx \
                    python3-test-generator \
                    bash \
                    "

FILES_${PN} += "${datadir}/openvino"
