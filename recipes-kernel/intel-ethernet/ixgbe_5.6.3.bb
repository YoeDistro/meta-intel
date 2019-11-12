SUMMARY="ixgbe kernel driver for Intel Magnolia Park 10GbE"
DESCRIPTION="The ixgbe driver supports 82598- and 82599-based \
PCI Express* 10 Gigabit Network Connections."

HOMEPAGE = "https://sourceforge.net/projects/e1000/"
SECTION = "kernel/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${BP}/COPYING;md5=a216b4192dc6b777b6f0db560e9a8417"

SRC_URI = "https://sourceforge.net/projects/e1000/files/ixgbe%20stable/${PV}/${BP}.tar.gz \
           file://0001-ixgbe-skip-host-depmod.patch \
           "

SRC_URI[md5sum] = "990321b97c58b5cbf86e6b1e095d9cbf"
SRC_URI[sha256sum] = "d6a5129b7be0a25bed65843be37e4ac913deab4ca169b215521088e10eb03ef1"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/e1000/files/ixgbe%20stable/"
UPSTREAM_CHECK_REGEX = "ixgbe%20stable/(?P<pver>\d+(\.\d+)+)/"

CVE_PRODUCT = "linux:linux_kernel_ixgbe"

S = "${WORKDIR}/${BP}/src"
MODULES_INSTALL_TARGET = "install"

EXTRA_OEMAKE='KSRC="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'

KERNEL_MODULE_AUTOLOAD_append_intel-core2-32 = " ixgbe"
KERNEL_MODULE_AUTOLOAD_append_intel-corei7-64 = " ixgbe"

inherit module

do_install_append () {
        # Install scripts/set_irq_affinity
        install -d      ${D}${sysconfdir}/network
        install -m 0755 ${S}/../scripts/set_irq_affinity  ${D}${sysconfdir}/network

        rm -rf ${D}${prefix}/man
}

PACKAGES += "${PN}-script"

FILES_${PN}-script += "${sysconfdir}/network/set_irq_affinity"
