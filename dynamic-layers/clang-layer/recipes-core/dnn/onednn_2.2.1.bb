HOMEPAGE = "https://www.oneapi.com"
SUMMARY  = "Deep Neural Network Library"
DESCRIPTION = "This software is a user mode library that accelerates\
deep-learning applications and frameworks on Intel architecture."
LICENSE  = "Apache-2.0 & BSD-3-Clause & BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8e17c0f9656ebaf0c380d9b22707c846 \
                    file://tests/gtests/gtest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
                    file://src/cpu/x64/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
                    file://src/common/ittnotify/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb \
                    file://doc/assets/mathjax/MathJax.js;endline=17;md5=25a014ad78c3d72a0e15d15f1d007c20 \
                    "
SECTION = "lib"

inherit pkgconfig cmake ptest

S = "${WORKDIR}/git"
SRCREV = "f58682cd8bd0615f41d879f8afc8f1511ab42d24"
SRC_URI = "git://github.com/oneapi-src/oneDNN.git;branch=rls-v2.2 \
           file://run-ptest \
           "

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

CVE_PRODUCT = "intel:math_kernel_library"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = 'null'

EXTRA_OECMAKE += " \
                   -DDNNL_LIBRARY_TYPE=SHARED \
                   -DDNNL_BUILD_EXAMPLES=ON \
                   -DDNNL_BUILD_TESTS=ON \
                   -DDNNL_CPU_RUNTIME=OMP \
                   -DDNNL_ARCH_OPT_FLAGS="" \
                   -DCMAKE_SKIP_RPATH=ON \
                   "

PACKAGECONFIG ??= ""
PACKAGECONFIG[gpu] = "-DDNNL_GPU_RUNTIME=OCL, , opencl-headers ocl-icd, intel-compute-runtime"

do_install_append () {
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
