SUMMARY = "Intel(R) Graphics Memory Management Library"
DESCRIPTION = "The Intel(R) Graphics Memory Management Library provides \
device specific and buffer management for the Intel(R) Graphics \
Compute Runtime for OpenCL(TM) and the Intel(R) Media Driver for VAAPI."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=465fe90caea3edd6a2cecb3f0c28a654"

SRC_URI = " \
            git://github.com/intel/gmmlib.git;protocol=https;branch=master \
            "

SRCREV = "aa4e5d6c8f1d798b78ffd7ea85296fdd3a3946b2"

COMPATIBLE_HOST:x86-x32 = "null"

UPSTREAM_CHECK_GITTAGREGEX = "^intel-gmmlib-(?P<pver>(\d+(\.\d+)+))$"

inherit pkgconfig cmake

EXTRA_OECMAKE += " \
    -DRUN_TEST_SUITE=OFF \
    -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
    "

BBCLASSEXTEND = "native nativesdk"
