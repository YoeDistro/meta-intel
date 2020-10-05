require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME", True) == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
"

KBRANCH = "5.4/preempt-rt"
KMETA_BRANCH = "yocto-5.4"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.4.66"
SRCREV_machine ?= "01485c410c9539722b37c6432e3ad0616d2a1750"
SRCREV_meta ?= "8a4128085a87d8ecb8a7a5c1f7e7f37cc5d4d119"

LINUX_KERNEL_TYPE = "preempt-rt"

# Kernel config 'CONFIG_GPIO_LYNXPOINT' goes by a different name 'CONFIG_PINCTRL_LYNXPOINT' in
# linux-intel 5.4 specifically. This cause annoying warning during kernel config audit. So suppress the
# harmless warning for now.
KCONF_BSP_AUDIT_LEVEL = "0"
