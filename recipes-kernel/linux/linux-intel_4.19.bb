require linux-intel.inc

KBRANCH = "4.19/base"
KMETA_BRANCH = "yocto-4.19"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.19.68"
SRCREV_machine ?= "1e852a3597a92ce329cf55a3c86a1c2cae3afc6f"
SRCREV_meta ?= "e73568c48e08a0cefa34a689e2f06f60abd17aec"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
