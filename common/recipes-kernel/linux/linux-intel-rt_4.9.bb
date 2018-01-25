
require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

KBRANCH = "4.9/yocto/base-rt"
KMETA_BRANCH = "yocto-4.9"

LINUX_VERSION ?= "4.9.77"
SRCREV_machine ?= "ae1aee65fd27590eff2c308ad5661a13d023641d"
SRCREV_meta ?= "a2dfb1610d9dad34652a3c27c6c9d8751ed67af6"

LINUX_KERNEL_TYPE = "preempt-rt"
