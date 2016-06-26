FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#LINUX_VERSION_i586-nlp-32-intel-common = "4.1.26"
COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
SRCREV_meta_i586-nlp-32-intel-common = "20edcbf4e42dd4cef7213a0ce2a4481d8d296f5d"
SRCREV_machine_i586-nlp-32-intel-common = "fb0153332a1fdd0518f9055ece1c53f3a99973f5"
KBRANCH_i586-nlp-32-intel-common = "standard/tiny/intel/base"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
