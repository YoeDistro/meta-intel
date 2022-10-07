SUMMARY = "Intel® C++ Compiler Classic"

DESCRIPTION = "Intel® C++ Compiler Classic generates applications \
that can run on Intel® 64 architecture."

HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/documentation/cpp-compiler-developer-guide-and-reference/top/introducing-the-intel-c-compiler-classic.html"

LICENSE="EULA"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/licensing/2022.1.0/license.htm;md5=f721d37d5ef65590e052bc47e15feec3 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-and-cpp-classic-2022.1.0-${PV}_amd64.deb;subdir=${BPN};name=classic-compiler \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-licensing-2022.1.0-2022.1.0-161_all.deb;subdir=${BPN};name=license \
            "

SRC_URI[classic-compiler.sha256sum] = "0407cf12127f641f1e1b50e8e8e3c6c9cd27be40b849d697401fa8c140604c23"
SRC_URI[license.sha256sum] = "30f36ef653964ac629ce77c2c2d21a923c7ba4ff88936c39a8f39237b7446cca"

S = "${WORKDIR}/${BPN}"

inherit bin_package update-alternatives
RDEPENDS:${PN} += " intel-oneapi-runtime-compilers"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

do_install () {
    install -d ${D}/${bindir}/
    cp -r ${S}/opt/intel/oneapi/compiler/2022.1.0/linux/bin/intel64/* ${D}${bindir}/
}
BBCLASSEXTEND = "native nativesdk"
