SUMMARY  = "Intel(R) Processor Trace Decoder Library"
DESCRIPTION = "The Intel Processor Trace (Intel PT) Decoder Library is Intel's \
reference implementation for decoding Intel PT. It can be used as a standalone \
library or it can be partially or fully integrated into your tool."
HOMEPAGE = "https://github.com/intel/libipt"

LICENSE  = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c91702d8338efc75588b838922b7b803"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/intel/libipt.git;protocol=https;branch=stable/v2.0"

SRCREV = "916d777123bf60d323890557347570e5e19dfa12"

EXTRA_OECMAKE += " \
                  -DPTDUMP=ON  \
                  -DPTTC=ON \
                  "

do_install_append() {
    install -d ${D}${bindir}/libipt
    install -d ${D}${bindir}/libipt/tests

    cp -r ${B}/bin/* ${D}${bindir}/libipt/
    cp -r ${WORKDIR}/git/test/src/* ${D}${bindir}/libipt/tests
}

FILES_${PN}-test = "${bindir}"
PACKAGES =+ "${PN}-test"
