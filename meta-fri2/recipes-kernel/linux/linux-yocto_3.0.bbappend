FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_fri2 = "fri2"
KMACHINE_fri2  = "yocto/standard/fri2"
KERNEL_FEATURES_append_fri2 += " cfg/smp.scc"

COMPATIBLE_MACHINE_fri2-noemgd = "fri2-noemgd"
KMACHINE_fri2-noemgd  = "yocto/standard/fri2"
KERNEL_FEATURES_append_fri2-noemgd += " cfg/smp.scc"

SRCREV_machine_pn-linux-yocto_fri2 ?= "6bf40aa4f2271be63d6b39666ff96c90849284c1"
SRCREV_meta_pn-linux-yocto_fri2 ?= "d386e09f316e03061c088d2b13a48605c20fb3a6"

SRCREV_machine_pn-linux-yocto_fri2-noemgd ?= "6bf40aa4f2271be63d6b39666ff96c90849284c1"
SRCREV_meta_pn-linux-yocto_fri2-noemgd ?= "d386e09f316e03061c088d2b13a48605c20fb3a6"
