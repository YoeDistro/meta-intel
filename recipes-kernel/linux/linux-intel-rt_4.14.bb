require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

KBRANCH = "4.14/preempt-rt"
KMETA_BRANCH = "yocto-4.14"

# Fix for 32-bit perf issue. Remove when patch is backported to 4.14.
SRC_URI_append = " file://0001-perf-x86-32-explicitly-include-errno.h.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.14.34"
SRCREV_machine ?= "c40b54f7eae42ac6ef28b324900c1c99e719e449"
SRCREV_meta ?= "245d701df6c3691a078a268eff54009959beb842"

LINUX_KERNEL_TYPE = "preempt-rt"
