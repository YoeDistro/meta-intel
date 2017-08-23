
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "0ca18bd8ce05428471b46031f821e17c3e7d1d24"
SRCREV_meta ?= "e8095d45e15f02ffc953312bb41a57554b25a439"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
