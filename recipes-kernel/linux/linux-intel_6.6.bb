require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
                    "
KBRANCH = "6.6/linux"
KMETA_BRANCH = "yocto-6.6"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.6.23"
SRCREV_machine ?= "lts-v6.6.23-linux-240407T055600Z"
SRCREV_meta ?= "eb283ea577df80542d48f0c498365960b4c4ecd9"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^lts-(?P<pver>v6.6.(\d+)-linux-(\d+)T(\d+)Z)$"
