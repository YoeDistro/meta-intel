SUMMARY = "Linux thermal daemon"

DESCRIPTION = "Thermal Daemon is a Linux daemon used to prevent the \
overheating of platforms. This daemon monitors temperature and applies \
compensation using available cooling methods."

HOMEPAGE = "https://github.com/01org/thermal_daemon"

DEPENDS = "dbus dbus-glib dbus-glib-native libxml2 glib-2.0 glib-2.0-native"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','',d)}"
DEPENDS_append_libc-musl = " argp-standalone"
DEPENDS_append_toolchain-clang = " openmp"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea8831610e926e2e469075b52bf08848"

SRC_URI = "https://github.com/01org/thermal_daemon/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "d0cdba81b75d8dd304c10bf4663fb201"
SRC_URI[sha256sum] = "c63ae1b031f2b4ce037441e7e9910d05405b540fe6668e156d32c56d5dfa7492"

S = "${WORKDIR}/thermal_daemon-${PV}"

inherit pkgconfig autotools systemd

FILES_${PN} += "${datadir}/dbus-1/system-services/*.service"

SYSTEMD_SERVICE_${PN} = "thermald.service"

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

CONFFILES_${PN} = " \
                   ${sysconfdir}/thermald/thermal-conf.xml \
                   ${sysconfdir}/thermald/thermal-cpu-cdev-order.xml \
                  "

UPSTREAM_CHECK_URI = "https://github.com/01org/thermal_daemon/releases"
