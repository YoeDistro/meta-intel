require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                  "
KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.7-rc3"
SRCREV_machine ?= "0111e14b36a9f12753cfb40afaa1dbb04ffdebbb"
SRCREV_meta ?= "00bbff3018f3d13781b2e9da6b3adddb4f1dc6fb"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v6.7-rc3-linux-(?P<pver>(\d+)T(\d+)Z)$"
