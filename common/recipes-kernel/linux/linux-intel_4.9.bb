
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "22a3e397584619839ec645c7378dc40bdd5ad2d1"
SRCREV_meta ?= "299f12a06ca1d6fd90b24450dae3b9f257a536be"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
