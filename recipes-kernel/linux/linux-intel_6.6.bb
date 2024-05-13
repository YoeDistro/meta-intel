require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
                    "
KBRANCH = "6.6/linux"
KMETA_BRANCH = "yocto-6.6"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.6.25"
SRCREV_machine ?= "lts-v6.6.25-linux-240415T215440Z"
SRCREV_meta ?= "c3d1322fb6ff68cdcf4d7a3c1140d81bfdc1320a"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/iommu/iommu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^lts-(?P<pver>v6.6.(\d+)-linux-(\d+)T(\d+)Z)$"
