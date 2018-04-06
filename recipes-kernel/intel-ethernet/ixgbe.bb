SUMMARY="ixgbe kernel driver for Intel Magnolia Park 10GbE"
DESCRIPTION="Intel 10-Gbps Ethernet driver for Magnolia Park"
AUTHOR = "Ong Boon Leong"
HOMEPAGE = "http://www.intel.com/network/connectivity/products/server_adapters.htm"
SECTION = "kernel/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "5.3.6"
PR = "r0"

SRC_URI = "https://sourceforge.net/projects/e1000/files/ixgbe%20stable/${PV}/ixgbe-${PV}.tar.gz \
           file://0001-ixgbe-src-Makefile-change-make-install-to-make.patch \
           file://0001-ixgbe-skip-host-depmod.patch \
           "

SRC_URI[md5sum] = "d6816f2b6b8bddfa2d78267f84770d91"
SRC_URI[sha256sum] = "6ba26de1bb9b55b92f5f54c6c7b25f837323ec3322a6ee54e882c8e54e6d0eaa"

S = "${WORKDIR}/${PN}-${PV}/src"
SCRIPT_DIR = "${WORKDIR}/${PN}-${PV}/scripts"

EXTRA_OEMAKE='KSRC="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" \
              BUILD_ARCH="${TARGET_ARCH}" PREFIX="${D}" \
              SYSTEM_MAP_FILE="${STAGING_KERNEL_BUILDDIR}/System.map-${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'

KERNEL_MODULE_AUTOLOAD_append_intel-core2-32 = " ixgbe"
KERNEL_MODULE_AUTOLOAD_append_intel-corei7-64 = " ixgbe"

inherit module

do_install_append () {
        # Install scripts/set_irq_affinity
        install -d      ${D}/etc/network
        install -m 0755 ${SCRIPT_DIR}/set_irq_affinity  ${D}/etc/network
}

#SSTATE_DUPWHITELIST += "${STAGING_DIR_HOST}/lib/modules/${KERNEL_VERSION}/"

PACKAGES += "${PN}-script"

FILES_${PN}-script += "/etc/network/set_irq_affinity"

#Ignore "ERROR: QA Issue: ixgbe: Files/directories were installed but not shipped"
INSANE_SKIP_${PN} = "installed-vs-shipped"
