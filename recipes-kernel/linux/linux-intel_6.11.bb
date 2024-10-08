require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                  "
SRC_URI:append = " file://0001-6.11-lib-build_OID_registry-fix-reproducibility-issues.patch \
                  "
KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.11"
SRCREV_machine ?= "9a961f4b0a6a2655ef37e86b8588f2946b427650"
SRCREV_meta ?= "5b42ba3f7fd0bb7b15d194871152f3b1a30f3926"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/intel-npu/intel-npu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v6.11-linux-(?P<pver>(\d+)T(\d+)Z)$"
