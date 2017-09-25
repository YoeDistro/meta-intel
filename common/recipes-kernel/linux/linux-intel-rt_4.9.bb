
require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

KBRANCH = "base-rt"
SRCREV_machine ?= "fc08e0615edccfb4d297c07531451cb7905fb4f4"
SRCREV_meta ?= "3ddaed3671efc2936efbebf4c5216e11b9dfd55d"

LINUX_KERNEL_TYPE = "preempt-rt"
