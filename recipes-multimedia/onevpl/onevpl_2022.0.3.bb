SUMMARY = "oneAPI Video Processing Library"
DESCRIPTION = "The oneAPI Video Processing Library (oneVPL) provides \
a single video processing API for encode, decode, and video processing \
that works across a wide range of accelerators."

HOMEPAGE = "https://github.com/intel/libvpl"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=43ed11c52f754dafaa62e2cfdd2bac13"

SRC_URI = "git://github.com/intel/libvpl.git;protocol=https;branch=main \
            file://0001-Fix-basename-build-issue-with-musl_libc.patch \
            file://0001-samples-Addin-wayland-scanner-auto-generate-on-cmake.patch \
            file://0002-sample_misc-Addin-basic-wayland-dmabuf-support.patch \
            file://0003-sample_misc-use-wayland-dmabuf-to-render-nv12.patch \
            file://0001-sample_common-Fix-regression-of-missing-mutex-init.patch \
            file://0001-sample_common-Fix-missing-UYUV-fourcc-enc-input.patch \
            "
SRCREV = "efc259f8b7ee5c334bca1a904a503186038bbbdd"
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

FILES:${PN}-examples = "${datadir}/oneVPL \
                       "

FILES_SOLIBSDEV = ""
FILES:${PN}-dev += "${libdir}/libvpl.so"

FILES:${PN} += " ${libdir}/oneVPL/libvpl_wayland.so \
                "
