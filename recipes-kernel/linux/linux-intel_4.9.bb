
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "c2e99163add32c914514460fb67140409e287476"
SRCREV_meta ?= "f4e37e151102d89c4d0e110c88eb3b3c36bdeaa4"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
