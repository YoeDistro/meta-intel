DESCRIPTION = "Intel® Integrated Performance Primitives are production-ready \
 building blocks for cross-platform performance. Develop high-performance vision, \
 signal, security, and storage applications with this multithreaded software library."
HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/ipp.html"

LICENSE = "ISSL"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/lib/licensing/ipp/license.txt;md5=0c8b92562c3c165187e2c15ff638855e \
                     file://opt/intel/oneapi/lib/licensing/ipp/third-party-programs.txt;md5=6cd9ad51f3b5cdfd4a355fa5599a6c03 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-ipp-${PV}_amd64.deb;subdir=${BPN};name=runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-ipp-common-${PV}_all.deb;subdir=${BPN};name=common \
            "

SRC_URI[runtime.sha256sum] = "98cd14bf635969d57bf9ea7bb27b90f27f72404f1927aa486a2bcf3c20a0f303"
SRC_URI[common.sha256sum] = "1aac41633179e824567b90afd39b8405db30df79a027421568df019b2b51bdd1"

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
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_omp.so.10.4 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_omp.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_tbb.so.10.4 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_tbb.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_omp.so.10.4 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_omp.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_tbb.so.10.4 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_tbb.so
}
