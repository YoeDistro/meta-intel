SUMMARY = "Applications and Scripts for libyami."
DESCRIPTION = "Applications and Scripts for libyami."

HOMEPAGE = "https://github.com/01org/libyami-utils"
BUGTRACKER = "https://github.com/01org/libyami-utils/issues/new"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "https://github.com/01org/libyami-utils/archive/${PV}.tar.gz;downloadfilename=${BP}.tar.gz \
           file://0001-Fix-build-with-clang.patch \
           "
SRC_URI[md5sum] = "b4637f1a384e3de20076bf01ca2515d3"
SRC_URI[sha256sum] = "3dbaedc797bf2d0e03879bfdbea462c3ec2aac89b49c1ed55cbff1be2590d1e8"

DEPENDS = "libva libyami"

EXTRA_OECONF = " --enable-tests-gles --disable-md5"

inherit autotools pkgconfig distro_features_check

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"

# --enable-x11 needs libva-x11
# gles-tests fail to build without x11: see https://github.com/01org/libyami-utils/issues/91
PACKAGECONFIG[x11] = "--enable-x11 --enable-tests-gles,--disable-x11 --disable-tests-gles, virtual/libx11"

UPSTREAM_CHECK_URI = "http://github.com/01org/libyami-utils/releases"
UPSTREAM_CHECK_REGEX = "(?P<pver>\d+(\.\d+)+)"
