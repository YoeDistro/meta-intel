require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/meta-intel-compat-kernel.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-intel:"

KERNEL_CONFIG_URI ?= "git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"

SRC_URI = " \
           git://github.com/intel/linux-intel-lts.git;protocol=https;name=machine;branch=${KBRANCH}; \
           ${KERNEL_CONFIG_URI} \
           file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
           file://0001-vt-conmakehash-improve-reproducibility.patch \
           file://0001-lib-build_OID_registry-fix-reproducibility-issues.patch \
	  "

LINUX_VERSION_EXTENSION ??= "-intel-pk-${LINUX_KERNEL_TYPE}"

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

COMPATIBLE_MACHINE ?= "(intel-corei7-64)"

KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"
