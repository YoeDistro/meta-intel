
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "6f51d2bc29cdce8d86a408393a90c0decdea9384"
SRCREV_meta ?= "e8095d45e15f02ffc953312bb41a57554b25a439"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
