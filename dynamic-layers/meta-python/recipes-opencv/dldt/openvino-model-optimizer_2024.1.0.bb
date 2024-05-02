SUMMARY = "OpenVINO Model Optimzer"
DESCRIPTION = "Model Optimizer is a cross-platform command-line tool that \
facilitates the transition between the training and deployment \
environment, performs static model analysis, and adjusts deep \
learning models for optimal execution on end-point target devices."
HOMEPAGE = "https://01.org/openvinotoolkit"

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=https;branch=releases/2024/1;lfs=0 \
           "
SRCREV = "f4afc983258bcb2592d999ed6700043fdb58ad78"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

CVE_PRODUCT = "intel:openvino"
S = "${WORKDIR}/git"

inherit setuptools3

SETUPTOOLS_SETUP_PATH = "${WORKDIR}/git/tools/mo"

RDEPENDS:${PN} += " \
                    python3-defusedxml \
                    python3-fastjsonschema \
                    python3-networkx \
                    python3-numpy \
                    python3-protobuf \
                    python3-requests \
                    python3-urllib3 \
                    bash \
                    "

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+\.\d+\.\d+))$"
