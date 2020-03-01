SUMMARY = "Intel® Single Event API"
DESCRIPTION = "Intel® SEAPI is the translator of itt_notify calls into \
several OS specific and third party tracing formats. \
You can use it as memory/performance/whatever profiler."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://sea_itt_lib/Copyright.txt;md5=7d54dfc8860742fb06b9c5ad28f41fcd"

SRC_URI = "git://github.com/intel/IntelSEAPI.git;protocol=https \
           file://0001-CMakeLists.txt-set-output-directory-path.patch \
           "
SRCREV = "488651b6bf0da6f15fb33e64a1dcdb63f8bee910"
S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"
EXTRA_OECMAKE += "-DDCO_PILOT=0"
EXTRA_OECMAKE += "${@oe.utils.conditional('TARGET_ARCH','x86_64','','-DFORCE_32=ON',d)}"

COMPATIBLE_HOST_libc-musl_class-target = "null"

do_install() {
     # Create the directories needed
     install -d -m 755 ${D}${libdir} ${D}${includedir}/ittnotify
     install -m 644 ${S}/bin/*.a ${D}${libdir}
     install -m 755 ${S}/bin/*.so ${D}${libdir}
     cp -r ${S}/ittnotify/include/* ${D}${includedir}/ittnotify
     cp -r ${S}/ittnotify/src/ittnotify/*.h ${D}${includedir}/ittnotify
     cp -r ${S}/*.hpp ${D}${includedir}/ittnotify
}

FILES_${PN} = "${libdir}"
FILES_${PN}-dev = "${includedir}"

RDEPENDS_${PN}-dev_remove = "${PN} (= ${EXTENDPKGV})"
