
require linux-intel.inc

KBRANCH = "4.9/yocto/base"
KMETA_BRANCH = "yocto-4.9"

LINUX_VERSION ?= "4.9.77"
SRCREV_machine ?= "d7f2f7269253c2be1c1000d49d2bded4ba70407a"
SRCREV_meta ?= "a2dfb1610d9dad34652a3c27c6c9d8751ed67af6"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
