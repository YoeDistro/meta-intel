# These are variables that should be added to any
# kernel compatible with meta-intel

KERNEL_FEATURES_INTEL_COMMON ?= ""

COMPATIBLE_MACHINE_intel-x86-common = "${MACHINE}"
KERNEL_FEATURES_intel-x86-common = "${KERNEL_FEATURES_INTEL_COMMON}"

KMACHINE_corei7-64-intel-common = "intel-corei7-64"
KMACHINE_core2-32-intel-common = "intel-core2-32"
KMACHINE_skylake-64-intel-common = "intel-corei7-64"

INTEL_COMMON_AUTOLOAD ?= " uio iwlwifi i915"
KERNEL_MODULE_AUTOLOAD_intel-x86-common = " ${INTEL_COMMON_AUTOLOAD}"
