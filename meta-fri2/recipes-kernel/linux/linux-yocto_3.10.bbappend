FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION = "3.10.35"

COMPATIBLE_MACHINE_fri2 = "fri2"
KMACHINE_fri2 = "fri2"
KBRANCH_fri2 = "standard/fri2"
KERNEL_FEATURES_append_fri2 = " features/drm-emgd/drm-emgd-1.18 cfg/vesafb"
SRCREV_meta_fri2 = "7df9ef8ee47dc9023044614210f4c1d9d916dd5f"
SRCREV_machine_fri2 = "cee957655fe67826b2e827e2db41f156fa8f0cc4"
SRCREV_emgd_fri2 = "42d5e4548e8e79e094fa8697949eed4cf6af00a3"
SRC_URI_fri2 = "git://git.yoctoproject.org/linux-yocto-3.10.git;protocol=git;nocheckout=1;branch=${KBRANCH},${KMETA},emgd-1.18;name=machine,meta,emgd"

COMPATIBLE_MACHINE_fri2-noemgd = "fri2-noemgd"
KMACHINE_fri2-noemgd = "fri2"
KBRANCH_fri2-noemgd = "standard/fri2"
KERNEL_FEATURES_append_fri2-noemgd = " cfg/vesafb"
SRCREV_meta_fri2-noemgd = "7df9ef8ee47dc9023044614210f4c1d9d916dd5f"
SRCREV_machine_fri2-noemgd = "cee957655fe67826b2e827e2db41f156fa8f0cc4"

module_autoload_iwlwifi = "iwlwifi"

