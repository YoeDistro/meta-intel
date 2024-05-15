SUMMARY = "oneAPI Video Processing Library"
DESCRIPTION = "The oneAPI Video Processing Library (oneVPL) provides \
a single video processing API for encode, decode, and video processing \
that works across a wide range of accelerators."

HOMEPAGE = "https://github.com/intel/libvpl"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=0e35a23482445dd089b4eabe19103a06"

SRC_URI = "git://github.com/intel/libvpl.git;protocol=https;branch=main \
           file://0001-vpl.pc.in-dont-pass-pcfiledir-to-cflags.patch \
            "
SRCREV = "2274efcd3672b43297ef774f332e1fed6781381c"
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
