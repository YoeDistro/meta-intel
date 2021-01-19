SUMMARY = "oneAPI Level Zero Specification Headers and Loader"
HOMEPAGE = "https://github.com/oneapi-src/level-zero"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8956dfdba7f8169c4005d1e9753ffddc"

SRC_URI = "git://github.com/oneapi-src/level-zero.git;protocol=https"
SRCREV = "64a766440bf6e63038a24d49ae2c9d8e35b9e103"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "opencl-headers"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

PACKAGES =+ "${PN}-headers ${PN}-samples ${PN}-loader"

do_install_append () {
        install -d ${D}${bindir} ${D}${libdir}
        install -m 755 ${B}/bin/zello* ${D}${bindir}

        oe_libinstall -C lib libze_null ${D}${libdir}
}


FILES_${PN}-headers = "${includedir}"
FILES_${PN}-samples = "${bindir} ${libdir}/libze_null* ${libdir}/libze_validation*"
FILES_${PN}-loader = "${libdir}"

# PN-loader (non -dev/-dbg/nativesdk- package) contains symlink .so
INSANE_SKIP_${PN}-loader = "dev-so"
INSANE_SKIP_${PN}-samples = "dev-so"
