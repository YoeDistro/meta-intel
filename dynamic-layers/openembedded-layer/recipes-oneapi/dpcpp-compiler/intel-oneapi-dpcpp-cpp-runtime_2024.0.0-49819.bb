SUMMARY = "Intel® oneAPI DPC++/C++ Compiler runtime files"
DESCRIPTION = "The Intel® oneAPI DPC++/C++ Compiler provides optimizations \
that help your applications run faster on Intel® 64 architectures with support \
for the latest C, C++, and SYCL language standards. This compiler produces \
optimized code that can run significantly faster by taking advantage of the \
ever-increasing core count and vector register width in Intel® Xeon® processors \
and compatible processors."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/tools/oneapi/dpc-compiler.html"

LICENSE="EULA"

COMPILERMAINVER = "2024.0"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/licensing/${COMPILERMAINVER}/licensing/${COMPILERMAINVER}/license.htm;md5=5ff64c6ff3ef98089ed69360e7a84c39 \
                     "
COMPILERDOTVER = "2024.0.0-49406"
DEVUTILITVERSION = "2024.0-2024.0.0-49320"
SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-runtime-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=dpcpp-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=dpcpp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-runtime-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=compiler-shared-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-vars-${COMPILERDOTVER}_all.deb;subdir=${BPN};name=common-vars \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-openmp-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=openmp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-openmp-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=openmp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-licensing-${COMPILERMAINVER}-${COMPILERDOTVER}_all.deb;subdir=${BPN};name=license \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-dev-utilities-${DEVUTILITVERSION}_amd64.deb;subdir=${BPN};name=dev-utils \
            "

SRC_URI[dpcpp-runtime.sha256sum] = "e24f0ba69daf3f66ceaf23d5e632f183cdb90bac69f65407fdb4407fc9034f33"
SRC_URI[dpcpp-common.sha256sum] = "f5a3db6a725598224edf1099260955aee3e36beadcaed2af5b8b453e873a82fa"
SRC_URI[compiler-shared-runtime.sha256sum] = "bce010cbe076259ddd3feb8e69792869e22fccd5b4e2c9af9e815826f2c1a394"
SRC_URI[common-vars.sha256sum] = "368553c99db1b52060b8225355336778be0b00e5991d0f769c42f891c6328750"
SRC_URI[openmp.sha256sum] = "154ff1e81adfdc872ba1d47bd860de70d62188417c7128422435dfd0ceca62fe"
SRC_URI[openmp-common.sha256sum] = "8217001d78311cbef97dd139e684c6767932b532309c3843ba57d7894d15c07d"
SRC_URI[license.sha256sum] = "9f9c8a12fc0bc82ab5b71e118e66745eff23f42224eba304068225b366cd74b6"
SRC_URI[dev-utils.sha256sum] = "c675d960a5abca361cead9217d6e74adee499ee0a095c4e44092bd710b304d50"

S = "${WORKDIR}/${BPN}"

inherit bin_package

RDEPENDS:${PN} += "virtual-opencl-icd zlib tbb level-zero-loader bash tcsh"
SKIP_FILEDEPS:${PN} = '1'

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# doesn't have GNU_HASH (didn't pass LDFLAGS?)
INSANE_SKIP:${PN} += "textrel dev-so dev-elf ldflags already-stripped staticdev rpaths arch useless-rpaths file-rdeps"

FILES_SOLIBSDEV = ""
BBCLASSEXTEND = "native nativesdk"
