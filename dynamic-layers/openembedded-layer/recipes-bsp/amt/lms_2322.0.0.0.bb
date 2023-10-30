SUMMARY = "Intel(R) Local Managability Service"
DESCRIPTION = "Intel Local Manageability Service allows applications \
to access the Intel Active Management Technology (AMT) firmware via \
the Intel Management Engine Interface (MEI)."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=2ee41112a44fe7014dce33e26468ba93"

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

COMPATIBLE_HOST:libc-musl = "null"

inherit cmake systemd features_check

DEPENDS = "metee ace xerces-c libnl libxml2 glib-2.0 glib-2.0-native pkgconfig-native"

EXTRA_OECMAKE += "-DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3"

# Enable either connman or networkmanager or none but not both.
PACKAGECONFIG ??= "connman"
PACKAGECONFIG[connman]        = "-DNETWORK_CN=ON, -DNETWORK_CN=OFF, connman"
PACKAGECONFIG[networkmanager] = "-DNETWORK_NM=ON, -DNETWORK_NM=OFF, networkmanager"

REQUIRED_DISTRO_FEATURES= "systemd"

FILES:${PN} += "${datadir}/dbus-1/system-services/*.service"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE:${PN} = "lms.service"

SRC_URI = "git://github.com/intel/lms.git;branch=master;protocol=https \
           file://fix-gcc13.patch \
           file://0001-UNS-CMakeLists.txt-migrate-udev-rules-to-usr.patch \
           "
SRCREV = "cbc1e1019cf5dc2d03a3b722ee07fbfb9a90f2d1"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${B}/UNS/lms.service ${D}${systemd_system_unitdir}
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${S}/UNS/linux_scripts/70-mei-wdt.rules ${D}${sysconfdir}/udev/rules.d/70-mei-wdt.rules
}

RDEPENDS:${PN} += "ace"

# This CVE is for Lan Management System software and not this lms.
CVE_CHECK_IGNORE += "CVE-2018-1000535"
