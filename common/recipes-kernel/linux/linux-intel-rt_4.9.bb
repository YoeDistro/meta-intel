
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

LINUX_VERSION ?= "4.9.61"
SRCREV_machine ?= "530f7b58f610e99c0dc8f6d2e5695561b442f694"
SRCREV_meta ?= "4553798a3e73b0791f4d5065ec5ad4b45027914f"

LINUX_KERNEL_TYPE = "preempt-rt"
