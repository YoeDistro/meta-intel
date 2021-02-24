require linux-intel.inc

KBRANCH = "5.10/yocto"
KMETA_BRANCH = "yocto-5.10"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
                   file://objtool-fix-segfault-with-clang.patch \
                   "

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.10.14"
SRCREV_machine ?= "2b705cc891cb47bef099726156acaeaf72ba2006"
SRCREV_meta ?= "513e9332a0092795b3b29eec0030b462ae4c8b6e"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
