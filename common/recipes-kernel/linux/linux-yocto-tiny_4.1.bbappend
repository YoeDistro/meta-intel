FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION_i586-nlp-32-intel-common = "4.1.43"
SRCREV_meta_i586-nlp-32-intel-common = "11ebcfbc45fb55c7bfd02e98d218011f88732a8d"
SRCREV_machine_i586-nlp-32-intel-common = "81122b12d7b9b891cb69063e6e269179c287a89c"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
