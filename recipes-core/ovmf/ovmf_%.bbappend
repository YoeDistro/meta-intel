FILESEXTRAPATHS:prepend:intel-x86-common := "${THISDIR}/files:"

SRC_URI:append:intel-x86-common = " \
	file://0001-ovmf-RefkitTestCA-TEST-UEFI-SecureBoot.patch \
"
PACKAGECONFIG:append:intel-x86-common = " secureboot"
