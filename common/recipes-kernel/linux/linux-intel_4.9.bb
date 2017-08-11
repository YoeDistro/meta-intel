
require linux-intel.inc

KBRANCH = "base"
SRCREV_machine ?= "e3650c213b06488500c005f3075407fe1218c51b"
SRCREV_meta ?= "e8095d45e15f02ffc953312bb41a57554b25a439"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
