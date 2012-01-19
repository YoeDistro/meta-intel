FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_crownbay = "crownbay"
KMACHINE_crownbay  = "yocto/standard/crownbay"
KERNEL_FEATURES_append_crownbay += " cfg/smp.scc"

COMPATIBLE_MACHINE_crownbay-noemgd = "crownbay-noemgd"
KMACHINE_crownbay-noemgd  = "yocto/standard/crownbay"
KERNEL_FEATURES_append_crownbay-noemgd += " cfg/smp.scc"

SRCREV_machine_pn-linux-yocto_crownbay ?= "570788fd910fe8984347aa60c946a7cec5df020a"
SRCREV_meta_pn-linux-yocto_crownbay ?= "d386e09f316e03061c088d2b13a48605c20fb3a6"

SRCREV_machine_pn-linux-yocto_crownbay-noemgd ?= "570788fd910fe8984347aa60c946a7cec5df020a"
SRCREV_meta_pn-linux-yocto_crownbay-noemgd ?= "d386e09f316e03061c088d2b13a48605c20fb3a6"
