FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
COMPATIBLE_MACHINE_cedartrail = "cedartrail"
KMACHINE_cedartrail  = "cedartrail"

COMPATIBLE_MACHINE_cedartrail-nopvr = "cedartrail"
KMACHINE_cedartrail-nopvr  = "cedartrail"
KERNEL_FEATURES_append_cedartrail-nopvr += " cfg/smp.scc"

COMPATIBLE_MACHINE_cedartrail = "cedartrail"
KMACHINE_cedartrail  = "cedartrail"
KERNEL_FEATURES_append_cedartrail += " cfg/smp.scc"
KERNEL_FEATURES_append_cedartrail += " cfg/drm-cdvpvr.scc"
KERNEL_FEATURES_append_cedartrail += " bsp/cedartrail/cedartrail-pvr-merge.scc"

# Update the following to use a different BSP branch or meta SRCREV
#KBRANCH_cedartrail-nopvr = "yocto/standard/preempt-rt/base"
#SRCREV_machine_pn-linux-yocto-rt_cedartrail-nopvr ?= XXXX
#SRCREV_meta_pn-linux-yocto-rt_cedartrail-nopvr ?= XXXX

#KBRANCH_cedartrail  = "yocto/standard/preempt-rt/base"
#SRCREV_machine_pn-linux-yocto-rt_cedartrail ?= XXXX
#SRCREV_meta_pn-linux-yocto-rt_cedartrail ?= XXXX
