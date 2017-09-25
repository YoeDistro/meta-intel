
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "2d533948da0a7c53a0a662d3764be166ba996d93"
SRCREV_meta ?= "3ddaed3671efc2936efbebf4c5216e11b9dfd55d"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
