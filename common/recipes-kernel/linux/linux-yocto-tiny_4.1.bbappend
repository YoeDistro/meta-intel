FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KERNEL_FEATURES_INTEL_COMMON ?= ""

LINUX_VERSION_i586-nlp-32-intel-common = "4.1.49"
SRCREV_meta_i586-nlp-32-intel-common = "4e12cb8f8e06636f2058ea0ab3096ed38228a88b"
SRCREV_machine_i586-nlp-32-intel-common = "9503c5000f9785625fc60b30eb363f9f6e621d20"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
