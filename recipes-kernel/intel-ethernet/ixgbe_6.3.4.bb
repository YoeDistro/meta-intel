SUMMARY = "ixgbe kernel driver for Intel Magnolia Park 10GbE"
DESCRIPTION = "The ixgbe driver supports 82598- and 82599-based \
PCI Express* 10 Gigabit Network Connections."

HOMEPAGE = "https://github.com/intel/ethernet-linux-ixgbe"
SECTION = "kernel/network"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/${BP}/COPYING;md5=a216b4192dc6b777b6f0db560e9a8417"

SRC_URI = "https://github.com/intel/ethernet-linux-ixgbe/releases/download/v${PV}/${BP}.tar.gz \
           "

SRC_URI[sha256sum] = "1bb6a63bf2502bfd205ba55e48ef6486754b1823ed330cb103ac1a590544abe6"

UPSTREAM_CHECK_URI = "https://github.com/intel/ethernet-linux-ixgbe/releases"
UPSTREAM_CHECK_REGEX = "ixgbe-(?P<pver>\d+(\.\d+)+)\.tar\.gz"

CVE_PRODUCT = "linux:linux_kernel_ixgbe"

S = "${UNPACKDIR}/${BP}/src"

EXTRA_OEMAKE = ' KSRC="${STAGING_KERNEL_DIR}" KOBJ="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'

KERNEL_MODULE_AUTOLOAD:append:intel-core2-32 = " ixgbe"
KERNEL_MODULE_AUTOLOAD:append:intel-corei7-64 = " ixgbe"

inherit module

do_install:append () {
        # Install scripts/set_irq_affinity
        install -d      ${D}${sysconfdir}/network
        install -m 0755 ${S}/../scripts/set_irq_affinity  ${D}${sysconfdir}/network

        rm -rf ${D}${prefix}/man
}

PACKAGES += "${PN}-script"

FILES:${PN}-script += "${sysconfdir}/network/set_irq_affinity"

EXCLUDE_FROM_WORLD = "1"

CVE_STATUS[CVE-2015-1142857] = "fixed-version: Fixed from version 4.4-rc1"
