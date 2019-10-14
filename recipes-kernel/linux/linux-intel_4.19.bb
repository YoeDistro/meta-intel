require linux-intel.inc

KBRANCH = "4.19/base"
KMETA_BRANCH = "yocto-4.19"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.19.73"
SRCREV_machine ?= "ca05e9cd64b5afbd698fa2707be4d2602890b849"
SRCREV_meta ?= "a7cb57afb9fb9787079c28a1028d797632105e56"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
