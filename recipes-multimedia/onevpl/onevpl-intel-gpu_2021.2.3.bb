SUMMARY = "Intel(R) oneVPL runtime for Intel GPU accelerated media processing"
DESCRIPTION = "Intel(R) oneVPL runtime provides an runtime to access hardware-accelerated \
video decode, encode and filtering on Intel® graphics."

HOMEPAGE = "https://github.com/oneapi-src/oneVPL-intel-gpu"
BUGTRACKER = "https://github.com/oneapi-src/oneVPL-intel-gpu/issues"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb8cb45b9b57dbaa9fcc9adc4230202b"

# Only for 64 bit
COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_x86-x32 = "null"

DEPENDS += "libdrm libva intel-media-driver onevpl"

SRC_URI = "git://github.com/oneapi-src/oneVPL-intel-gpu.git;protocol=https;branch=main;lfs=0 \
            file://0001-ADLP-add-new-device-id-list.patch \
            "

SRCREV = "d6b0df4f058166cff31a360b77774f7410667be5"
S = "${WORKDIR}/git"

inherit cmake
