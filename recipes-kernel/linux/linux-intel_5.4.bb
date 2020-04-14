require linux-intel.inc

KBRANCH = "5.4/yocto"
KMETA_BRANCH = "yocto-5.4"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.4.28"
SRCREV_machine ?= "4726da64050d85576d3ea73ea9578865e3676f41"
SRCREV_meta ?= "b8c82ba37370e4698ff0c42f3e54b8b4f2fb4ac0"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
