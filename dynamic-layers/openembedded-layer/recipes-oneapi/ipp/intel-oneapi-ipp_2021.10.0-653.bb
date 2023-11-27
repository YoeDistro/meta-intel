DESCRIPTION = "Intel® Integrated Performance Primitives are production-ready \
 building blocks for cross-platform performance. Develop high-performance vision, \
 signal, security, and storage applications with this multithreaded software library."
HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/ipp.html"

LICENSE = "ISSL"

MAXVER = "2021.10"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/ipp/${MAXVER}/share/doc/ipp/licensing/license.txt;md5=d7cdc92ed6c4de1263da879599ddc3e2 \
                     file://opt/intel/oneapi/ipp/${MAXVER}/share/doc/ipp/licensing/third-party-programs.txt;md5=22bd13987911dcf790907041b43081f3 \
                     "
SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-ipp-${MAXVER}-${PV}_amd64.deb;subdir=${BPN};name=ipp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-ipp-common-devel-${MAXVER}-${PV}_all.deb;subdir=${BPN};name=headers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-ipp-common-${MAXVER}-${PV}_all.deb;subdir=${BPN};name=env \
            "

SRC_URI[ipp.sha256sum] = "b51e45c6e691aa46c7136b0ab61f5abe346388433e017a30cf53fd23e92bea07"
SRC_URI[headers.sha256sum] = "342f37ab2f82bc9f4498435f848ee660591c2488b44d988bf6ee96b2a71fd005"
SRC_URI[env.sha256sum] = "731e8c28a3b8b757730cd874d0941de2eb744856128f24ade59d36c12b415bf6"

S = "${WORKDIR}/${BPN}"

COMPATIBLE_HOST:libc-musl = "null"

inherit bin_package

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
INHIBIT_DEFAULT_DEPS = ""

RDEPENDS:${PN} += "tbb setup-intel-oneapi-env"
INSANE_SKIP:${PN} += "ldflags dev-so"
