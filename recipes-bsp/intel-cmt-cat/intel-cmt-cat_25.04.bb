SUMMARY = "intel-cmt-cat"
DESCRIPTION = "Software package which provides basic support for Intel(R) \
Resource Director Technology (Intel(R) RDT)"
HOMEPAGE = "https://github.com/intel/intel-cmt-cat"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4b63c65942e1c16fd897f8cd20abebf8"

SRC_URI = "git://github.com/intel/intel-cmt-cat;protocol=https;branch=master"
SRCREV = "17629d0b726875836af6c7d9cb38b8ed23f32089"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

do_install() {
    oe_runmake install PREFIX=${D}${prefix} NOLDCONFIG=y
}

FILES:${PN} += "${nonarch_libdir}/libpqos*"
FILES:${PN}-doc = "/usr/man*"

INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "libdir"
INSANE_SKIP:${PN} += "already-stripped"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
