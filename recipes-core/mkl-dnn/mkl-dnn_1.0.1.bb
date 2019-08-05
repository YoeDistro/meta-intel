SUMMARY  = "Intel Math Kernel Library for Deep Neural Networks"
DESCRIPTION = "This software is a user mode library that accelerates\
deep-learning applications and frameworks on Intel architecture."
LICENSE  = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a34303951205b8c53485477c74052380 \
	file://tests/gtests/gtest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
	file://src/cpu/xbyak/COPYRIGHT;md5=03532861dad9003cc2c17f14fc7a4efa \
	file://src/cpu/jit_utils/jitprofiling/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb"
SECTION = "lib"

inherit pkgconfig cmake ptest

S = "${WORKDIR}/git"
SRCREV = "61b9663bcd58ece1b96b930bed73d6545f41d656"
SRC_URI = "git://github.com/intel/mkl-dnn.git;branch=rls-v1.0 \
        file://run-ptest \
"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = 'null'

EXTRA_OECMAKE += "-DMKLDNN_LIBRARY_TYPE=SHARED"
EXTRA_OECMAKE += "-DMKLDNN_THREADING=OMP"
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
