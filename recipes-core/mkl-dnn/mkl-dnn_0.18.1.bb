SUMMARY  = "Intel Math Kernel Library for Deep Neural Networks"
DESCRIPTION = "This software is a user mode library that accelerates\
deep-learning applications and frameworks on Intel architecture."
LICENSE  = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=afa44a3d001cc203032135324f9636b7 \
	file://tests/gtests/gtest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
	file://src/cpu/xbyak/COPYRIGHT;md5=03532861dad9003cc2c17f14fc7a4efa"
SECTION = "lib"

inherit pkgconfig cmake

S = "${WORKDIR}/git"
SRCREV = "7de7e5d02bf687f971e7668963649728356e0c20"
SRC_URI = "git://github.com/intel/mkl-dnn.git;branch=rls-v0.18"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = 'null'

EXTRA_OECMAKE += "-DMKLDNN_LIBRARY_TYPE=SHARED"
EXTRA_OECMAKE += "-DMKLDNN_THREADING=OMP"
EXTRA_OECMAKE += "-DWITH_EXAMPLE=ON"
EXTRA_OECMAKE += "-DWITH_TEST=ON"
