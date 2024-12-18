require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                  "
SRC_URI:append = " file://0001-6.11-6.12-lib-build_OID_registry-fix-reproducibility-issues.patch \
                  "
KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.11"
SRCREV_machine ?= "8bf2da4f731bd973d5a91a31e71879f40de48a7e"
SRCREV_meta ?= "07f0dd688718f2b9dc66488288542955841b5e49"

LINUX_KERNEL_TYPE = "preempt-rt"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/intel-npu/intel-npu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-pre-prod-v6.11-rt7-preempt-rt-(?P<pver>(\d+)T(\d+)Z)$"
