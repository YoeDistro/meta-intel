require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
                    "
SRC_URI:append = " file://0001-v5.19-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

KMETA_BRANCH = "yocto-5.19"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-mainline-tracking-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "5.19.0"
SRCREV_machine ?= "20024f44f7ef62c5c4bd030a858e6b44ea2541ec"
SRCREV_meta ?= "0cba9aa40445bf59bfa651e1bd43a31c2ca7d524"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
