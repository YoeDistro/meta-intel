require linux-intel.inc

KBRANCH = "4.14/base"
KMETA_BRANCH = "yocto-4.14"

# Fix for 32-bit perf issue. Remove when patch is backported to 4.14.
SRC_URI_append = " file://0001-perf-x86-32-explicitly-include-errno.h.patch \
                   file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
                   "

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.14.78"
SRCREV_machine ?= "56f15146cf0573a64a02f6d0ed2f002d4e1037d5"
SRCREV_meta ?= "6a3254e7b370cbb86c1f73379dcf38885c1c69e0"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
