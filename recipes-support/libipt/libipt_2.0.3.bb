SUMMARY  = "Intel(R) Processor Trace Decoder Library"
DESCRIPTION = "The Intel Processor Trace (Intel PT) Decoder Library is Intel's \
reference implementation for decoding Intel PT. It can be used as a standalone \
library or it can be partially or fully integrated into your tool."
HOMEPAGE = "https://github.com/intel/libipt"

LICENSE  = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c46e7f00db37676465d8c6ccf91b3412"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/intel/libipt.git;protocol=https;branch=stable/v2.0"

SRCREV = "99a73227f15d49f83bd7c83dbcf0980ec3653e7a"

EXTRA_OECMAKE += " \
                  -DPTDUMP=ON  \
                  -DPTTC=ON \
                  -DCMAKE_SKIP_RPATH=ON \
                  "

do_install_append() {
    install -d ${D}${bindir}/libipt
    install -d ${D}${bindir}/libipt/tests

    cp -r ${B}/bin/* ${D}${bindir}/libipt/
    cp -r ${WORKDIR}/git/test/src/* ${D}${bindir}/libipt/tests
}

FILES_${PN}-test = "${bindir}"
PACKAGES =+ "${PN}-test"
