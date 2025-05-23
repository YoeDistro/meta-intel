require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
                    "
SRC_URI:append = " file://0001-6.12-lib-build_OID_registry-fix-reproducibility-issues.patch \
                  "
KBRANCH = "6.12/linux"
KMETA_BRANCH = "yocto-6.12"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.12.27"
SRCREV_machine ?= "9e2f92c31c6353101755c83670232c94e0c07ddc"
SRCREV_meta ?= "da71eb19ceab34a7a6ff4284f1580e043c870168"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc \
                            features/intel-npu/intel-npu.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^lts-(?P<pver>v6.12.(\d+)-linux-(\d+)T(\d+)Z)$"
