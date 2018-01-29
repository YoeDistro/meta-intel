FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/${PN}:"

LINUX_VERSION_INTEL_COMMON = "4.4.113"
SRCREV_META_INTEL_COMMON ?= "b149d14ccae8349ab33e101f6af233a12f4b17ba"
SRCREV_MACHINE_INTEL_COMMON ?= "1b020b6a41e153456442e201a06cb34ae8f7cbf3"

KBRANCH_INTEL_COMMON = "standard/preempt-rt/intel/base"

KERNEL_FEATURES_INTEL_COMMON ?= ""

LINUX_VERSION_core2-32-intel-common = "${LINUX_VERSION_INTEL_COMMON}"
COMPATIBLE_MACHINE_core2-32-intel-common = "${MACHINE}"
KMACHINE_core2-32-intel-common = "intel-core2-32"
KBRANCH_core2-32-intel-common = "${KBRANCH_INTEL_COMMON}"
SRCREV_meta_core2-32-intel-common ?= "${SRCREV_META_INTEL_COMMON}"
SRCREV_machine_core2-32-intel-common ?= "${SRCREV_MACHINE_INTEL_COMMON}"
KERNEL_FEATURES_append_core2-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"

LINUX_VERSION_corei7-64-intel-common = "${LINUX_VERSION_INTEL_COMMON}"
COMPATIBLE_MACHINE_corei7-64-intel-common = "${MACHINE}"
KMACHINE_corei7-64-intel-common = "intel-corei7-64"
KBRANCH_corei7-64-intel-common = "${KBRANCH_INTEL_COMMON}"
SRCREV_meta_corei7-64-intel-common ?= "${SRCREV_META_INTEL_COMMON}"
SRCREV_machine_corei7-64-intel-common ?= "${SRCREV_MACHINE_INTEL_COMMON}"
KERNEL_FEATURES_append_corei7-64-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"
