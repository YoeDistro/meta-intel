SUMMARY  = "Intel Math Kernel Library for Deep Neural Networks"
DESCRIPTION = "This software is a user mode library that accelerates\
deep-learning applications and frameworks on Intel architecture."
LICENSE  = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e \
	file://tests/gtests/gtest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
	file://src/cpu/xbyak/COPYRIGHT;md5=03532861dad9003cc2c17f14fc7a4efa"
SECTION = "lib"

inherit pkgconfig cmake

S = "${WORKDIR}/git"
SRCREV = "722901c9aaefa579698df778d061d4848ab8c3e3"
SRC_URI = "git://github.com/intel/mkl-dnn.git;branch=rls-v0.17"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = 'null'

EXTRA_OECMAKE += "-DMKLDNN_LIBRARY_TYPE=SHARED"
EXTRA_OECMAKE += "-DMKLDNN_THREADING=OMP"
EXTRA_OECMAKE += "-DWITH_EXAMPLE=ON"
EXTRA_OECMAKE += "-DWITH_TEST=ON"
