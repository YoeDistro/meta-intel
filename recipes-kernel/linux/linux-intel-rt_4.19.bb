require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

KBRANCH = "4.19/preempt-rt"
KMETA_BRANCH = "yocto-4.19"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.19.59"
SRCREV_machine ?= "d6dc301c71aea6098e5e19ef3f265d0ba9981167"
SRCREV_meta ?= "960be4218436fbbb3500e019f7abf02fa94e6aac"

LINUX_KERNEL_TYPE = "preempt-rt"
