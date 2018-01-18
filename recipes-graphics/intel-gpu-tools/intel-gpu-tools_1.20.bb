require ${COREBASE}/meta/recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "Intel GPU tools"
DESCRIPTION = "Variety of small tools for testing intel graphics."

LIC_FILES_CHKSUM = "file://COPYING;md5=e4b3dd344780e0400593b21b115a6947"

LICENSE_append = " & ISC"

inherit autotools gtk-doc

SRC_URI += "file://0001-lib-Fix-compilation-on-non-x86.patch"

DEPENDS += "libdrm libpciaccess cairo udev glib-2.0 libxv libx11 libxext libxrandr procps"
RDEPENDS_${PN} += "bash"
RDEPENDS_${PN}-tests += "bash"

PACKAGE_BEFORE_PN = "${PN}-benchmarks ${PN}-tests"

SRC_URI[md5sum] = "3b77a6a23274afe363bd5c942fe42562"
SRC_URI[sha256sum] = "2fffe7a66789f56f301e6b60a3afe21556f34acbad8b7b29c8f3dd41f0b148e8"

PACKAGECONFIG ??= ""
PACKAGECONFIG[libunwind] = "--with-libunwind,--without-libunwind,libunwind,libunwind"

EXTRA_OECONF = "--disable-nouveau --disable-shader-debugger"
COMPATIBLE_HOST = "(x86_64.*|i.86.*)-linux"
COMPATIBLE_HOST_libc-musl_class-target = "null"

gputools_sysroot_preprocess() {
	rm -f ${SYSROOT_DESTDIR}${libdir}/pkgconfig/intel-gen4asm.pc
}
SYSROOT_PREPROCESS_FUNCS += "gputools_sysroot_preprocess"

FILES_${PN} += "${libdir}/intel_aubdump.so"
FILES_${PN}-benchmarks += "${libexecdir}/intel-gpu-tools/benchmarks"
FILES_${PN}-tests += "\
		${libexecdir}/intel-gpu-tools/*\
		${datadir}/intel-gpu-tools/1080p-right.png\
		${datadir}/intel-gpu-tools/1080p-left.png\
		${datadir}/intel-gpu-tools/pass.png\
		${datadir}/intel-gpu-tools/test-list.txt"
