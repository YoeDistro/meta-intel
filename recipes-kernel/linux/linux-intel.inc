require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/meta-intel-compat-kernel.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-intel:"

KERNEL_CONFIG_URI ?= "git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"

SRC_URI = " \
           ${KERNEL_CONFIG_URI} \
           file://0001-vt-conmakehash-improve-reproducibility.patch \
           file://0001-lib-build_OID_registry-fix-reproducibility-issues.patch \
           file://fix-perf-reproducibility.patch \
	  "

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

COMPATIBLE_MACHINE ?= "(intel-corei7-64)"

KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"
