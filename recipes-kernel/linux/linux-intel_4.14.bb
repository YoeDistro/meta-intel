require linux-intel.inc

KBRANCH = "4.14/base"
KMETA_BRANCH = "yocto-4.14"

# Fix for 32-bit perf issue. Remove when patch is backported to 4.14.
SRC_URI_append = " file://0001-perf-x86-32-explicitly-include-errno.h.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.14.59"
SRCREV_machine ?= "a36b9969b6b2c9321d9c6e907b47211be0f4b99c"
SRCREV_meta ?= "8a3be7d783a39b56796e1d5197cb9f757e38f22f"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
