SUMMARY = "Intel® oneAPI DPC++/C++ Compiler"
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
                     file://opt/intel/oneapi/compiler/${COMPILERMAINVER}/licensing/credist.txt;md5=319d5cc98a28a36b7ea38526a34a66b9 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-dpcpp-cpp-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=icx-compiler \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-${COMPILERMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=compiler-linker \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-common-${COMPILERMAINVER}-${PV}_all.deb;subdir=${BPN};name=shared-common \
            "

SRC_URI[icx-compiler.sha256sum] = "5acd0dc9fc540355bc19317be5d79686f2acb90ac28486dd0717c423e4c94326"
SRC_URI[compiler-linker.sha256sum] = "014104b9721d7fca7bb2529ed810f27e9a73410698e9cc643ded7f909a7f17cf"
SRC_URI[shared-common.sha256sum] = "894eaf13bfe840d2f5df49685e9f5bb9ca8212bc2c8ddf848adcabb3806db8bd"

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
