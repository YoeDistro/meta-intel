require linux-intel.inc

KBRANCH = "5.4/yocto"
KMETA_BRANCH = "yocto-5.4"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.4.44"
SRCREV_machine ?= "010689ad67b92133d1f9a3cf895a93e4c1800f59"
SRCREV_meta ?= "627191aa87d971e153f95beac4d9e45aea0e9b65"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

# Kernel config 'CONFIG_GPIO_LYNXPOINT' goes by a different name 'CONFIG_PINCTRL_LYNXPOINT' in
# linux-intel 5.4 specifically. This cause warning during kernel config audit. So suppress the
# harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"
