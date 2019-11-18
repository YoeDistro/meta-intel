SUMMARY = "Yami is media infrastructure base on libva"
DESCRIPTION = "Yet Another Media Infrastructure \
light weight hardware codec library base on VA-API "

HOMEPAGE = "https://github.com/intel/libyami"
BUGTRACKER = "https://github.com/intel/libyami/issues/new"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/intel/libyami.git;branch=apache \
"
SRCREV = "08606d0a43e0ef15e5b61cc13563169370ce8715"
S = "${WORKDIR}/git"

CXXFLAGS_append = " -Wno-error"

PACKAGECONFIG ??= "${@bb.utils.contains("DISTRO_FEATURES", "x11", "x11", "", d)}"
PACKAGECONFIG[x11] = "--enable-x11,--disable-x11,virtual/libx11 libxrandr libxrender"

DEPENDS = "libva"
inherit autotools pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "opengl"
