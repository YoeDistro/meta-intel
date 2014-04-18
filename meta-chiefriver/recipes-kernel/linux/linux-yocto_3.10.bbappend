FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_chiefriver = "chiefriver"
KMACHINE_chiefriver = "chiefriver"
KBRANCH_chiefriver = "standard/common-pc-64/chiefriver"

KERNEL_FEATURES_append_chiefriver = " features/amt/mei/mei.scc"

LINUX_VERSION = "3.10.35"

SRCREV_meta_chiefriver = "7df9ef8ee47dc9023044614210f4c1d9d916dd5f"
SRCREV_machine_chiefriver = "cee957655fe67826b2e827e2db41f156fa8f0cc4"
