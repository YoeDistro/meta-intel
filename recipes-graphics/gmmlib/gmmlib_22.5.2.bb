SUMMARY = "Intel(R) Graphics Memory Management Library"
DESCRIPTION = "The Intel(R) Graphics Memory Management Library provides \
device specific and buffer management for the Intel(R) Graphics \
Compute Runtime for OpenCL(TM) and the Intel(R) Media Driver for VAAPI."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=465fe90caea3edd6a2cecb3f0c28a654"

SRC_URI = " \
            git://github.com/intel/gmmlib.git;protocol=https;branch=master \
            file://0001-Extend-helper-Macros-219.patch \
            "

SRCREV = "567dc09fd3859de3d9c09456ee7b366c0d327eb6"

S = "${WORKDIR}/git"

COMPATIBLE_HOST:x86-x32 = "null"

UPSTREAM_CHECK_GITTAGREGEX = "^intel-gmmlib-(?P<pver>(\d+(\.\d+)+))$"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DRUN_TEST_SUITE=OFF"

BBCLASSEXTEND = "native nativesdk"
