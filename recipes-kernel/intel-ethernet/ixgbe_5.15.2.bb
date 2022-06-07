SUMMARY="ixgbe kernel driver for Intel Magnolia Park 10GbE"
DESCRIPTION="The ixgbe driver supports 82598- and 82599-based \
PCI Express* 10 Gigabit Network Connections."

HOMEPAGE = "https://sourceforge.net/projects/e1000/"
SECTION = "kernel/network"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${BP}/COPYING;md5=a216b4192dc6b777b6f0db560e9a8417"

SRC_URI = "https://sourceforge.net/projects/e1000/files/ixgbe%20stable/${PV}/${BP}.tar.gz \
           "

SRC_URI[sha256sum] = "ca31fa581d6ddabee706b01fc59a5bbb841291fc0a392183adc9a80796eb9d04"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/e1000/files/ixgbe%20stable/"
UPSTREAM_CHECK_REGEX = "ixgbe%20stable/(?P<pver>\d+(\.\d+)+)/"

CVE_PRODUCT = "linux:linux_kernel_ixgbe"

S = "${WORKDIR}/${BP}/src"

EXTRA_OEMAKE='KSRC="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'

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

# This was fixed in kernel v4.4
CVE_CHECK_IGNORE += "CVE-2015-1142857"
