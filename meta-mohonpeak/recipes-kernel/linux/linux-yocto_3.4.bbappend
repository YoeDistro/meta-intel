FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_mohonpeak32 = "mohonpeak32"
KMACHINE_mohonpeak32  = "mohonpeak32"
KBRANCH_mohonpeak32  = "standard/common-pc/base"

SRCREV_machine_pn-linux-yocto_mohonpeak32 ?=  "c29f5c8952c0f3ef27773d78e5cc64e437a357cb"
SRCREV_meta_pn-linux-yocto_mohonpeak32 ?= "ef4cd500d4b64680f7a319d399b8a12f9ecc9fe6"

COMPATIBLE_MACHINE_mohonpeak64 = "mohonpeak64"
KMACHINE_mohonpeak64  = "mohonpeak"
KBRANCH_mohonpeak64  = "standard/common-pc-64/base"

SRCREV_machine_pn-linux-yocto_mohonpeak64 ?=  "c29f5c8952c0f3ef27773d78e5cc64e437a357cb"
SRCREV_meta_pn-linux-yocto_mohonpeak64 ?= "ef4cd500d4b64680f7a319d399b8a12f9ecc9fe6"

LINUX_VERSION = "3.4.82"
