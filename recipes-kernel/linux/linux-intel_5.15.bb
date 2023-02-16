require linux-intel.inc

KBRANCH = "5.15/linux"
KMETA_BRANCH = "yocto-5.15"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.15.85"
SRCREV_machine ?= "c256f934aefa78ec001067313a76a4a382ac59a6"
SRCREV_meta ?= "78c4410c172946903e35ba8cebf1cf90fad09b5a"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

# We've 8b766b0f8eece backported from v5.19 to linux-intel v5.15 kernel
# https://github.com/torvalds/linux/commit/8b766b0f8eece55155146f7628610ce54a065e0f
# It drops 'CONFIG_FB_BOOT_VESA_SUPPORT' config option which would result in a warning with 5.15 y-k-c.
# Suppress the harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"
