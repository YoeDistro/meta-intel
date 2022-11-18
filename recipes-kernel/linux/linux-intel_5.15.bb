require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
                    "
SRC_URI:append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

KBRANCH = "5.15/linux"
KMETA_BRANCH = "yocto-5.15"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "5.15.71"
SRCREV_machine ?= "7f0de68338ad7f625a7137278bf1d0a5edcbab45"
SRCREV_meta ?= "50e12441b95837206d02d3ecfd17b1c562759d76"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

# We've 8b766b0f8eece backported from v5.19 to linux-intel v5.15 kernel
# https://github.com/torvalds/linux/commit/8b766b0f8eece55155146f7628610ce54a065e0f
# It drops 'CONFIG_FB_BOOT_VESA_SUPPORT' config option which would result in a warning with 5.15 y-k-c.
# Suppress the harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"
