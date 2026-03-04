SUMMARY  = "Intel(R) Processor Trace Decoder Library"
DESCRIPTION = "The Intel Processor Trace (Intel PT) Decoder Library is Intel's \
reference implementation for decoding Intel PT. It can be used as a standalone \
library or it can be partially or fully integrated into your tool."
HOMEPAGE = "https://github.com/intel/libipt"

LICENSE  = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fcee2b5da70c8e2e58c5f4d1f2d5788a"

inherit pkgconfig cmake

SRC_URI = "git://github.com/intel/libipt.git;protocol=https;branch=stable/v2.2 \
           file://0001-pttc-use-nasm-instead-of-yasm.patch \
           "

SRCREV = "eecdf779a35384235d3c32a6213024f53368cb60"

EXTRA_OECMAKE += " \
                  -DPTDUMP=ON  \
                  -DPTTC=ON \
                  -DPTSEG=ON \
                  -DCMAKE_SKIP_RPATH=ON \
                  -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
                  "

do_install:append() {
    install -d ${D}${bindir}/libipt
    install -d ${D}${bindir}/libipt/tests

    cp -r ${B}/bin/* ${D}${bindir}/libipt/
    cp -r ${S}/test/src/* ${D}${bindir}/libipt/tests
}

FILES:${PN}-test = "${bindir}"
PACKAGES =+ "${PN}-test"

