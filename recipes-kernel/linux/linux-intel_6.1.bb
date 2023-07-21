require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
                    "
KBRANCH = "6.1/linux"
KMETA_BRANCH = "yocto-6.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.1.38"
SRCREV_machine ?= "11e606448ad35e3c3041a363f4231d3676c5a226"
SRCREV_meta ?= "d62bfbd59e3f6f9b6fa6e396df2d9c7f1abb80ed"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^lts-(?P<pver>v6.1.(\d+)-linux-(\d+)T(\d+)Z)$"
