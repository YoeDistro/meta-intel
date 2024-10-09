SUMMARY = "Intel Video Processing Library Tools"
DESCRIPTION = "Intel Video Processing Library (VPL) Tools provides \
access to hardware accelerated video decode, encode and video processing \
capabilities on Intel® GPUs use cases."

HOMEPAGE = "https://github.com/intel/libvpl-tools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=ddf05049184e74942f45b0ca4cc69b8a"

SRC_URI = "git://github.com/intel/libvpl-tools.git;protocol=https;branch=main \
           file://0001-Correct-va-attrib-for-vaapiallocator.patch \
           file://0002-Enable-YUV400-JPEG-Enc-for-vaapi.patch \
           file://0003-Enable-YUV400-JPEG-Enc-for-linux-vaapi-only.patch \
           file://0004-Fix-rDRM-DMA-methods.patch \
           file://0005-Force-allocator-to-use-DRM_PRIME-for-rDRM.patch \
           file://0006-Enable-VVC-in-sample_decode.patch \
           file://0007-Fix-X11-rendering-for-xe.patch \
           file://0008-Fix-code-formatting.patch \
          "

SRCREV = "452ab253da13b57067222e8311ef143d0203d766"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "libva libvpl pkgconfig-native"

PACKAGECONFIG ??= "tools"
PACKAGECONFIG[tools] = "-DENABLE_WAYLAND=ON, -DENABLE_WAYLAND=OFF, wayland wayland-native wayland-protocols"

do_install:append() {
        mkdir -p ${D}${datadir}/VPL/samples
        mv ${D}${bindir}/sample_* ${D}${datadir}/VPL/samples
        mv ${D}${bindir}/metrics_monitor ${D}${datadir}/VPL/samples
}

COMPATIBLE_HOST = '(x86_64).*-linux'

FILES_SOLIBSDEV = ""

FILES:${PN} += " ${datadir}/VPL/samples  \
                 ${libdir}/libcttmetrics.so \
                 ${libdir}/vpl-tools/libvpl_wayland.so \
                "

FILES:${PN}-doc += " ${datadir}/vpl-tools \
                   "
