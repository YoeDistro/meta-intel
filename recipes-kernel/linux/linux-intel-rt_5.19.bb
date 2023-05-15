require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                    "
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
SRCREV_machine ?= "3abe09b48ca3c13b50c78d3b7b0d7ce668211a70"
SRCREV_meta ?= "f5d4c109d6de04005def04c3a06f053ae0c397ad"

LINUX_KERNEL_TYPE = "preempt-rt"
UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v5.19-(?P<pver>rt(\d+)-preempt-rt-(\d+)T(\d+)Z)$"
