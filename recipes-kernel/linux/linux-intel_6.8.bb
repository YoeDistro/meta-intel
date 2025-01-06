require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                  "
SRC_URI:append = " file://0001-6.8-vt-conmakehash-improve-reproducibility.patch \
                   file://0001-6.8-lib-build_OID_registry-fix-reproducibility-issues.patch \
                  "

KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.8"
SRCREV_machine ?= "4b78f19d1c451c3738b10d489e67977e97036a7f"
SRCREV_meta ?= "d6379f226f25136d9292f09cd7c11921f0bbcd9b"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/intel-npu/intel-npu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v6.8-linux-(?P<pver>(\d+)T(\d+)Z)$"
