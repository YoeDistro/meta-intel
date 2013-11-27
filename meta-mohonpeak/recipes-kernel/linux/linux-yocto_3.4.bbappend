FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_mohonpeak32 = "mohonpeak32"
KMACHINE_mohonpeak32  = "mohonpeak32"
KBRANCH_mohonpeak32  = "standard/common-pc/base"

SRCREV_machine_pn-linux-yocto_mohonpeak32 ?=  "ea977edd05ae2ebfa82731e0bee309bdfd08abee"
SRCREV_meta_pn-linux-yocto_mohonpeak32 ?= "f36797c2df3fbe9491c8ac5977fb691f4a75e9b7"

COMPATIBLE_MACHINE_mohonpeak64 = "mohonpeak64"
KMACHINE_mohonpeak64  = "mohonpeak"
KBRANCH_mohonpeak64  = "standard/common-pc-64/base"

SRCREV_machine_pn-linux-yocto_mohonpeak64 ?=  "ea977edd05ae2ebfa82731e0bee309bdfd08abee"
SRCREV_meta_pn-linux-yocto_mohonpeak64 ?= "f36797c2df3fbe9491c8ac5977fb691f4a75e9b7"

LINUX_VERSION = "3.4.59"
