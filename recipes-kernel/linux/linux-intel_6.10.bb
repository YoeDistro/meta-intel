require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                  "
SRC_URI:append = " file://0001-6.10-vt-conmakehash-improve-reproducibility.patch \
                   file://0001-6.10-lib-build_OID_registry-fix-reproducibility-issues.patch \
                  "
KMETA_BRANCH = "yocto-6.10"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.10"
SRCREV_machine ?= "37b6bf1243b894e0da6e640e589d479f3208f5ae"
SRCREV_meta ?= "e4d2ade39f231ea279f19298c008ba48f4a202e5"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/intel-npu/intel-npu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v6.10-linux-(?P<pver>(\d+)T(\d+)Z)$"
