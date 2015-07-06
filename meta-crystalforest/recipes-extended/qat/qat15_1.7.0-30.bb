include qat.inc

PV = "2.2.0-30"

export QAT_VERSION = "1.7.0-30"
export ICP_DH89XX = "QAT1.5"
export ICP_DRIVER_TYPE = "${ICP_DH89XX}"
export ICP_FIRMWARE_DIR="firmware"

SRC_URI="https://01.org/sites/default/files/page/qatmux.l.${PV}.tar_.gz;name=qat \
	file://00-qat_qa.rules \
	file://0001-Fix-for-cross-compilation-issue.patch \
	file://qat-1.5-enable-dynamic.patch \
	"

SRC_URI[qat.md5sum] = "9567adeba4a4af50f4ac3ab70a3091ed"
SRC_URI[qat.sha256sum] = "00ac245dc6226d6bcba2326d7b6fa045f91a616c67a003f299f404af84d52032"
