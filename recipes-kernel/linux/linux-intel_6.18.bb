require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
                    "
KBRANCH = "6.18/linux"
KMETA_BRANCH = "yocto-6.18"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.18.15"
SRCREV_machine ?= "16b46595039e12fe48cef4b96a78e3e8061508e5"
SRCREV_meta ?= "bc293057a14f94dd50838a399bd8758a6766b877"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/intel-npu/intel-npu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^lts-(?P<pver>v6.18.(\d+)-linux-(\d+)T(\d+)Z)$"
