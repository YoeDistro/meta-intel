FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KERNEL_FEATURES_INTEL_COMMON ?= ""

LINUX_VERSION_i586-nlp-32-intel-common = "4.1.41"
SRCREV_meta_i586-nlp-32-intel-common = "9f9c9a66ef3452343586adf150137967e955d71a"
SRCREV_machine_i586-nlp-32-intel-common = "ff69a6453667e1ecfcfaf002ab116616398eb397"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
