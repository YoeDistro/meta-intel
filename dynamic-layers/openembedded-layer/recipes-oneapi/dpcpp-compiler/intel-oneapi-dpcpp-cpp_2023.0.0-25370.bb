SUMMARY = "Intel® oneAPI DPC++/C++ Compiler"
DESCRIPTION = "The Intel® oneAPI DPC++/C++ Compiler provides optimizations \
that help your applications run faster on Intel® 64 architectures with support \
for the latest C, C++, and SYCL language standards. This compiler produces \
optimized code that can run significantly faster by taking advantage of the \
ever-increasing core count and vector register width in Intel® Xeon® processors \
and compatible processors."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/tools/oneapi/dpc-compiler.html"

LICENSE="EULA"

COMPILERMAINVER = "2023.0.0"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/compiler/${COMPILERMAINVER}/licensing/credist.txt;md5=0347b85fa78c51d1156ccc367202c66c \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-dpcpp-cpp-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=icx-compiler \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=compiler-linker \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=shared-common \
            "

SRC_URI[icx-compiler.sha256sum] = "2aa3f782f8c5ea1920d7fbabd7758b95b110764c53418f73d3c156164d84f44a"
SRC_URI[compiler-linker.sha256sum] = "2fa8d9f8bdbada124da24f1d161b4b882f85f54bcc341149ea5a5d1e9dcc826d"
SRC_URI[shared-common.sha256sum] = "a1dcc49438fc1a83474a6f3bc4543ca6cf7dcb0b5a8dcd1bdffed9ac488825fd"

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
