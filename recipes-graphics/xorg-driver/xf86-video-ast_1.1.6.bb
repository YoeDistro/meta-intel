require recipes-graphics/xorg-driver/xorg-driver-video.inc

SUMMARY = "X.Org X server -- ASpeed Technologies graphics driver"

DESCRIPTION = "ast is an Xorg driver for ASpeed Technologies video cards"

LIC_FILES_CHKSUM = "file://COPYING;md5=0b8c242f0218eea5caa949b7910a774b"

DEPENDS += "libpciaccess"
XORG_DRIVER_COMPRESSOR = ".tar.gz"

SRC_URI[sha256sum] = "eeff52178ce2916a0e07b531bb23c8b105826b6e6e6c5f7c90e33f757827a7c7"
