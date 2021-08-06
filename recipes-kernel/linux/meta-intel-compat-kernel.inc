# These are variables that should be added to any
# kernel compatible with meta-intel

KERNEL_FEATURES_INTEL_COMMON ?= ""

COMPATIBLE_MACHINE:intel-x86-common = "${MACHINE}"
KERNEL_FEATURES:intel-x86-common = "${KERNEL_FEATURES_INTEL_COMMON}"

KMACHINE:corei7-64-intel-common = "intel-corei7-64"
KMACHINE:core2-32-intel-common = "intel-core2-32"
KMACHINE:skylake-64-intel-common = "intel-corei7-64"

INTEL_COMMON_AUTOLOAD ?= " uio iwlwifi i915"
KERNEL_MODULE_AUTOLOAD:intel-x86-common = " ${INTEL_COMMON_AUTOLOAD}"
