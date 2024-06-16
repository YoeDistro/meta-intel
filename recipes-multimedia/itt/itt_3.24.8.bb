SUMMARY = "Intel® Instrumentation and Tracing Technology (ITT) and Just-In-Time (JIT) API"
DESCRIPTION = "The Instrumentation and Tracing Technology (ITT) API enables \
application to generate and control the collection of trace data during its \
execution across different Intel tools."

LICENSE = "BSD-3-Clause & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/BSD-3-Clause.txt;md5=c551872bcf41ce707df54c722edeca7b \
                    file://LICENSES/GPL-2.0-only.txt;md5=e2d76e7801260c21b90eea3605508ad6 \
                    "

SRC_URI = "git://github.com/intel/ittapi.git;protocol=https;branch=master"
SRCREV = "1d92c61fa2293d86ff248ff37160651de5d49bbf"
S = "${WORKDIR}/git"
PE = "1"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"
EXTRA_OECMAKE += "${@oe.utils.conditional('TARGET_ARCH','x86_64','','-DFORCE_32=ON',d)}"

do_install() {
     install -d -m 755 ${D}${libdir} ${D}${includedir}/ittnotify
     install -m 644 ${B}/bin/*.a ${D}${libdir}
     cp -r ${S}/include/* ${D}${includedir}/ittnotify
     cp -r ${S}/src/ittnotify/*.h ${D}${includedir}/ittnotify
     rm -r ${D}${includedir}/ittnotify/fortran/win32
     if [ "${TARGET_ARCH}" = "x86_64" ]; then
        rm -r ${D}${includedir}/ittnotify/fortran/posix/x86
     else
        rm -r ${D}${includedir}/ittnotify/fortran/posix/x86_64
     fi

}

RDEPENDS:${PN}-dev:remove = "${PN} (= ${EXTENDPKGV})"
