require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                    "
SRC_URI:append = " file://0001-v5.19-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

KMETA_BRANCH = "yocto-5.19"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "5.19.0"
SRCREV_machine ?= "51b9920cab3c5ee38b2c0cd5c7509ce005241b72"
SRCREV_meta ?= "f5d4c109d6de04005def04c3a06f053ae0c397ad"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^mainline-tracking-v5.19-linux-(?P<pver>(\d+)T(\d+)Z)$"
