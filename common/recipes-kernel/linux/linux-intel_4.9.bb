
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "042ebdd2a30e9612077bd9ebf1802179af7ddf37"
SRCREV_meta ?= "3d5b27b2d138b71052a1d17a5cca73aa0ec75328"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
