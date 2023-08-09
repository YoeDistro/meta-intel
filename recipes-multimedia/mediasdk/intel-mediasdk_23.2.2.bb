SUMMARY = "Intel(R) Media SDK for hardware accelerated media processing"
DESCRIPTION = "Intel(R) Media SDK provides an API to access hardware-accelerated \
video decode, encode and filtering on Intel® platforms with integrated graphics."

HOMEPAGE = "https://github.com/Intel-Media-SDK/MediaSDK"
BUGTRACKER = "https://github.com/Intel-Media-SDK/MediaSDK/issues"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3cb331af679cd8f968bf799a9c55b46e"

CVE_DETAILS = "intel:media_sdk"

# Only for 64 bit until media-driver issues aren't fixed
COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:x86-x32 = "null"

inherit features_check
REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS += "libva"

RDEPENDS:${PN} += "intel-media-driver"

PACKAGECONFIG ??= "${@bb.utils.contains("DISTRO_FEATURES", "x11", "dri3", "", d)} \
                   ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "wayland", "", d)} \
                   samples \
                   itt \
                   "

PACKAGECONFIG[dri3] 	= "-DENABLE_X11_DRI3=ON, -DENABLE_X11_DRI3=OFF"
PACKAGECONFIG[itt] 	= "-DENABLE_ITT=ON, -DENABLE_ITT=OFF, itt"
PACKAGECONFIG[opencl]	= "-DENABLE_OPENCL=ON, -DENABLE_OPENCL=OFF, virtual/opencl-icd opencl-clhpp opencl-headers"
PACKAGECONFIG[samples]	= "-DBUILD_SAMPLES=ON, -DBUILD_SAMPLES=OFF"
PACKAGECONFIG[wayland]	= "-DENABLE_WAYLAND=ON, -DENABLE_WAYLAND=OFF, wayland wayland-native"

SRC_URI = "git://github.com/Intel-Media-SDK/MediaSDK.git;protocol=https;nobranch=1;lfs=0 \
           file://0001-FindITT.cmake-fix-detection-of-header-library.patch \
           file://fix-gcc13.patch \
           "

SRCREV = "869b60a6c3d7b5e9f7c3b3b914986322dca4bbae"
S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "^intel-mediasdk-(?P<pver>(\d+(\.\d+)+))$"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DMFX_INCLUDE=${S}/api/include"

do_install:append() {
        mv ${D}${datadir}/mfx/samples ${D}${libdir}/mfx/samples
}

PACKAGE_BEFORE_PN = " ${PN}-samples"

FILES:${PN} += " \
                 ${libdir}/mfx \
                 ${datadir}/mfx/plugins.cfg \
                 "

FILES:${PN}-samples = "${libdir}/mfx/samples"

INSANE_SKIP:${PN}-samples += "staticdev"
