SUMMARY = "The Intel(R) Graphics Compute Runtime for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compute Runtime for OpenCL(TM) \
is an open source project to converge Intel's development efforts \
on OpenCL(TM) compute stacks supporting the GEN graphics hardware \
architecture."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae27f47fd6755510247c19e547e4c804 \
                    file://third_party/opencl_headers/LICENSE;md5=dcefc90f4c3c689ec0c2489064e7273b"

SRC_URI = "git://github.com/intel/compute-runtime.git;protocol=https \
           file://113cef897712c8b475f668f2bcf77a12db76a90e.patch \
           "

SRCREV = "e0633548a9bd025c70bc7f3539eef094b1bc9ce1"

S = "${WORKDIR}/git"

DEPENDS += " intel-graphics-compiler gmmlib clang"
DEPENDS_append_class-target = " intel-compute-runtime-native"

RDEPENDS_${PN} += " intel-graphics-compiler gmmlib"

inherit cmake pkgconfig

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

EXTRA_OECMAKE = " \
                 -DIGC_DIR=${STAGING_INCDIR}/igc \
                 -DBUILD_TYPE=Release \
                 -DSKIP_UNIT_TESTS=1 \
                 -DCCACHE_ALLOWED=FALSE \
                 -Dcloc_cmd_prefix=ocloc \
                 "

FILES_${PN} += " \
                 ${libdir}/intel-opencl/libigdrcl.so \
                 ${libdir}/intel-opencl/libocloc.so \
                 "

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+(\.\d+)+)"
