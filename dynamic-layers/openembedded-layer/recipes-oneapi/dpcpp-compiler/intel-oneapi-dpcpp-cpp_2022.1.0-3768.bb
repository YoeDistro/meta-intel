SUMMARY = "Intel® oneAPI DPC++/C++ Compiler"
DESCRIPTION = "The Intel® oneAPI DPC++/C++ Compiler provides optimizations \
that help your applications run faster on Intel® 64 architectures with support \
for the latest C, C++, and SYCL language standards. This compiler produces \
optimized code that can run significantly faster by taking advantage of the \
ever-increasing core count and vector register width in Intel® Xeon® processors \
and compatible processors."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/tools/oneapi/dpc-compiler.html"

LICENSE="EULA"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/compiler/2022.1.0/licensing/credist.txt;md5=f772f43824318f221f6363883e44201f \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-dpcpp-cpp-2022.1.0-${PV}_amd64.deb;subdir=${BPN};name=icx-compiler \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-2022.1.0-${PV}_amd64.deb;subdir=${BPN};name=compiler-linker \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-common-2022.1.0-${PV}_all.deb;subdir=${BPN};name=shared-common \
            "

SRC_URI[icx-compiler.sha256sum] = "c525c416c61cac9aa4e22fec78be95b88d33bca13360d0444c22da52eb8dd318"
SRC_URI[compiler-linker.sha256sum] = "3185dc02473b3d3a34234dd82a17c285e43701455636f93be235b52c2ac6f2c6"
SRC_URI[shared-common.sha256sum] = "c9961b90a9c4f4636c78292b5ba3d6cf6bbd7081f1ce4a4690bb7db94596226e"

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
