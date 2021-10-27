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
SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
                   "

KBRANCH = "5.10/preempt-rt"
KMETA_BRANCH = "yocto-5.10"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.10.59"
SRCREV_machine ?= "3ab3139402e6adc34f10481f1c1c60459bcd0a7e"
SRCREV_meta ?= "f8afd84b117f336477846b9e22178ebefb26c08d"

LINUX_KERNEL_TYPE = "preempt-rt"

# Following commit is backported from mainline 5.12-rc to linux-intel 5.10 kernel
# Commit: https://github.com/torvalds/linux/commit/26499e0518a77de29e7db2c53fb0d0e9e15be8fb
# In which 'CONFIG_DRM_GMA3600' config option is dropped.
# This causes warning during config audit. So suppress the harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"

# Disabling CONFIG_SND_SOC_INTEL_SKYLAKE for 32-bit, does not allow to set CONFIG_SND_SOC_INTEL_SST too, which
# causes config warning too.
KCONF_AUDIT_LEVEL_core2-32-intel-common = "0"
