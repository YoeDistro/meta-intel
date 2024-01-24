SUMMARY = "oneAPI Level Zero Specification Headers and Loader"
HOMEPAGE = "https://github.com/oneapi-src/level-zero"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=97957beb2f7808ffa247e5d93e6442cc"

SRC_URI = "git://github.com/oneapi-src/level-zero.git;protocol=https;branch=master"
SRCREV = "1685d01497428ca4d8b99200972b64685424d5c9"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "opencl-headers"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

PACKAGES =+ "${PN}-headers ${PN}-samples ${PN}-loader"

do_install:append () {
        install -d ${D}${bindir} ${D}${libdir}
        install -m 755 ${B}/bin/zello* ${D}${bindir}

        oe_libinstall -C lib libze_null ${D}${libdir}
}


FILES:${PN}-headers = "${includedir}"
FILES:${PN}-samples = "${bindir} ${libdir}/libze_null* ${libdir}/libze_validation*"
FILES:${PN}-loader = "${libdir}"

# PN-loader (non -dev/-dbg/nativesdk- package) contains symlink .so
INSANE_SKIP:${PN}-loader = "dev-so"
INSANE_SKIP:${PN}-samples = "dev-so"
ALLOW_EMPTY:${PN} = "1"
BBCLASSEXTEND = "native nativesdk"
