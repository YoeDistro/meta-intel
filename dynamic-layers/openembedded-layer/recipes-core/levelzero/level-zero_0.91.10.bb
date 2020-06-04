SUMMARY = "oneAPI Level Zero Specification Headers and Loader"
HOMEPAGE = "https://github.com/oneapi-src/level-zero"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8956dfdba7f8169c4005d1e9753ffddc"

SRC_URI = "git://github.com/oneapi-src/level-zero.git;protocol=https"
SRCREV = "ebb363e938a279cf866cb93d28e31aaf0791ea19"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "opencl-headers"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

PACKAGES =+ "${PN}-headers ${PN}-loader"

FILES_${PN}-headers = "${includedir}/*"
FILES_${PN}-loader = "${libdir}/*"

# PN-loader (non -dev/-dbg/nativesdk- package) contains symlink .so
INSANE_SKIP_${PN}-loader = "dev-so"
