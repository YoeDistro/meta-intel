require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                    "
SRC_URI:append = " file://0001-v5.19-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME", True) == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KMETA_BRANCH = "yocto-5.19"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "5.19.0"
SRCREV_machine ?= "7de7f243440a51b6b996fc462f7fd7a546c175d2"
SRCREV_meta ?= "0cba9aa40445bf59bfa651e1bd43a31c2ca7d524"

LINUX_KERNEL_TYPE = "preempt-rt"
