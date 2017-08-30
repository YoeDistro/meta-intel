
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "6b22e2135fd1d3255222577aba812bc3c49fd137"
SRCREV_meta ?= "1341c5348935adc7ef4b3994d1fa9f67368efe8b"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
