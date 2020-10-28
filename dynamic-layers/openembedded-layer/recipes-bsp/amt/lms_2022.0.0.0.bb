SUMMARY = "Intel(R) Local Managability Service"
DESCRIPTION = "Intel Local Manageability Service allows applications \
to access the Intel Active Management Technology (AMT) firmware via \
the Intel Management Engine Interface (MEI)."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=2ee41112a44fe7014dce33e26468ba93"

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

COMPATIBLE_HOST_libc-musl = "null"

inherit cmake systemd features_check

DEPENDS = "metee ace xerces-c libnl libxml2 glib-2.0 glib-2.0-native"

EXTRA_OECMAKE += "-DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3"

# Enable either connman or networkmanager or none but not both.
PACKAGECONFIG ??= "connman"
PACKAGECONFIG[connman]        = "-DNETWORK_CN=ON, -DNETWORK_CN=OFF, connman"
PACKAGECONFIG[networkmanager] = "-DNETWORK_NM=ON, -DNETWORK_NM=OFF, networkmanager"

REQUIRED_DISTRO_FEATURES= "systemd"

FILES_${PN} += "${datadir}/dbus-1/system-services/*.service"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE_${PN} = "lms.service"

SRC_URI = "git://github.com/intel/lms.git \
           file://0001-CIM_Framework-CMakeLists.txt-turn-OFF-BUILD_LIBCIM.patch \
           "
SRCREV = "597783800b000091e05855f1e7431555216e77da"

SRC_URI_append = " file://lms_drop_rpath_${PV}.diff"

do_install_append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${B}/UNS/lms.service ${D}${systemd_system_unitdir}
}

RDEPENDS_${PN} += "ace"
