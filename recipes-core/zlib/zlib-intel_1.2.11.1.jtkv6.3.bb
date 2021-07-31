require recipes-core/zlib/zlib_1.2.11.bb

FILESEXTRAPATHS:append = ":${COREBASE}/meta/recipes-core/zlib/zlib"

SUMMARY = "Zlib Compression Library (Intel-tuned)"

PROVIDES = "zlib"

SRC_URI:remove = "${SOURCEFORGE_MIRROR}/libpng/${BPN}/${PV}/${BPN}-${PV}.tar.xz"
SRC_URI:prepend = "git://github.com/jtkukunas/zlib.git;protocol=git "

SRCREV = "a43a247bfa16ec5368747b5b64f11ea5ca033010"
S = "${WORKDIR}/git"

RPROVIDES:${PN} += "zlib"
RPROVIDES:${PN}-ptest += "zlib-ptest"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+)_jtkv(\d+(\.\d+)+))$"
