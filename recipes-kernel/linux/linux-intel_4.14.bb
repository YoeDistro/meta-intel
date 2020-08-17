require linux-intel.inc

KBRANCH = "4.14/base"
KMETA_BRANCH = "yocto-4.14"

# Fix for 32-bit perf issue. Remove when patch is backported to 4.14.
SRC_URI_append = " file://0001-perf-x86-32-explicitly-include-errno.h.patch \
                   file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
                   file://0001-perf-tools-Add-Python-3-support.patch \
                   "

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.14.188"
SRCREV_machine ?= "6fda79a99bf63d5f59dee82da7269c2f67597b7b"
SRCREV_meta ?= "d421224de85af341b8c67bffb29e234c44bfe1f2"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
