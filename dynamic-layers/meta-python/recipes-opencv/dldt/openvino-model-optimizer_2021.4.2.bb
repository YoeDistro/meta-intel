SUMMARY = "OpenVINO Model Optimzer"
DESCRIPTION = "Model Optimizer is a cross-platform command-line tool that \
facilitates the transition between the training and deployment \
environment, performs static model analysis, and adjusts deep \
learning models for optimal execution on end-point target devices."
HOMEPAGE = "https://01.org/openvinotoolkit"

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=https;branch=releases/2021/4;lfs=0 \
           "
SRCREV = "6c4462759e8974c0ce5b36ad22972a1c6ded76a8"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

CVE_PRODUCT = "intel:openvino"

S = "${WORKDIR}/git"

do_install() {
        mkdir -p ${D}${datadir}/openvino/model-optimizer
        cp -r model-optimizer ${D}${datadir}/openvino/
}

RDEPENDS:${PN} += " \
                    python3-numpy \
                    python3-protobuf \
                    python3-defusedxml \
                    python3-networkx \
                    python3-requests \
                    python3-urllib3 \
                    bash \
                    "

FILES:${PN} += "${datadir}/openvino"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"
