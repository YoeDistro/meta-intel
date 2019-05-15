SUMMARY = "Intel® Single Event API"
DESCRIPTION = "Intel® SEAPI is the translator of itt_notify calls into \
several OS specific and third party tracing formats. \
You can use it as memory/performance/whatever profiler."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://sea_itt_lib/Copyright.txt;md5=7d54dfc8860742fb06b9c5ad28f41fcd"

SRC_URI = "\
	git://github.com/intel/IntelSEAPI.git;protocol=https \
	file://0001-CMakeLists.txt-set-output-directory-path.patch \
"
SRCREV="36bff07521afffc0c0f7db79252338954ca7e6dd"
S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"
EXTRA_OECMAKE += "-DDCO_PILOT=0"
EXTRA_OECMAKE += "${@oe.utils.conditional('TARGET_ARCH','x86_64','','-DFORCE_32=ON',d)}"

COMPATIBLE_HOST_libc-musl_class-target = "null"

do_install() {
     install -d -m 755 ${D}${libdir} ${D}${includedir}
     install -m 644 ${S}/bin/*.a ${D}${libdir}
     install -m 644 ${S}/ittnotify/include/ittnotify.h ${D}${includedir}
}

RDEPENDS_${PN}-dev_remove = "${PN} (= ${EXTENDPKGV})"
