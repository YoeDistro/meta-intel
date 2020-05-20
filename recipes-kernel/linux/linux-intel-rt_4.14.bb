require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME", True) == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

KBRANCH = "4.14/preempt-rt"
KMETA_BRANCH = "yocto-4.14"

# Fix for 32-bit perf issue. Remove when patch is backported to 4.14.
SRC_URI_append = " file://0001-perf-x86-32-explicitly-include-errno.h.patch \
                file://0001-perf-tools-Add-Python-3-support.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "4.14.170"
SRCREV_machine ?= "fecd5d817dac0185ea48bab35c83844f2e720e75"
SRCREV_meta ?= "c8f030fdbf6046dede4b7ce6c94b5b24221c250e"

LINUX_KERNEL_TYPE = "preempt-rt"
