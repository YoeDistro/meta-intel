
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

LINUX_VERSION ?= "4.9.146"
SRCREV_machine ?= "bc2342621494c9c1b28ce29110fb95c80a17a1be"
SRCREV_meta ?= "bae5cc4e973f60baa35ed227ba0c3bdda4d16e87"

LINUX_KERNEL_TYPE = "preempt-rt"
