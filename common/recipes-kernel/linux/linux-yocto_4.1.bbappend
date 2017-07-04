FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KERNEL_FEATURES_INTEL_COMMON += "features/amt/mei/mei.scc"
KERNEL_FEATURES_LEAFHILL_AUDIO += "bsp/leafhill/snd_ssp_intel.scc"

LINUX_VERSION_core2-32-intel-common = "4.1.26"
COMPATIBLE_MACHINE_core2-32-intel-common = "${MACHINE}"
KMACHINE_core2-32-intel-common = "intel-core2-32"
KBRANCH_core2-32-intel-common = "standard/intel/base"
SRCREV_meta_core2-32-intel-common ?= "20edcbf4e42dd4cef7213a0ce2a4481d8d296f5d"
SRCREV_machine_core2-32-intel-common ?= "9195020e5747fba069c19996fab079931584a7fb"
KERNEL_FEATURES_append_core2-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"

LINUX_VERSION_corei7-64-intel-common = "${@bb.utils.contains('BSP_SUBTYPE', 'leafhill', '4.1.27', '4.1.26', d)}"
COMPATIBLE_MACHINE_corei7-64-intel-common = "${MACHINE}"
KMACHINE_corei7-64-intel-common = "${@bb.utils.contains('BSP_SUBTYPE', 'leafhill', 'leafhill', 'intel-corei7-64', d)}"
KBRANCH_corei7-64-intel-common = "${@bb.utils.contains('BSP_SUBTYPE', 'leafhill', 'standard/intel/4.1.27/leaf-hill', 'standard/intel/base', d)}"
SRCREV_meta_corei7-64-intel-common ?= "c3de59fdaf93a3aaaf90adb4505e323b54dae51f"
SRCREV_machine_corei7-64-intel-common ?= "${@bb.utils.contains('BSP_SUBTYPE', 'leafhill', '0417f3b89474742be646a109140ff695dcf96d4c', '9195020e5747fba069c19996fab079931584a7fb', d)}"
KERNEL_FEATURES_append_corei7-64-intel-common = "${KERNEL_FEATURES_INTEL_COMMON} \
						 ${@bb.utils.contains('AUDIO_FEATURES', 'ssp', '${KERNEL_FEATURES_LEAFHILL_AUDIO}', '', d)}"

# Quark / X1000 BSP Info
LINUX_VERSION_i586-nlp-32-intel-common = "4.1.26"
COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KBRANCH_i586-nlp-32-intel-common = "standard/intel/base"
SRCREV_meta_i586-nlp-32-intel-common ?= "20edcbf4e42dd4cef7213a0ce2a4481d8d296f5d"
SRCREV_machine_i586-nlp-32-intel-common ?= "9195020e5747fba069c19996fab079931584a7fb"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = ""

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# For FRI2, NUC
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " iwlwifi"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " iwlwifi"
