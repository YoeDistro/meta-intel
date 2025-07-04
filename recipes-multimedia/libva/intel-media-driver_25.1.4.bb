SUMMARY = "VA driver for Intel Gen based graphics hardware"
DESCRIPTION = "Intel Media Driver for VAAPI is a new VA-API (Video Acceleration API) \
user mode driver supporting hardware accelerated decoding, encoding, \
and video post processing for GEN based graphics hardware."

HOMEPAGE = "https://github.com/intel/media-driver"
BUGTRACKER = "https://github.com/intel/media-driver/issues"

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=6aab5363823095ce682b155fef0231f0 \
                    file://media_driver/media_libvpx.LICENSE;md5=d5b04755015be901744a78cc30d390d4 \
                    "

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

inherit features_check
REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS += "libva gmmlib"

SRC_URI = "git://github.com/intel/media-driver.git;protocol=https;nobranch=1 \
           file://0003-Force-ARGB-surface-to-tile4-for-ACM.patch \
           file://0004-Fix-failed-4k-videowalll-test-case-and-color-corrupt.patch \
          "

SRCREV = "14e2e7bcf1014186dbf1c099089c7c05cd880ae8"

COMPATIBLE_HOST:x86-x32 = "null"

UPSTREAM_CHECK_GITTAGREGEX = "^intel-media-(?P<pver>(?!600\..*)\d+(\.\d+)+)$"

inherit cmake pkgconfig

MEDIA_DRIVER_ARCH:x86    = "32"
MEDIA_DRIVER_ARCH:x86-64 = "64"

EXTRA_OECMAKE += " \
                   -DMEDIA_RUN_TEST_SUITE=OFF \
                   -DARCH=${MEDIA_DRIVER_ARCH} \
                   -DMEDIA_BUILD_FATAL_WARNINGS=OFF \
		  "

CXXFLAGS:append:x86 = " -D_FILE_OFFSET_BITS=64 -D_LARGEFILE_SOURCE"

do_configure:prepend:toolchain-clang() {
    sed -i -e '/-fno-tree-pre/d' ${S}/media_driver/cmake/linux/media_compile_flags_linux.cmake
}

FILES:${PN} += " \
                 ${libdir}/dri/ \
                 "
