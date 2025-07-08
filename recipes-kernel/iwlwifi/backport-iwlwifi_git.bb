SUMMARY = "Intel Wireless LinuxCore kernel driver"
DESCRIPTION = "Intel Wireless LinuxCore kernel driver"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

REQUIRED_DISTRO_FEATURES = "wifi"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit module features_check

# For some iwfwifi LinuxCore supported wireless chips, the best/latest
# firmware blobs are found in the iwlwifi's linux-firmware.git fork.
#
# See: https://wireless.wiki.kernel.org/en/users/drivers/iwlwifi/core_release
#
# When updating this recipe, ensure that the proper firmware is included from
# either the linux-firmware or iwlwifi-firmware repos.

PV = "96"
SRCREV = "d16e74cc1c76ffbfc335153daa86660f199a90da"

SRC_URI = " \
           git://git.kernel.org/pub/scm/linux/kernel/git/iwlwifi/backport-iwlwifi;branch=release/core${PV} \
           file://0001-Makefile.real-skip-host-install-scripts.patch \
           file://iwlwifi.conf \
          "


EXTRA_OEMAKE = "INSTALL_MOD_PATH=${D} KLIB_BUILD=${KBUILD_OUTPUT}"

do_configure() {
	CC=gcc CFLAGS= LDFLAGS= make defconfig-iwlwifi-public KLIB_BUILD=${KBUILD_OUTPUT}
}

MODULES_INSTALL_TARGET = "install"

do_install:append() {
	## install configs and service scripts
	install -d ${D}${sysconfdir}/modprobe.d
	install -m 0644 ${UNPACKDIR}/iwlwifi.conf ${D}${sysconfdir}/modprobe.d
}

RDEPENDS:${PN} = "linux-firmware-iwlwifi"

FILES:${PN} += "${sysconfdir}/modprobe.d/iwlwifi.conf"

KERNEL_MODULE_AUTOLOAD:append:core2-32-intel-common = " iwlwifi"
KERNEL_MODULE_AUTOLOAD:append:corei7-64-intel-common = " iwlwifi"

KERNEL_MODULE_PACKAGE_PREFIX = "backport-iwlwifi"

EXCLUDE_FROM_WORLD = "1"
