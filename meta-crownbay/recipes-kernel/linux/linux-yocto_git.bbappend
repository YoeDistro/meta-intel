FILESEXTRAPATHS := "${THISDIR}/${PN}"
COMPATIBLE_MACHINE_crownbay = "crownbay"
KMACHINE_crownbay  = "yocto/standard/crownbay"

SRC_URI += "file://0001-crownbay-update-a-handful-of-EMGD-licenses.patch"
