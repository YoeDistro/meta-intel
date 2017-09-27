FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION_i586-nlp-32-intel-common = "4.8.25"
SRCREV_meta_i586-nlp-32-intel-common = "76db2c6ca67b9b3597257684a027344d11b1bc81"
SRCREV_machine_i586-nlp-32-intel-common = "39c746a1aef2c1a5072ca1ea6742b697478fa465"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"

KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON} cfg/fs/ext4.scc"
