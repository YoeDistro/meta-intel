require linux-intel.inc

SRC_URI:prepend = "git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
                    "
SRC_URI:append = " file://0001-v5.19-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

KBRANCH = "6.1/linux"
KMETA_BRANCH = "yocto-6.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "6.1.8"
SRCREV_machine ?= "0b3cff71dc868b577c8b32c0f2e01d41ae124cf3"
SRCREV_meta ?= "6c723fc4d35fbdb277e28d0072f8117e43679528"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

UPSTREAM_CHECK_GITTAGREGEX = "^lts-(?P<pver>v6.1.(\d+)-linux-(\d+)T(\d+)Z)$"
