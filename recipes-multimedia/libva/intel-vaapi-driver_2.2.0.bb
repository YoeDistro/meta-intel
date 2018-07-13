SUMMARY = "VA driver for Intel G45 & HD Graphics family"
DESCRIPTION = "intel-vaapi-driver is the VA-API implementation \
for Intel G45 chipsets and Intel HD Graphics for Intel Core \
processor family."

HOMEPAGE = "https://github.com/intel/intel-vaapi-driver"
BUGTRACKER = "https://github.com/intel/intel-vaapi-driver/issues"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2e48940f94acb0af582e5ef03537800f"

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

DEPENDS = "libva libdrm"

SRC_URI = "https://github.com/intel/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "e296c52fa1cb5c3d53a6cc994ce0a66c"
SRC_URI[sha256sum] = "e8a5f54694eb76aad42653b591030b8a53b1513144c09a80defb3d8d8c875c18"

UPSTREAM_CHECK_URI = "https://github.com/intel/intel-vaapi-driver/releases"

inherit autotools pkgconfig distro_features_check

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG ??= "${@bb.utils.contains("DISTRO_FEATURES", "x11", "x11", "", d)} \
                   ${@bb.utils.contains("DISTRO_FEATURES", "opengl wayland", "wayland", "", d)}"
PACKAGECONFIG[x11] = "--enable-x11,--disable-x11"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,wayland wayland-native virtual/egl"

FILES_${PN} += "${libdir}/dri/*.so"
FILES_${PN}-dev += "${libdir}/dri/*.la"
FILES_${PN}-dbg += "${libdir}/dri/.debug"
