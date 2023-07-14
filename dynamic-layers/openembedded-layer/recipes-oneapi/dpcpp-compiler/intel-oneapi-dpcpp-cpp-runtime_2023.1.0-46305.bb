SUMMARY = "Intel® oneAPI DPC++/C++ Compiler runtime files"
DESCRIPTION = "The Intel® oneAPI DPC++/C++ Compiler provides optimizations \
that help your applications run faster on Intel® 64 architectures with support \
for the latest C, C++, and SYCL language standards. This compiler produces \
optimized code that can run significantly faster by taking advantage of the \
ever-increasing core count and vector register width in Intel® Xeon® processors \
and compatible processors."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/tools/oneapi/dpc-compiler.html"

LICENSE="EULA"

COMPILERMAINVER = "2023.1.0"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/licensing/${COMPILERMAINVER}/license.htm;md5=f721d37d5ef65590e052bc47e15feec3 \
                     "
COMPILERDOTVER = "2023.1.0-43473"
DEVUTILITVERSION = "2021.9.0-2021.9.0-44447"
SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-runtime-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=dpcpp-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=dpcpp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-runtime-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=compiler-shared-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-vars-${COMPILERDOTVER}_all.deb;subdir=${BPN};name=common-vars \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-openmp-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=openmp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-licensing-${COMPILERMAINVER}-${COMPILERDOTVER}_all.deb;subdir=${BPN};name=license \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-dev-utilities-${DEVUTILITVERSION}_amd64.deb;subdir=${BPN};name=dev-utils \
            "

SRC_URI[dpcpp-runtime.sha256sum] = "d0eec67fe7e3b36c8c5b1d07a17779f739fd2fd1881f6b1848169faffcb855c6"
SRC_URI[dpcpp-common.sha256sum] = "9c117b25ddee699d1a8162ab101b9c23beeea5e9a3f2409414ee6bbc78d6593b"
SRC_URI[compiler-shared-runtime.sha256sum] = "fa3f4c23f527f1ced767fef56c022e252daedd08fab752ec653985f178d509b6"
SRC_URI[common-vars.sha256sum] = "5eced1aad7100cdeff317798046589e4b08b5095602e23e9a87adc0fb3e42e52"
SRC_URI[openmp-common.sha256sum] = "d4cb4adfbfc1ba289996f4b55e85fef73348ee193314552b08a3c59c2af2c8e0"
SRC_URI[license.sha256sum] = "5a00affd150f0f8475b999f2a7719f6aaffaf67eaf07dd70072295299451fb9a"
SRC_URI[dev-utils.sha256sum] = "2d34f9d4f746b2b6468ed033628ffbe7838bf1d0bafcc3650d13d5740b10157f"

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
