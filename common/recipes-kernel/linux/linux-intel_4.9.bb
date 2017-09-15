
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "750f82327361c57162741f6b6326f142a4c1367e"
SRCREV_meta ?= "6acae6f7200af17b3c2be5ecab2cffdc59a02b35"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
