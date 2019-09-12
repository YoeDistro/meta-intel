SUMMARY = "The Intel(R) Graphics Compute Runtime for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compute Runtime for OpenCL(TM) \
is an open source project to converge Intel's development efforts \
on OpenCL(TM) compute stacks supporting the GEN graphics hardware \
architecture."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae27f47fd6755510247c19e547e4c804 \
                    file://third_party/opencl_headers/LICENSE;md5=dcefc90f4c3c689ec0c2489064e7273b"

SRC_URI = "git://github.com/intel/compute-runtime.git;protocol=https \
           "

SRC_URI_append_class-target = " \
      file://dont-use-ld-library-path.patch \
"

SRCREV = "cb4e5576cb5414ab3af54c49819a4ced305b965b"

S = "${WORKDIR}/git"

DEPENDS += " intel-graphics-compiler gmmlib clang"
DEPENDS_append_class-target = " intel-compute-runtime-native"

RDEPENDS_${PN} += " intel-graphics-compiler gmmlib"

inherit cmake pkgconfig

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

EXTRA_OECMAKE = " \
                 -DBUILD_TYPE=Release \
                 -DSKIP_UNIT_TESTS=1 \
                 -DCCACHE_ALLOWED=FALSE \
                 "

LDFLAGS_append_class-native = " -fuse-ld=lld"
TOOLCHAIN_class-native = "clang"

FILES_${PN} += "${libdir}/intel-opencl/libigdrcl.so"

BBCLASSEXTEND = "native nativesdk"

EXCLUDE_FROM_WORLD = "1"
