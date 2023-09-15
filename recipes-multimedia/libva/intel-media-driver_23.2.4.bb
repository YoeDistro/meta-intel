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
           file://0001-ARGB-force-to-tile4.patch \
           file://0002-Add-mocs-index-in-patch-list.patch \
           file://0003-fix-vdsfc-csc-issue.patch \
           file://0004-XRGB-force-to-tile4.patch \
           file://0001-Force-to-render-path-according-to-app-setting.patch \
           file://0002-Add-DRM-format-mappings-for-JPEG-decoder-output.patch \
           file://0003-Add-DRM-format-mappings-for-JPEG-output-to-softlet.patch \
           file://0004-Disable-vp9-padding-on-mtl.patch \
           file://0001-Fix-FC-Corruption-When-Blending-without-Colorfill.patch \
           file://0001-Fix-FC-Corruption-When-Blending-without-Colorfill-in.patch \
          "

SRCREV = "cf942344b9e439d19873f1d47c0c890d7c63b6ad"
S = "${WORKDIR}/git"

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
