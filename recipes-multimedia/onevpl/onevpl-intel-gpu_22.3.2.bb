SUMMARY = "Intel(R) oneVPL runtime for Intel GPU accelerated media processing"
DESCRIPTION = "Intel(R) oneVPL runtime provides an runtime to access hardware-accelerated \
video decode, encode and filtering on Intel® graphics."

HOMEPAGE = "https://github.com/intel/vpl-gpu-rt"
BUGTRACKER = "https://github.com/intel/vpl-gpu-rt/issues"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb8cb45b9b57dbaa9fcc9adc4230202b"

PE = "1"

# Only for 64 bit
COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:x86-x32 = "null"

DEPENDS += "libdrm libva intel-media-driver onevpl pkgconfig-native"

SRC_URI = "git://github.com/intel/vpl-gpu-rt.git;protocol=https;branch=intel-onevpl-22.3;lfs=0 \
           file://0001-HEVCe-lib-Fix-REXT-DDIID-issue-2566.patch \
            "

SRCREV = "0f7e5f41160bb5a48e24f7cec2ced1ef9dd9c5be"
S = "${WORKDIR}/git"

FILES:${PN} += " \
                ${libdir}/libmfx-gen/enctools.so \
               "

inherit cmake
