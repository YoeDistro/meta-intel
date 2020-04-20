require ${COREBASE}/meta/recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "Intel GPU tools"
DESCRIPTION = "Variety of small tools for testing intel graphics."

LIC_FILES_CHKSUM = "file://COPYING;md5=67bfee4df38fa6ecbe3a675c552d4c08"

LICENSE_append = " & ISC"

inherit meson

SRC_URI = "${XORG_MIRROR}/individual/app/${BP}.tar.xz"

DEPENDS += "libdrm libpciaccess cairo udev glib-2.0 libxv libx11 libxext libxrandr procps libunwind kmod openssl elfutils bison-native"
RDEPENDS_${PN} += "bash"
RDEPENDS_${PN}-tests += "bash"

PACKAGE_BEFORE_PN = "${PN}-benchmarks ${PN}-tests"

SRC_URI[sha256sum] = "40454d8f0484ea2477862007398a08eef78a6c252c4defce1c934548593fdd11"

EXTRA_OEMESON = "-Dlibdrm_drivers=intel "
SECURITY_LDFLAGS = "${SECURITY_X_LDFLAGS}"

COMPATIBLE_HOST = "(x86_64.*|i.86.*)-linux"
COMPATIBLE_HOST_libc-musl_class-target = "null"

gputools_sysroot_preprocess() {
	rm -f ${SYSROOT_DESTDIR}${libdir}/pkgconfig/intel-gen4asm.pc
}
SYSROOT_PREPROCESS_FUNCS += "gputools_sysroot_preprocess"

FILES_${PN}-benchmarks += "${libexecdir}/${BPN}/benchmarks"
FILES_${PN}-tests += "\
		${libexecdir}/${BPN}/*\
		${datadir}/${BPN}/1080p-right.png\
		${datadir}/${BPN}/1080p-left.png\
		${datadir}/${BPN}/pass.png\
		${datadir}/${BPN}/test-list.txt"
