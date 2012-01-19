FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_emenlow = "emenlow"
KMACHINE_emenlow  = "yocto/standard/emenlow"
KERNEL_FEATURES_append_emenlow += " cfg/smp.scc"

SRCREV_machine_pn-linux-yocto_emenlow ?= "5e4bde7fd0ce4cbb2e6ea5459732ed928d653af3"
SRCREV_meta_pn-linux-yocto_emenlow ?= "d386e09f316e03061c088d2b13a48605c20fb3a6"
