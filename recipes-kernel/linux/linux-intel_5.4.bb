require linux-intel.inc

KBRANCH = "5.4/yocto"
KMETA_BRANCH = "yocto-5.4"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.4.193"
SRCREV_machine ?= "61e96c8dd65a47ee01b18cb054cbc97e8eb76e30"
SRCREV_meta ?= "318db1080fdd1e26567b6ba679310adad84c173a"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

# Kernel config 'CONFIG_GPIO_LYNXPOINT' goes by a different name 'CONFIG_PINCTRL_LYNXPOINT' in
# linux-intel 5.4 specifically. This causes a warning during kernel config audit. Suppress the
# harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"
