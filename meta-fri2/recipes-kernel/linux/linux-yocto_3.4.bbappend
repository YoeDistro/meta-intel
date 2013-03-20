FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_fri2 = "fri2"
KMACHINE_fri2  = "fri2"
KBRANCH_fri2 = "standard/fri2"
KERNEL_FEATURES_fri2_append = " features/drm-emgd/drm-emgd-1.16 cfg/vesafb"
SRCREV_machine_pn-linux-yocto_fri2 ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_meta_pn-linux-yocto_fri2 ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"
SRCREV_emgd_fri2 = "f5c3a221f0e42d48ee5af369d73594e26ef7fae6"

COMPATIBLE_MACHINE_fri2-noemgd = "fri2-noemgd"
KMACHINE_fri2-noemgd  = "fri2"
KBRANCH_fri2-noemgd = "standard/fri2"
KERNEL_FEATURES_fri2-noemgd_append = " cfg/vesafb"
SRCREV_machine_pn-linux-yocto_fri2-noemgd ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_meta_pn-linux-yocto_fri2-noemgd ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"

module_autoload_iwlwifi = "iwlwifi"

LINUX_VERSION = "3.4.28"

SRC_URI_fri2 = "git://git.yoctoproject.org/linux-yocto-3.4.git;protocol=git;nocheckout=1;branch=${KBRANCH},${KMETA},emgd-1.16;name=machine,meta,emgd"
