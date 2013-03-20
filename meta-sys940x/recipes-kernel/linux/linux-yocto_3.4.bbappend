FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_sys940x = "sys940x"
KMACHINE_sys940x  = "sys940x"
KBRANCH_sys940x = "standard/sys940x"
KERNEL_FEATURES_sys940x_append = " features/drm-emgd/drm-emgd-1.16 cfg/vesafb"
SRCREV_machine_pn-linux-yocto_sys940x ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_meta_pn-linux-yocto_sys940x ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"
SRCREV_emgd_sys940x = "f5c3a221f0e42d48ee5af369d73594e26ef7fae6"

COMPATIBLE_MACHINE_sys940x-noemgd = "sys940x-noemgd"
KMACHINE_sys940x-noemgd  = "sys940x"
KBRANCH_sys940x-noemgd = "standard/sys940x"
KERNEL_FEATURES_sys940x-noemgd_append = " cfg/vesafb"
SRCREV_machine_pn-linux-yocto_sys940x-noemgd ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_meta_pn-linux-yocto_sys940x-noemgd ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"

LINUX_VERSION = "3.4.28"

SRC_URI_sys940x = "git://git.yoctoproject.org/linux-yocto-3.4.git;protocol=git;nocheckout=1;branch=${KBRANCH},${KMETA},emgd-1.16;name=machine,meta,emgd"
