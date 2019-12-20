require linux-intel.inc

KBRANCH = "5.4/yocto"
KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.4.2"
SRCREV_machine ?= "dafda1446f5af2f71c97a41f279bde06acda37c5"
SRCREV_meta ?= "0a2379e9aa2bf7ac86f0b83cd4e016d6139e7384"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
