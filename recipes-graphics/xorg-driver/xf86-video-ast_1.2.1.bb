require recipes-graphics/xorg-driver/xorg-driver-video.inc

SUMMARY = "X.Org X server -- ASpeed Technologies graphics driver"

DESCRIPTION = "ast is an Xorg driver for ASpeed Technologies video cards"

LIC_FILES_CHKSUM = "file://COPYING;md5=0b8c242f0218eea5caa949b7910a774b"

DEPENDS += "libpciaccess"
XORG_DRIVER_COMPRESSOR = ".tar.gz"

SRC_URI[sha256sum] = "0fd3d350f26e03f04b7c6abe088f7470051285e1706a610a3f1b459576cc4eb6"
