require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                  "
SRC_URI:append = " file://0001-6.11-6.12-lib-build_OID_registry-fix-reproducibility-issues.patch \
                  "
KMETA_BRANCH = "yocto-6.12"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.12"
SRCREV_machine ?= "0f23779724ce8d6654abd0ef2a4167dd37d677c8"
SRCREV_meta ?= "b6eb84a3327f850c42300c394586fe58a57db72d"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/intel-npu/intel-npu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v6.12-linux-(?P<pver>(\d+)T(\d+)Z)$"
