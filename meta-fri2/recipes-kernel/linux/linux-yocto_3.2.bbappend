FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_fri2 = "fri2"
KMACHINE_fri2  = "fri2"
KBRANCH_fri2 = "standard/default/fri2"
SRCREV_machine_pn-linux-yocto_fri2 ?= "76dc683eccc46804729a76b9d2fd425ba540a483"
SRCREV_meta_pn-linux-yocto_fri2 ?= "5b4c9dc78b5ae607173cc3ddab9bce1b5f78129b"

COMPATIBLE_MACHINE_fri2-noemgd = "fri2-noemgd"
KMACHINE_fri2-noemgd  = "fri2"
KBRANCH_fri2-noemgd = "standard/default/fri2"
SRCREV_machine_pn-linux-yocto_fri2-noemgd ?= "76dc683eccc46804729a76b9d2fd425ba540a483"
SRCREV_meta_pn-linux-yocto_fri2-noemgd ?= "5b4c9dc78b5ae607173cc3ddab9bce1b5f78129b"

module_autoload_iwlwifi = "iwlwifi"
