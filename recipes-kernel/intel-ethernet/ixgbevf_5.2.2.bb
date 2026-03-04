SUMMARY = "ixgbevf kernel driver for Intel Magnolia Park 10GbE"
DESCRIPTION = "This virtual function driver supports kernel versions 2.6.x and newer \
This driver supports 82599, X540, X550, and X552-based virtual function devices \
that can only be activated on kernels that support SR-IOV. \
SR-IOV requires the correct platform and OS support. \
The guest OS loading this driver must support MSI-X interrupts."

HOMEPAGE = "https://github.com/intel/ethernet-linux-ixgbevf"
SECTION = "kernel/network"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/${BP}/COPYING;md5=a216b4192dc6b777b6f0db560e9a8417"

SRC_URI = "https://github.com/intel/ethernet-linux-ixgbevf/releases/download/v${PV}/${BP}.tar.gz \
           "

SRC_URI[sha256sum] = "8e0950ccfc19515f4fcb0dd347f776ed49aa6781ee3a177511d0fb7ac4d25e9a"

UPSTREAM_CHECK_URI = "https://github.com/intel/ethernet-linux-ixgbevf/releases"
UPSTREAM_CHECK_REGEX = "ixgbevf-(?P<pver>\d+(\.\d+)+)\.tar\.gz"

CVE_PRODUCT = "linux:linux_kernel_ixgbe"

S = "${UNPACKDIR}/${BP}/src"

EXTRA_OEMAKE = 'KSRC="${STAGING_KERNEL_DIR}" KOBJ="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'

KERNEL_MODULE_AUTOLOAD:append:intel-core2-32 = " ixgbevf"
KERNEL_MODULE_AUTOLOAD:append:intel-corei7-64 = " ixgbevf"

inherit module

do_install:append () {
        # Install scripts/set_irq_affinity
        install -d      ${D}${sysconfdir}/network
        install -m 0755 ${S}/../scripts/set_irq_affinity  ${D}${sysconfdir}/network

        rm -rf ${D}${prefix}/man
}

PACKAGES += "${PN}-script"

FILES:${PN}-script += "${sysconfdir}/network/set_irq_affinity"

CVE_STATUS[CVE-2015-1142857] = "fixed-version: Fixed from version 4.4-rc1"

EXCLUDE_FROM_WORLD = "1"
