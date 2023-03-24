DESCRIPTION = "Intel® Integrated Performance Primitives are production-ready \
 building blocks for cross-platform performance. Develop high-performance vision, \
 signal, security, and storage applications with this multithreaded software library."
HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/ipp.html"

LICENSE = "ISSL"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/lib/licensing/ipp/license.txt;md5=d7cdc92ed6c4de1263da879599ddc3e2 \
                     file://opt/intel/oneapi/lib/licensing/ipp/third-party-programs.txt;md5=8a4cf3c62b53158d35a7bb8b85e44bf9 \
                     "
MAXVER = "2021.7.0"
SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-ipp-${PV}_amd64.deb;subdir=${BPN};name=runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-ipp-common-${PV}_all.deb;subdir=${BPN};name=common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-ipp-common-devel-${MAXVER}-${PV}_all.deb;subdir=${BPN};name=headers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-ipp-common-${MAXVER}-${PV}_all.deb;subdir=${BPN};name=env \
            "

SRC_URI[runtime.sha256sum] = "ffb637d9d34c28de157d7cff3bc39b5306b5958bc9a56ee40739b0a7d0da5169"
SRC_URI[common.sha256sum] = "3bc7f2258e115e1e5ad6457f404b2cf3e75fc4f33acf2ceed3098052bb875bac"
SRC_URI[headers.sha256sum] = "9c23b560ee4a035656c2f502aad171c65386d62f7180cfe099b08bb73adbf0a9"
SRC_URI[env.sha256sum] = "c870add93cb529c23dabaf1e62bbb4d059a38ca85d930e5187989aa79a19b7c2"

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
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_omp.so.10.6 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_omp.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_tbb.so.10.6 ${D}/opt/intel/oneapi/lib/intel64/libippcore_tl_tbb.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_omp.so.10.6 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_omp.so
    install -m 755 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_tbb.so.10.6 ${D}/opt/intel/oneapi/lib/intel64/libippi_tl_tbb.so
}
