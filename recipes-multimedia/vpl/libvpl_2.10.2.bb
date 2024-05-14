SUMMARY = "Intel Video Processing Library"
DESCRIPTION = "Intel Video Processing Library (VPL) provides \
a single video processing API for encode, decode, and video processing \
that works across a wide range of accelerators."

HOMEPAGE = "https://github.com/intel/libvpl"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=ddf05049184e74942f45b0ca4cc69b8a"

SRC_URI = "git://github.com/intel/libvpl.git;protocol=https;branch=main \
           file://0001-vpl.pc.in-dont-pass-pcfiledir-to-cflags.patch \
            "
SRCREV = "383b5caac6df614e76ade5a07c4f53be702e9176"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "libva pkgconfig-native"

PACKAGECONFIG ??= "tools"
PACKAGECONFIG[tools] = "-DBUILD_TOOLS=ON, -DBUILD_TOOLS=OFF, wayland wayland-native wayland-protocols"

do_install:append() {
        mkdir -p ${D}${datadir}/VPL/samples
        mv ${D}${bindir}/sample_* ${D}${datadir}/VPL/samples
}

COMPATIBLE_HOST = '(x86_64).*-linux'

PACKAGES =+ "${PN}-examples"

RREPLACES:${PN} = "onevpl"
RCONFLICTS:${PN} = "onevpl"

FILES:${PN}-examples = "${datadir}/vpl \
                       "

FILES_SOLIBSDEV = ""
FILES:${PN}-dev += "${libdir}/libvpl.so"

FILES:${PN} += " ${datadir}/VPL/samples  \
                ${libdir}/vpl/libvpl_wayland.so \
                "
