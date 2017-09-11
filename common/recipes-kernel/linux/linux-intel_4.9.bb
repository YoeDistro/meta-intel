
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "2ad4fad2c2749a8ad133b853e682b9e950f28507"
SRCREV_meta ?= "f16cac53436be696fa055bc4a139f3f1dc39a9ce"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
