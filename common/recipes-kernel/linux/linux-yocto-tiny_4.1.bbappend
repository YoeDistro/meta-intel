FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KERNEL_FEATURES_INTEL_COMMON ?= ""

LINUX_VERSION_i586-nlp-32-intel-common = "4.1.43"
SRCREV_meta_i586-nlp-32-intel-common = "a867edab415f0ddc1a0d8da6ddb43c8afa9611dd"
SRCREV_machine_i586-nlp-32-intel-common = "81122b12d7b9b891cb69063e6e269179c287a89c"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
