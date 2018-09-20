
require linux-intel.inc

KBRANCH = "4.9/yocto/base"
KMETA_BRANCH = "yocto-4.9"

LINUX_VERSION ?= "4.9.126"
SRCREV_machine ?= "38d41ee6fde529a3c8e98a0134411e6f0067e0ad"
SRCREV_meta ?= "f9e90afd4e47920e990dacb73cd6508917ca5422"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
