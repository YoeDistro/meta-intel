FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION_i586-nlp-32-intel-common = "4.8.17"
SRCREV_meta_i586-nlp-32-intel-common = "bb6984f46b5a1a4fa85af23032d49d6a012bd5ab"
SRCREV_machine_i586-nlp-32-intel-common = "b62f29ac5c15d6333a13811db030d704b35ace8f"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"

KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON} cfg/fs/ext4.scc"
