HOMEPAGE = "https://www.oneapi.com"
SUMMARY  = "Deep Neural Network Library"
DESCRIPTION = "This software is a user mode library that accelerates\
deep-learning applications and frameworks on Intel architecture."
LICENSE  = "Apache-2.0 & BSD-3-Clause & BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3c6ff4426dbd618bcfd552ac4a7c1c56 \
                    file://tests/gtests/gtest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
                    file://src/cpu/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
                    file://src/cpu/jit_utils/jitprofiling/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb \
                    file://doc/assets/mathjax/MathJax.js;endline=17;md5=25a014ad78c3d72a0e15d15f1d007c20 \
                    file://src/common/primitive_hashing.hpp;beginline=52;endline=55;md5=f56de33cb6ec02de60006b10e027b300 \
                    "
SECTION = "lib"

inherit pkgconfig cmake ptest

S = "${WORKDIR}/git"
SRCREV = "f7c41dc7b5471ad8bf7905e459bbed27f9094caa"
SRC_URI = "git://github.com/oneapi-src/oneDNN.git;branch=rls-v1.4 \
           file://0001-src-CMakeLists.txt-fix-FindOpenCL.cmake-source-path.patch \
           file://0002-src-CMakeLists.txt-use-value-of-DESTDIR.patch \
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
                   "

PACKAGECONFIG ??= ""
PACKAGECONFIG[gpu] = "-DDNNL_GPU_RUNTIME=OCL, , opencl-icd-loader, intel-compute-runtime"

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
