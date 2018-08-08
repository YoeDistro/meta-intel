
require linux-intel.inc

KBRANCH = "4.9/yocto/base"
KMETA_BRANCH = "yocto-4.9"

SRC_URI_append = " file://0001-ver_linux-point-to-usr-bin-awk.patch"

LINUX_VERSION ?= "4.9.116"
SRCREV_machine ?= "521a610f0eab91e6f8c36c88916338e2cb36aa1c"
SRCREV_meta ?= "5e993963afb54bdc82a02077c29ecdbc0b12368e"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
