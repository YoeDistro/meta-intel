require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/meta-intel-compat-kernel.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-intel:"

SRC_URI = " \
           git://github.com/intel/mainline-tracking.git;protocol=https;name=machine;nobranch=1; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA} \
           file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
           file://libtraceevent-fix-build-with-binutils-25.patch \
          "
SRC_URI_append_core2-32-intel-common = " file://disable_skylake_sound.cfg"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

KMETA_BRANCH = "master"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.6-rc3"
SRCREV_machine ?= "f8788d86ab28f61f7b46eb6be375f8a726783636"
SRCREV_meta ?= "e31896120dc72c1f35fa9e65a031d672cb67497d"

LINUX_VERSION_EXTENSION ?= "-mainline-tracking-${LINUX_KERNEL_TYPE}"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE ?= "(intel-corei7-64|intel-core2-32)"

# Functionality flags
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
