FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION_i586-nlp-32-intel-common = "4.1.37"
SRCREV_meta_i586-nlp-32-intel-common = "8f9c57ff647028ba74c5fbc7bd78b8d444ae08b8"
SRCREV_machine_i586-nlp-32-intel-common = "f398e00752ecee739ee0a031e8f02303913d0b20"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
