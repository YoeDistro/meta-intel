require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                    "
KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.2.0"
SRCREV_machine ?= "da96c5fe8e1222da818618e15dbc956022228306"
SRCREV_meta ?= "aeb1ad4a2a72e2acf206fac02da0551fbd29b9d5"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v6.2-linux-(?P<pver>(\d+)T(\d+)Z)$"
