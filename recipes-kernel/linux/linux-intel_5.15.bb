require linux-intel.inc

KBRANCH = "5.15/linux"
KMETA_BRANCH = "yocto-5.15"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.15.43"
SRCREV_machine ?= "1ec5959a8f6dbfbb47057317bc935924cd8d6977"
SRCREV_meta ?= "ea948a0983d7b7820814e5bce4eda3079201bd95"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
