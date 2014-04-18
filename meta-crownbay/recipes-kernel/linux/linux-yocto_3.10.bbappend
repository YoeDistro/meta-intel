FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_crownbay = "crownbay"
KMACHINE_crownbay = "crownbay"
KBRANCH_crownbay = "standard/crownbay"
KERNEL_FEATURES_append_crownbay = " features/drm-emgd/drm-emgd-1.18 cfg/vesafb"

COMPATIBLE_MACHINE_crownbay-noemgd = "crownbay-noemgd"
KMACHINE_crownbay-noemgd = "crownbay"
KBRANCH_crownbay-noemgd = "standard/crownbay"
KERNEL_FEATURES_append_crownbay-noemgd = " cfg/vesafb"

LINUX_VERSION = "3.10.35"

SRCREV_meta_crownbay = "7df9ef8ee47dc9023044614210f4c1d9d916dd5f"
SRCREV_machine_crownbay = "cee957655fe67826b2e827e2db41f156fa8f0cc4"
SRCREV_emgd_crownbay = "42d5e4548e8e79e094fa8697949eed4cf6af00a3"

SRCREV_meta_crownbay-noemgd = "7df9ef8ee47dc9023044614210f4c1d9d916dd5f"
SRCREV_machine_crownbay-noemgd = "cee957655fe67826b2e827e2db41f156fa8f0cc4"

SRC_URI_crownbay = "git://git.yoctoproject.org/linux-yocto-3.10.git;protocol=git;nocheckout=1;branch=${KBRANCH},${KMETA},emgd-1.18;name=machine,meta,emgd"
