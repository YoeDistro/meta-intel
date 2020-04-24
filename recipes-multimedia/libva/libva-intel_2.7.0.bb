# Latest upgrades for media components depend upon libva v2.7.0
# So need this upgrade

SUMMARY = "Video Acceleration (VA) API for Linux"
DESCRIPTION = "Video Acceleration API (VA API) is a library (libVA) \
and API specification which enables and provides access to graphics \
hardware (GPU) acceleration for video processing on Linux and UNIX \
based operating systems. Accelerated processing includes video \
decoding, video encoding, subpicture blending and rendering. The \
specification was originally designed by Intel for its GMA (Graphics \
Media Accelerator) series of GPU hardware, the API is however not \
limited to GPUs or Intel specific hardware, as other hardware and \
manufacturers can also freely use this API for hardware accelerated \
video decoding."

HOMEPAGE = "https://01.org/linuxmedia/vaapi"
BUGTRACKER = "https://github.com/intel/libva/issues"

SECTION = "x11"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2e48940f94acb0af582e5ef03537800f"

SRC_URI = "https://github.com/intel/libva/releases/download/${PV}/libva-${PV}.tar.bz2"
SRC_URI[md5sum] = "bd5052569520e734eb8aeb0f503cfcae"
SRC_URI[sha256sum] = "b75be416615dea75c74314fae8919dd72ca46d06b4e009e029661c2c51d87d70"

S = "${WORKDIR}/libva-${PV}"

UPSTREAM_CHECK_URI = "https://github.com/intel/libva/releases"

DEPENDS = "libdrm virtual/mesa"

inherit meson pkgconfig ${COMPAT_DISTRO_FEATURE_CHECK}

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'wayland x11', d)}"
PACKAGECONFIG[x11] = "-Dwith_x11=yes, -Dwith_x11=no,virtual/libx11 libxext libxfixes"
PACKAGECONFIG[wayland] = "-Dwith_wayland=yes, -Dwith_wayland=no,wayland-native wayland"

PACKAGES =+ "${PN}-x11 ${PN}-glx ${PN}-wayland"

RDEPENDS_${PN}-x11 =+ "${PN}"
RDEPENDS_${PN}-glx =+ "${PN}-x11"

FILES_${PN}-x11 =+ "${libdir}/libva-x11*${SOLIBS}"
FILES_${PN}-glx =+ "${libdir}/libva-glx*${SOLIBS}"
FILES_${PN}-wayland =+ "${libdir}/libva-wayland*${SOLIBS}"

PROVIDES = "libva"
RPROVIDES_${PN} += "libva"
