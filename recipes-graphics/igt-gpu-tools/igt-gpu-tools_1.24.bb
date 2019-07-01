require ${COREBASE}/meta/recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "Intel GPU tools"
DESCRIPTION = "Variety of small tools for testing intel graphics."

LIC_FILES_CHKSUM = "file://COPYING;md5=67bfee4df38fa6ecbe3a675c552d4c08"

LICENSE_append = " & ISC"

inherit autotools

SRC_URI = "${XORG_MIRROR}/individual/app/${BP}.tar.xz"

DEPENDS += "libdrm libpciaccess cairo udev glib-2.0 libxv libx11 libxext libxrandr procps libunwind kmod openssl elfutils"
RDEPENDS_${PN} += "bash"
RDEPENDS_${PN}-tests += "bash"

PACKAGE_BEFORE_PN = "${PN}-benchmarks ${PN}-tests"

SRC_URI[md5sum] = "0e0b4a1a80dc2e09c2705e0c5159e0a1"
SRC_URI[sha256sum] = "57357c72feeafc923c9cfd2d1234bd80e120fc7cc6099eac81158ec351a821bf"

EXTRA_OECONF = "--disable-nouveau"
COMPATIBLE_HOST = "(x86_64.*|i.86.*)-linux"
COMPATIBLE_HOST_libc-musl_class-target = "null"

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
