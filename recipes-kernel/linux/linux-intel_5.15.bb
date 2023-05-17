require linux-intel.inc

KBRANCH = "5.15/linux"
KMETA_BRANCH = "yocto-5.15"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.15.107"
SRCREV_machine ?= "95c83b378e090dcfd0b6371f5ebf68fc063fdb17"
SRCREV_meta ?= "9b702613899f074ea3bba478e90b2b71e31388e8"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

# We've 8b766b0f8eece backported from v5.19 to linux-intel v5.15 kernel
# https://github.com/torvalds/linux/commit/8b766b0f8eece55155146f7628610ce54a065e0f
# It drops 'CONFIG_FB_BOOT_VESA_SUPPORT' config option which would result in a warning with 5.15 y-k-c.
# Suppress the harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"
