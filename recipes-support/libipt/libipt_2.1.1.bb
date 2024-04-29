SUMMARY  = "Intel(R) Processor Trace Decoder Library"
DESCRIPTION = "The Intel Processor Trace (Intel PT) Decoder Library is Intel's \
reference implementation for decoding Intel PT. It can be used as a standalone \
library or it can be partially or fully integrated into your tool."
HOMEPAGE = "https://github.com/intel/libipt"

LICENSE  = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a429afa59f273b5d12778eda69d10313"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/intel/libipt.git;protocol=https;branch=stable/v2.1"

SRCREV = "1c9bc700f4b9a71fd2d1cf1742de7e2351ddb281"

EXTRA_OECMAKE += " \
                  -DPTDUMP=ON  \
                  -DPTTC=ON \
                  -DPTSEG=ON \
                  -DCMAKE_SKIP_RPATH=ON \
                  "

do_install:append() {
    install -d ${D}${bindir}/libipt
    install -d ${D}${bindir}/libipt/tests

    cp -r ${B}/bin/* ${D}${bindir}/libipt/
    cp -r ${S}/test/src/* ${D}${bindir}/libipt/tests
}

FILES:${PN}-test = "${bindir}"
PACKAGES =+ "${PN}-test"
