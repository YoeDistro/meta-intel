SUMMARY = "Intel(R) oneVPL runtime for Intel GPU accelerated media processing"
DESCRIPTION = "Intel(R) oneVPL runtime provides an runtime to access hardware-accelerated \
video decode, encode and filtering on Intel® graphics."

HOMEPAGE = "https://github.com/oneapi-src/oneVPL-intel-gpu"
BUGTRACKER = "https://github.com/oneapi-src/oneVPL-intel-gpu/issues"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb8cb45b9b57dbaa9fcc9adc4230202b"

PE = "1"

# Only for 64 bit
COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:x86-x32 = "null"

DEPENDS += "libdrm libva intel-media-driver onevpl pkgconfig-native"
RDEPENDS:${PN} += "intel-media-driver"

SRC_URI = "git://github.com/oneapi-src/oneVPL-intel-gpu.git;protocol=https;nobranch=1;lfs=0 \
           file://0001-Encode-Bugfix-for-HEVC-VDENC-422-RPL-caps-issue.-588.patch \
           file://0001-RT-Common-Fix-MediaAdapterType-issue-5898.patch \
          "

SRCREV = "30963ed30d428ccaf3c80360c8657d9c831cb38c"
S = "${WORKDIR}/git"

FILES:${PN} += " \
                ${libdir}/libmfx-gen/enctools.so \
               "

inherit cmake
