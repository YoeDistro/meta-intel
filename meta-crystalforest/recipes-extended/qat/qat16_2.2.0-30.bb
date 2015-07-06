include qat.inc

PV = "2.2.0-30"

export QAT_VERSION = "2.2.0-30"
export ICP_DH895X = "QAT1.6"
export ICP_DRIVER_TYPE = "${ICP_DH895X}"
export ICP_FIRMWARE_DIR="firmware/dh895xcc"

SRC_URI="https://01.org/sites/default/files/page/qatmux.l.${PV}.tar_.gz;name=qat \
	file://00-qat_qa.rules \
	file://0001-Fix-for-cross-compilation-issue.patch \
	file://dc_session.h \
	file://qat-1.6-app-fix-QA-issue.patch \
	file://qat-1.6-app-dynamiccompression.patch \
	"

SRC_URI[qat.md5sum] = "9567adeba4a4af50f4ac3ab70a3091ed"
SRC_URI[qat.sha256sum] = "00ac245dc6226d6bcba2326d7b6fa045f91a616c67a003f299f404af84d52032"

do_unpack2_append() {
cd ${WORKDIR}/
mv dc_session.h ${ICP_DRIVER_TYPE}/quickassist/lookaside/access_layer/src/common/compression/include/
}
