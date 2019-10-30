SUMMARY  = "Intel® RealSense™ SDK"
HOMEPAGE = "https://www.intelrealsense.com/"
DESCRIPTION = "Intel® RealSense™ SDK 2.0 is a cross-platform library for \
Intel® RealSense™ depth cameras (D400 series and the SR300) and the T265 \
tracking camera"
LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a1692f06943fa281fd047a22d7e10800"
SECTION = "lib"

inherit pkgconfig cmake

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/IntelRealSense/librealsense.git"
SRCREV = "5096aab40c71bfa2bebd17e4ace5f6678587b889"

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"
EXTRA_OECMAKE += "-DBUILD_EXAMPLES=true"
EXTRA_OECMAKE += "-DBUILD_GRAPHICAL_EXAMPLES=${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'true', 'false', d)}"
EXTRA_OECMAKE += "-DBUILD_WITH_TM2=false"
EXTRA_OECMAKE += "-DUSE_SYSTEM_LIBUSB=true"

do_install_append() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${S}/config/99-realsense-libusb.rules ${D}${sysconfdir}/udev/rules.d/99-${BPN}-libusb.rules
}

DEPENDS = "udev libusb1"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'libpng libglu glfw gtk+3', '', d)}"
