
require linux-intel.inc

KBRANCH = "4.9/yocto/base"
KMETA_BRANCH = "yocto-4.9"

SRC_URI_append = " \
                   file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
                   "

LINUX_VERSION ?= "4.9.195"
SRCREV_machine ?= "f1bbb927291ee709a47bb3124aa53e819e936e7b"
SRCREV_meta ?= "01eb2cf9a6626c65a4c117249d03a002f683222d"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
