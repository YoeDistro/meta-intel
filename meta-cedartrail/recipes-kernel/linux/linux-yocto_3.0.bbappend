FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_cedartrail = "cedartrail"
KMACHINE_cedartrail  = "yocto/standard/cedartrail"

# The Cedarview processors have hyperthreading and includes dual-core versions
KERNEL_FEATURES_append_cedartrail += " cfg/smp.scc"

SRCREV_machine_pn-linux-yocto_cedartrail ?= "5746120ee0afefd1e28400bea4b74f285bd5e59a"
SRCREV_meta_pn-linux-yocto_cedartrail ?= "d588bdafc0d9b4d2386144b7d76a1d379e2d16c0"
