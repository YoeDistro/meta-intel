SUMMARY = "oneAPI Video Processing Library"
DESCRIPTION = "The oneAPI Video Processing Library (oneVPL) provides \
a single video processing API for encode, decode, and video processing \
that works across a wide range of accelerators."

HOMEPAGE = "https://github.com/oneapi-src/oneVPL"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=f43d223f2b736e89abed9660483d0386"

SRC_URI = "git://github.com/oneapi-src/oneVPL.git;protocol=https;branch=master \
           file://0001-Fix-valgrind-leak-reported-on-wayland.patch \
           file://0002-Fix-sample_multi_transcode-segfault-on-wayland.patch \
           file://0003-Fix-X11-rendering-corruption-issue.patch \
           file://0004-Adjust-MPEG-1920x1088-alignment.patch \
           file://0005-Fix-sample_multi_transcode-intermittent-segfault.patch \
           file://0006-vpl.pc.in-dont-pass-pcfiledir.patch \
            "
SRCREV = "4cdf44ccaa605460499c52f39eff5517da2fc3c8"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "libva pkgconfig-native"

PACKAGECONFIG ??= "tools"
PACKAGECONFIG[tools] = "-DBUILD_TOOLS=ON, -DBUILD_TOOLS=OFF, wayland wayland-native wayland-protocols"

do_install:append() {
        mkdir -p ${D}${datadir}/oneVPL/samples
        mv ${D}${bindir}/sample_* ${D}${datadir}/oneVPL/samples
}

COMPATIBLE_HOST = '(x86_64).*-linux'

PACKAGES =+ "${PN}-examples"

FILES:${PN}-examples = "${datadir}/vpl \
                       "

FILES_SOLIBSDEV = ""
FILES:${PN}-dev += "${libdir}/libvpl.so"

FILES:${PN} += " ${datadir}/oneVPL/samples  \
                ${libdir}/vpl/libvpl_wayland.so \
                "
