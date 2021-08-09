SUMMARY = "oneAPI Video Processing Library"
DESCRIPTION = "The oneAPI Video Processing Library (oneVPL) provides \
a single video processing API for encode, decode, and video processing \
that works across a wide range of accelerators."

HOMEPAGE = "https://github.com/oneapi-src/oneVPL"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=52d2c0c51bb5c0d9d9c16ae91d66c8fb"

SRC_URI = "git://github.com/oneapi-src/oneVPL.git;protocol=https \
            file://0001-Adding-Wayland-support-to-legacy-tools.patch \
            file://0001-Corrected-the-install-path.patch \
            file://0001-Adding-X11-DRI3-support.patch \
            file://0001-Fix-sample_multi_transcode-join-issue.patch \
            file://0001-Fix-the-rendering-to-X11-failures.patch \
            "
SRCREV = "d5c072584ee6f81305ed85de8759658ab7854606"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS = "libva"

COMPATIBLE_HOST = '(x86_64).*-linux'

PACKAGES =+ "${PN}-examples"

FILES:${PN}-examples = "${datadir}/oneVPL/examples \
                       "

FILES_SOLIBSDEV = ""
FILES:${PN}-dev += "${libdir}/libvpl.so"

FILES:${PN} += "${datadir} \
                ${libdir}/libvpl_wayland.so \
                "
