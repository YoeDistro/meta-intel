SUMMARY = "Intel® C++ Compiler Classic runtime common files"

DESCRIPTION = "Intel® C++ Compiler Classic generates applications \
that can run on Intel® 64 architecture."

HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/documentation/cpp-compiler-developer-guide-and-reference/top/introducing-the-intel-c-compiler-classic.html"

LICENSE="EULA"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/lib/licensing/compiler/Intel%20Developer%20Tools%20EULA;md5=7bfc91523de2e84e7131d0eacf2827d4 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-compilers-${PV}_amd64.deb;subdir=${BPN};name=runtime-compilers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-compilers-common-${PV}_all.deb;subdir=${BPN};name=common-compilers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-classic-fortran-shared-runtime-2022.1.0-${PV}_amd64.deb;subdir=${BPN};name=shared-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-2022.1.0-${PV}_amd64.deb;subdir=${BPN};name=compiler-linker \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-common-2022.1.0-${PV}_all.deb;subdir=${BPN};name=dpcpp-common \
            "

SRC_URI[runtime-compilers.sha256sum] = "0f27cbde635f30e79329248398e36e5610aa78aeda4ebc9ed1f3538ffbf0ea8d"
SRC_URI[common-compilers.sha256sum] = "7347588844ad9e86291586c049185381ec960cde09b6fd220c626fb865f8865c"
SRC_URI[shared-runtime.sha256sum] = "77b7201b5fe991152528ec40d586114edef12bb79c1a1a8fbfd6bd23c2aba5e1"
SRC_URI[compiler-linker.sha256sum] = "3185dc02473b3d3a34234dd82a17c285e43701455636f93be235b52c2ac6f2c6"
SRC_URI[dpcpp-common.sha256sum] = "5bc6452a32f5781c96498515d061d8fe9d7bba13b41eb983fe9bb0f792621906"

S = "${WORKDIR}/${BPN}"

inherit bin_package update-alternatives

RDEPENDS:${PN} += " ocl-icd zlib"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# doesn't have GNU_HASH (didn't pass LDFLAGS?)
INSANE_SKIP:${PN} += "textrel dev-so dev-elf ldflags"
FILES_SOLIBSDEV = ""


do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${bindir}/
    install -d ${D}${libdir}/licensing/compiler/
    cp -r ${S}/opt/intel/oneapi/compiler/2022.1.0/linux/bin/intel64/* ${D}${bindir}/
    cp -r ${S}/opt/intel/oneapi/lib/intel64/*.so* ${D}${libdir}/
    cp -r ${S}/opt/intel/oneapi/lib/*.so* ${D}${libdir}/
    cp -r ${S}/opt/intel/oneapi/lib/*.a ${D}${libdir}/
    cp -r ${S}/opt/intel/oneapi/lib/libsycl-fallback-cstring.o ${D}${libdir}/
    cp -r ${S}/opt/intel/oneapi/lib/libsycl-fallback-cstring.spv ${D}${libdir}/
    cp -r ${S}/opt/intel/oneapi/lib/licensing/compiler/* ${D}${libdir}/licensing/compiler/
    cp -r ${S}/opt/intel/oneapi/compiler/2022.1.0/linux/compiler/lib/intel64_lin/* ${D}${libdir}/
    cp -r ${S}/opt/intel/oneapi/compiler/2022.1.0/linux/lib/clang ${D}${libdir}/
    rm -rf ${D}/${bindir}/*.so*
    rm -rf ${D}/opt
}

BBCLASSEXTEND = "native nativesdk"
