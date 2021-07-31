FILESEXTRAPATHS:prepend:intel-x86-common := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE:core2-32-intel-common = "${MACHINE}"
KMACHINE:core2-32-intel-common = "intel-core2-32"
KERNEL_FEATURES:append:core2-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"

COMPATIBLE_MACHINE:corei7-64-intel-common = "${MACHINE}"
KMACHINE:corei7-64-intel-common = "intel-corei7-64"
KERNEL_FEATURES:append:corei7-64-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"

# NOTE: We do not set SRCREVs here as -dev is intended to be built with AUTOREV
# and setting them here breaks the default mechanism to use AUTOREV if the
# default SRCREV is set and linux-yocto-dev is the preferred provider.

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD:append:core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD:append:corei7-64-intel-common = " uio"

# For FRI2, NUC
KERNEL_MODULE_AUTOLOAD:append:core2-32-intel-common = " iwlwifi"
KERNEL_MODULE_AUTOLOAD:append:corei7-64-intel-common = " iwlwifi"
