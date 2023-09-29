require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                  "
KMETA_BRANCH = "yocto-6.4"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.4.0"
SRCREV_machine ?= "7f4a11e3f6805a0169c4955415b2c7aac9e90c1a"
SRCREV_meta ?= "7a654f3f799146d1a9bfbd78ddebdc53510951e9"

LINUX_KERNEL_TYPE = "preempt-rt"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v6.4-(?P<pver>rt(\d+)-preempt-rt-(\d+)T(\d+)Z)$"
