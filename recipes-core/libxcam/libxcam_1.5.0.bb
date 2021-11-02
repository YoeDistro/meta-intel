SUMMARY  = "libXCam is a project for extended camera(not limited in camera) \
features and focus on image quality improvement and video analysis"
LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c911f084a3e5f46b21582a6cc9973e6"
SECTION = "lib"

inherit autotools pkgconfig

S = "${WORKDIR}/git"
SRCREV = "231a1d5243cd45c7a6b511b667f1ec52178fdda8"
SRC_URI = "git://github.com/intel/libxcam.git;branch=1.5.0;protocol=https \
"

COMPATIBLE_HOST:libc-musl = "null"

PACKAGECONFIG ??= " gst \
                    ${@bb.utils.contains("DISTRO_FEATURES", "opengl", "gles", "", d)} \
                    ${@bb.utils.contains("DISTRO_FEATURES", "vulkan", "vulkan", "", d)} \
                    "

PACKAGECONFIG[gst]    = "--enable-gst, --disable-gst, gstreamer1.0 gstreamer1.0-plugins-base"
PACKAGECONFIG[aiq]    = "--enable-aiq, --disable-aiq,"
PACKAGECONFIG[libcl]  = "--enable-libcl, --disable-libcl,"
PACKAGECONFIG[opencv] = "--enable-opencv, --disable-opencv, opencv"
PACKAGECONFIG[render] = "--enable-render, --disable-render,"
PACKAGECONFIG[gles]   = "--enable-gles, --disable-gles, virtual/mesa"
PACKAGECONFIG[vulkan] = "--enable-vulkan, --disable-vulkan, vulkan-loader virtual/mesa"
PACKAGECONFIG[dnn]    = "--enable-dnn, --disable-dnn,"

do_install:append () {
    install -d ${D}${bindir}/libxcam
    cp -r ${WORKDIR}/build/tests/.libs/* ${D}${bindir}/libxcam/
}

FILES:${PN} += "${libdir}/gstreamer-*/*.so"
FILES:${PN}-test = "${bindir}/libxcam/*"
PACKAGES =+ "${PN}-test"
RDEPENDS:${PN}-test =+ "bash"
