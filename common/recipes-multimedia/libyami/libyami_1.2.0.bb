SUMMARY = "Yami is media infrastructure base on libva"
DESCRIPTION = "Yet Another Media Infrastructure \
light weight hardware codec library base on VA-API "

HOMEPAGE = "https://github.com/01org/libyami"
BUGTRACKER = "https://github.com/01org/libyami/issues/new"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/01org/libyami/archive/${PV}.tar.gz;downloadfilename=${BP}.tar.gz"
SRC_URI[md5sum] = "2e2ed3bd900866476eced140799ee48b"
SRC_URI[sha256sum] = "fdc3025f828c065a4434e73f5629e7ab8af593f1abbe097449dd5a13fa7d465f"

PACKAGECONFIG ??= "${@bb.utils.contains("DISTRO_FEATURES", "x11", "x11", "", d)}"
PACKAGECONFIG[x11] = "--enable-x11,--disable-x11,virtual/libx11 libxrandr libxrender"

DEPENDS = "libva"
inherit autotools pkgconfig distro_features_check

REQUIRED_DISTRO_FEATURES = "opengl"

UPSTREAM_CHECK_URI = "https://github.com/01org/libyami/releases"
UPSTREAM_CHECK_REGEX = "(?P<pver>\d+(\.\d+)+)"
