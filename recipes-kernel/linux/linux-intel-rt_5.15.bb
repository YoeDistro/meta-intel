require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME", True) == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KBRANCH = "5.15/preempt-rt"
KMETA_BRANCH = "yocto-5.15"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.15.71"
SRCREV_machine ?= "e29405e36bfbda7ace776548de802b76f61b80d9"
SRCREV_meta ?= "50e12441b95837206d02d3ecfd17b1c562759d76"

LINUX_KERNEL_TYPE = "preempt-rt"

# We've 8b766b0f8eece backported from v5.19 to linux-intel v5.15 kernel
# https://github.com/torvalds/linux/commit/8b766b0f8eece55155146f7628610ce54a065e0f
# It drops 'CONFIG_FB_BOOT_VESA_SUPPORT' config option which would result in a warning with 5.15 y-k-c.
# Suppress the harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"
