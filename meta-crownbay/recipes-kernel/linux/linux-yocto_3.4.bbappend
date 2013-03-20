FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_crownbay = "crownbay"
KMACHINE_crownbay  = "crownbay"
KBRANCH_crownbay  = "standard/crownbay"

KERNEL_FEATURES_crownbay_append = " features/drm-emgd/drm-emgd-1.16 cfg/vesafb"

COMPATIBLE_MACHINE_crownbay-noemgd = "crownbay-noemgd"
KMACHINE_crownbay-noemgd  = "crownbay"
KBRANCH_crownbay-noemgd  = "standard/crownbay"

KERNEL_FEATURES_crownbay-noemgd_append = " cfg/vesafb"

SRCREV_machine_pn-linux-yocto_crownbay ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_meta_pn-linux-yocto_crownbay ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"
SRCREV_emgd_pn-linux-yocto_crownbay ?= "f5c3a221f0e42d48ee5af369d73594e26ef7fae6"

SRCREV_machine_pn-linux-yocto_crownbay-noemgd ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_meta_pn-linux-yocto_crownbay-noemgd ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"

KSRC_linux_yocto_3_4 ?= "git.yoctoproject.org/linux-yocto-3.4.git"
SRC_URI_crownbay = "git://git.yoctoproject.org/linux-yocto-3.4.git;protocol=git;nocheckout=1;branch=${KBRANCH},meta,emgd-1.16;name=machine,meta,emgd"
SRC_URI_crownbay-noemgd = "git://git.yoctoproject.org/linux-yocto-3.4.git;protocol=git;nocheckout=1;branch=${KBRANCH},meta;name=machine,meta"

LINUX_VERSION = "3.4.28"
