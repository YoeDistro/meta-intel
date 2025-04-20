SUMMARY = "Intel® oneAPI DPC++/C++ Compiler"
DESCRIPTION = "The Intel® oneAPI DPC++/C++ Compiler provides optimizations \
that help your applications run faster on Intel® 64 architectures with support \
for the latest C, C++, and SYCL language standards. This compiler produces \
optimized code that can run significantly faster by taking advantage of the \
ever-increasing core count and vector register width in Intel® Xeon® processors \
and compatible processors."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/tools/oneapi/dpc-compiler.html"

LICENSE = "EULA"

COMPILERMAINVER = "2024.0"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/compiler/${COMPILERMAINVER}/share/doc/compiler/credist.txt;md5=b41f55af9f479b9570fc35b955d5ba1a \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-dpcpp-cpp-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=icx-compiler \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=compiler-linker \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=shared-common \
            "

SRC_URI[icx-compiler.sha256sum] = "0dcbac766d5a1519d4cf393f5a85e71d19024fef65f77638f3f849796b62cd82"
SRC_URI[compiler-linker.sha256sum] = "e00faea6d797934d62143e4aa70b727ce30f80fdf30769d22122b3051140c236"
SRC_URI[shared-common.sha256sum] = "cf490a4a790f349da79e618359598d3b32312ca3b2639e5d4c84e1cfa2745558"

S = "${WORKDIR}/${BPN}"

inherit bin_package

RDEPENDS:${PN} += "intel-oneapi-dpcpp-cpp-runtime"
SKIP_FILEDEPS:${PN} = '1'

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# doesn't have GNU_HASH (didn't pass LDFLAGS?)
INSANE_SKIP:${PN} += "textrel dev-so dev-elf ldflags already-stripped file-rdeps staticdev rpaths arch useless-rpaths"

FILES_SOLIBSDEV = ""

EXCLUDE_FROM_SHLIBS = "1"
BBCLASSEXTEND = "native nativesdk"
