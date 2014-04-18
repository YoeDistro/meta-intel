FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_nuc = "nuc"
KMACHINE_nuc = "chiefriver"
KBRANCH_nuc = "standard/common-pc-64/chiefriver"

KERNEL_FEATURES_append_nuc = " features/amt/mei/mei.scc wifi"

LINUX_VERSION = "3.10.35"

SRCREV_meta_nuc = "7df9ef8ee47dc9023044614210f4c1d9d916dd5f"
SRCREV_machine_nuc = "cee957655fe67826b2e827e2db41f156fa8f0cc4"

module_autoload_iwlwifi_nuc = "iwlwifi"
