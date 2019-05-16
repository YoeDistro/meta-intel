require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/meta-intel-compat-kernel.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-intel:"

SRC_URI = " \
           git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA} \
           file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
          "
SRC_URI_append_core2-32-intel-common = " file://disable_skylake_sound.cfg"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

KBRANCH = "base"
KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.1.0"
SRCREV_machine ?= "abcfe412cc5140d20a592965744c050fd3cc3d19"
SRCREV_meta ?= "b8bce20786a4063e5f053036e5236d82c8fc3d91"

LINUX_VERSION_EXTENSION ?= "-mainline-tracking-${LINUX_KERNEL_TYPE}"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE ?= "(intel-corei7-64|intel-core2-32)"

# Functionality flags
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
