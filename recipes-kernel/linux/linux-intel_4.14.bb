require linux-intel.inc

KBRANCH = "4.14/base"
KMETA_BRANCH = "yocto-4.14"

# Fix for 32-bit perf issue. Remove when patch is backported to 4.14.
SRC_URI_append = " file://0001-perf-x86-32-explicitly-include-errno.h.patch \
                   file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
                   file://0001-perf-tools-Add-Python-3-support.patch \
                   "

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.14.138"
SRCREV_machine ?= "3f695bb28f52f6317f034efbf42b052c66dee0e6"
SRCREV_meta ?= "bc35d5bd224ab031440a69f9806d839db382f05c"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
