require ${COREBASE}/meta/recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "Intel GPU tools"
DESCRIPTION = "Variety of small tools for testing intel graphics."

LIC_FILES_CHKSUM = "file://COPYING;md5=e4b3dd344780e0400593b21b115a6947"

LICENSE_append = " & ISC"

inherit autotools gtk-doc

SRC_URI = "${XORG_MIRROR}/individual/app/${BP}.tar.xz \
           file://0001-lib-fb-Fix-rgb24-to-nv12-conversion.patch \
           "

DEPENDS += "libdrm libpciaccess cairo udev glib-2.0 libxv libx11 libxext libxrandr procps libunwind kmod openssl"
RDEPENDS_${PN} += "bash"
RDEPENDS_${PN}-tests += "bash"

PACKAGE_BEFORE_PN = "${PN}-benchmarks ${PN}-tests"

SRC_URI[md5sum] = "04c1f10d6fd85e079271540b0ea786e9"
SRC_URI[sha256sum] = "4d4b086c513bace5c23d0889de3f42ac3ebd3d968c64dedae6e28e006a499ad0"

EXTRA_OECONF = "--disable-nouveau --disable-shader-debugger"
COMPATIBLE_HOST = "(x86_64.*|i.86.*)-linux"
COMPATIBLE_HOST_libc-musl_class-target = "null"

PACKAGECONFIG ??= ""
PACKAGECONFIG[audio] = "--enable-audio,--disable-audio,alsa-lib gsl"

gputools_sysroot_preprocess() {
	rm -f ${SYSROOT_DESTDIR}${libdir}/pkgconfig/intel-gen4asm.pc
}
SYSROOT_PREPROCESS_FUNCS += "gputools_sysroot_preprocess"

FILES_${PN} += "${libdir}/intel_aubdump.so"
FILES_${PN}-benchmarks += "${libexecdir}/${BPN}/benchmarks"
FILES_${PN}-tests += "\
		${libexecdir}/${BPN}/*\
		${datadir}/${BPN}/1080p-right.png\
		${datadir}/${BPN}/1080p-left.png\
		${datadir}/${BPN}/pass.png\
		${datadir}/${BPN}/test-list.txt"
