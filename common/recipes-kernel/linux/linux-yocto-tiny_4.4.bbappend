FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION_i586-nlp-32-intel-common = "4.4.87"
SRCREV_meta_i586-nlp-32-intel-common = "804d2b3164ec25ed519fd695de9aa0908460c92e"
SRCREV_machine_i586-nlp-32-intel-common = "85b913cc9d5e13602d69e50bd9cf1a6ef242f9ee"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON} cfg/fs/ext4.scc"
