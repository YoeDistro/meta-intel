DESCRIPTION = "Intel® Integrated Performance Primitives are production-ready \
 building blocks for cross-platform performance. Develop high-performance vision, \
 signal, security, and storage applications with this multithreaded software library."
HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/ipp.html"

LICENSE = "ISSL"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/lib/licensing/ipp/license.txt;md5=4867389dfbeb11811d66cdcbcc8712a6 \
                     file://opt/intel/oneapi/lib/licensing/ipp/third-party-programs.txt;md5=934f899238546b3250e8d1de7262e563 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-ipp-${PV}_amd64.deb;subdir=${BPN};name=runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-ipp-common-${PV}_all.deb;subdir=${BPN};name=common \
            "

SRC_URI[runtime.sha256sum] = "10f611fe4d68e0b8e55cae0c8ed48e25c15e79dd1b7f83d87c2a342fe601485f"
SRC_URI[common.sha256sum] = "dc733ccc61b7c74a3ffa1c9f63a321e678cba8e09afd1c4ac882954d4602ed97"

S = "${WORKDIR}/${BPN}"

COMPATIBLE_HOST:libc-musl = "null"

inherit bin_package

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

RDEPENDS:${PN} += "tbb setup-intel-oneapi-env"
INSANE_SKIP:${PN} += "ldflags dev-so"

# Some libraries are linking against the unversioned lib and there's no soname entry.
# Workaround errors like:
# Problem: conflicting requests
#  - nothing provides libippcore_tl_omp.so()(64bit) needed by intel-oneapi-ipp-2021.3.0+333-r0.skylake_64
do_install:append () {
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_omp.so.10.2 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_omp.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_tbb.so.10.2 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_tbb.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_omp.so.10.2 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_omp.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_tbb.so.10.2 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_tbb.so
}
