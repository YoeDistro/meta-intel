SUMMARY = "Intel® oneAPI DPC++/C++ Compiler runtime files"
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
                     file://opt/intel/oneapi/licensing/${COMPILERMAINVER}/license.htm;md5=f721d37d5ef65590e052bc47e15feec3 \
                     "

COMPILERDOTVER = "2023.0.0-25325"
DEVUTILITVERSION = "2021.8.0-2021.8.0-25328"
SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-runtime-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=dpcpp-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=dpcpp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-runtime-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=compiler-shared-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-vars-${COMPILERDOTVER}_all.deb;subdir=${BPN};name=common-vars \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-openmp-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=openmp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-licensing-${COMPILERMAINVER}-${COMPILERDOTVER}_all.deb;subdir=${BPN};name=license \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-dev-utilities-${DEVUTILITVERSION}_amd64.deb;subdir=${BPN};name=dev-utils \
            "

SRC_URI[dpcpp-runtime.sha256sum] = "63f63e8e1f02ce34baec35d4892b89a940a745a488fff8d1e04437ab079e7543"
SRC_URI[dpcpp-common.sha256sum] = "ef2791532a7f5afee609e2e81ddadebf1306a248a84b711959a1be3112d8a509"
SRC_URI[compiler-shared-runtime.sha256sum] = "39c8f307c67129ef8d466561f39bbed3445548fc9bba48aabe58314cbcf0ae91"
SRC_URI[common-vars.sha256sum] = "a7779af0502470d07db51789abc2d31a7f80e9ffd6ac77ed3ffcaad79dbecd82"
SRC_URI[openmp-common.sha256sum] = "b324474eae8bd5bd010df52de880eea55566e91dc043462879fc295ea6b3039c"
SRC_URI[license.sha256sum] = "801db78b05c548060638dc02b819ee9b4a714b920aca22c8d57cf89ec619e130"
SRC_URI[dev-utils.sha256sum] = "df8513238bb753f9d169ffc7967784478ea10a2ab3cb26246c66a0d51d96d33f"

S = "${WORKDIR}/${BPN}"

inherit bin_package

RDEPENDS:${PN} += "virtual/opencl-icd zlib tbb level-zero-loader bash tcsh"
SKIP_FILEDEPS:${PN} = '1'

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# doesn't have GNU_HASH (didn't pass LDFLAGS?)
INSANE_SKIP:${PN} += "textrel dev-so dev-elf ldflags already-stripped staticdev rpaths arch useless-rpaths file-rdeps"

FILES_SOLIBSDEV = ""
BBCLASSEXTEND = "native nativesdk"
