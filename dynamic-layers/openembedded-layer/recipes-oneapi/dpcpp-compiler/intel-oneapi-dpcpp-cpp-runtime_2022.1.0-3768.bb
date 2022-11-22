SUMMARY = "Intel® oneAPI DPC++/C++ Compiler runtime files"
DESCRIPTION = "The Intel® oneAPI DPC++/C++ Compiler provides optimizations \
that help your applications run faster on Intel® 64 architectures with support \
for the latest C, C++, and SYCL language standards. This compiler produces \
optimized code that can run significantly faster by taking advantage of the \
ever-increasing core count and vector register width in Intel® Xeon® processors \
and compatible processors."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/tools/oneapi/dpc-compiler.html"

LICENSE="EULA"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/licensing/2022.1.0/license.htm;md5=f721d37d5ef65590e052bc47e15feec3 \
                     "
SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-runtime-2022.1.0-${PV}_amd64.deb;subdir=${BPN};name=dpcpp-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-dpcpp-cpp-common-2022.1.0-${PV}_all.deb;subdir=${BPN};name=dpcpp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-compiler-shared-runtime-2022.1.0-${PV}_amd64.deb;subdir=${BPN};name=compiler-shared-runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-vars-2022.1.0-161_all.deb;subdir=${BPN};name=common-vars \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-openmp-common-2022.1.0-${PV}_all.deb;subdir=${BPN};name=openmp-common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-common-licensing-2022.1.0-2022.1.0-161_all.deb;subdir=${BPN};name=license \
            "

SRC_URI[dpcpp-runtime.sha256sum] = "746d30e0794d0b60ab3d7cd31be9d7981b98c2b0305c238f1a9af5612a22b123"
SRC_URI[dpcpp-common.sha256sum] = "5bc6452a32f5781c96498515d061d8fe9d7bba13b41eb983fe9bb0f792621906"
SRC_URI[compiler-shared-runtime.sha256sum] = "c748adc3bac2f0f4c3419f0b1e5bdc34d1508832819deeef68336c290a2fd2be"
SRC_URI[common-vars.sha256sum] = "52a2726739652b4d3021a9f21d8ca664cd5582853b561e421f003b94789a4469"
SRC_URI[openmp-common.sha256sum] = "aaa06115d6cbad606373a61a512162f132c52360f6058970d7a4df55300fb826"
SRC_URI[license.sha256sum] = "30f36ef653964ac629ce77c2c2d21a923c7ba4ff88936c39a8f39237b7446cca"

S = "${WORKDIR}/${BPN}"

inherit bin_package

RDEPENDS:${PN} += "ocl-icd zlib tbb level-zero-loader bash"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# doesn't have GNU_HASH (didn't pass LDFLAGS?)
INSANE_SKIP:${PN} += "textrel dev-so dev-elf ldflags already-stripped staticdev rpaths arch useless-rpaths"

FILES_SOLIBSDEV = ""
BBCLASSEXTEND = "native nativesdk"
