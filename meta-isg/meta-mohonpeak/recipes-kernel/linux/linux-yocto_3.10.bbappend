FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_mohonpeak32 = "mohonpeak32"
KMACHINE_mohonpeak32  = "mohonpeak32"
KBRANCH_mohonpeak32  = "standard/common-pc/base"

LINUX_VERSION_mohonpeak32 = "3.10.34"
SRCREV_machine_pn-linux-yocto_mohonpeak32 ?=  "10f8245e0d3650144b034142c8f91e5d15c392ab"
SRCREV_meta_pn-linux-yocto_mohonpeak32 ?= "4d658aa580df62232a4a84957b02496436dc17c4"

COMPATIBLE_MACHINE_mohonpeak64 = "mohonpeak64"
KMACHINE_mohonpeak64  = "mohonpeak"
KBRANCH_mohonpeak64  = "standard/common-pc-64/base"

LINUX_VERSION_mohonpeak64 = "3.10.34"
SRCREV_machine_pn-linux-yocto_mohonpeak64 ?=  "10f8245e0d3650144b034142c8f91e5d15c392ab"
SRCREV_meta_pn-linux-yocto_mohonpeak64 ?= "4d658aa580df62232a4a84957b02496436dc17c4"
