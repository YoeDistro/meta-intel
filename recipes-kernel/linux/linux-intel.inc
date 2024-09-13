require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/meta-intel-compat-kernel.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-intel:"

KERNEL_CONFIG_URI ?= "git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"

SRC_URI = " \
           ${KERNEL_CONFIG_URI} \
           file://fix-perf-reproducibility.patch \
           file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
           file://0002-mconf-fix-output-of-cflags-and-libraries.patch \
	  "

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "0"

COMPATIBLE_MACHINE ?= "(intel-corei7-64)"

KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"
