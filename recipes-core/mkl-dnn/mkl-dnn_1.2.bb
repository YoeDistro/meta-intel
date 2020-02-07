SUMMARY  = "Deep Neural Network Library"
DESCRIPTION = "This software is a user mode library that accelerates\
deep-learning applications and frameworks on Intel architecture."
LICENSE  = "Apache-2.0 & BSD-3-Clause & BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c328b62744f16fbb96f4335c5b65325 \
                    file://tests/gtests/gtest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
                    file://src/cpu/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
                    file://src/cpu/jit_utils/jitprofiling/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb \
                    file://doc/assets/mathjax/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    "
SECTION = "lib"

inherit pkgconfig cmake ptest

S = "${WORKDIR}/git"
SRCREV = "75d0b1a7f3586c212e37acebbb8acd221cee7216"
SRC_URI = "git://github.com/intel/mkl-dnn.git;branch=rls-v1.2 \
           file://run-ptest \
           "

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

CVE_PRODUCT = "intel:math_kernel_library"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = 'null'

EXTRA_OECMAKE += "-DDNNL_LIBRARY_TYPE=SHARED"
EXTRA_OECMAKE += "-DDNNL_THREADING=OMP"
EXTRA_OECMAKE += "-DWITH_EXAMPLE=ON"
EXTRA_OECMAKE += "-DWITH_TEST=ON"
EXTRA_OECMAKE += "-DARCH_OPT_FLAGS=''"
EXTRA_OECMAKE += "-DCMAKE_SKIP_RPATH=ON"

do_install_append () {
    install -d ${D}${bindir}/mkl-dnn
    install -d ${D}${bindir}/mkl-dnn/tests
    install -d ${D}${bindir}/mkl-dnn/tests/benchdnn
    install -d ${D}${bindir}/mkl-dnn/tests/benchdnn/inputs
    install -m 0755 ${B}/tests/benchdnn/benchdnn ${D}${bindir}/mkl-dnn/tests/benchdnn
    cp -r ${B}/tests/benchdnn/inputs/* ${D}${bindir}/mkl-dnn/tests/benchdnn/inputs
}


do_install_ptest () {
    install -d ${D}${PTEST_PATH}/tests
    install -m 0755 ${B}/tests/api-c ${D}${PTEST_PATH}/tests
    install -m 0755 ${B}/tests/test_c_symbols-c ${D}${PTEST_PATH}/tests
}

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/mkl-dnn/*"
