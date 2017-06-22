FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KERNEL_FEATURES_INTEL_COMMON ?= ""

LINUX_VERSION_i586-nlp-32-intel-common = "4.1.39"
SRCREV_meta_i586-nlp-32-intel-common = "c3de59fdaf93a3aaaf90adb4505e323b54dae51f"
SRCREV_machine_i586-nlp-32-intel-common = "3a0465de39727615f8eb7830b27ff0f58f577c4c"

COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
