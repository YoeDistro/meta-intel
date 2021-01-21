require linux-intel.inc

KBRANCH = "5.10/yocto"
KMETA_BRANCH = "yocto-5.10"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.10.1"
SRCREV_machine ?= "b8dd2066fae53c1bba0c7a6c73a08e137d5fca29"
SRCREV_meta ?= "47c7a3148a4d7653cec536ba202b25148d1952ad"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
