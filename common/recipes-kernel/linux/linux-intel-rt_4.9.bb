
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
SRCREV_machine ?= "3ccf317abeba2f5219fdb2b8055599c263b4b8f2"
SRCREV_meta ?= "e8095d45e15f02ffc953312bb41a57554b25a439"

LINUX_KERNEL_TYPE = "preempt-rt"
