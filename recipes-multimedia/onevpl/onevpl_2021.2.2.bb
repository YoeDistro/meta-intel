SUMMARY = "oneAPI Video Processing Library"
DESCRIPTION = "The oneAPI Video Processing Library (oneVPL) provides \
a single video processing API for encode, decode, and video processing \
that works across a wide range of accelerators."

HOMEPAGE = "https://github.com/oneapi-src/oneVPL"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=0a071a05786c453d52f8b3e511ed39c4"

SRC_URI = "git://github.com/oneapi-src/oneVPL.git;protocol=https \
            file://0001-cmake-Allow-build-env-to-set-variables.patch \
            file://0001-Add-X11-support-to-legacy-tools.patch \
            "
SRCREV = "17968d8d2299352f5a9e09388d24e81064c81c87"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS = "libva"

COMPATIBLE_HOST = '(x86_64).*-linux'

PACKAGES =+ "${PN}-examples"

EXTRA_OECMAKE += " \
                    -DVPL_UTIL_PATH=${S}/examples/util \
                    -DCMAKE_INSTALL_ENVDIR=${datadir}/vpl/env \
                    -DCMAKE_INSTALL_MODDIR=${datadir}/vpl/modulefiles \
                    -DCMAKE_INSTALL_EXAMPLEDIR=${datadir}/vpl/examples \
                    -DCMAKE_INSTALL_LICENSEDIR=${datadir}/vpl/licensing \
"
do_install_append () {
        # delete examples source files
        find "${D}${datadir}/vpl/examples/" -type d \! -name 'examples' \! -name 'content' -exec rm -rf {} +
}


FILES_${PN}-examples = "${bindir}/dpcpp-blur \
                        ${bindir}/hello-* \
                        ${datadir}/vpl/examples \
                        "
FILES_${PN} += "${datadir}"
