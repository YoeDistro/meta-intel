SUMMARY = "Intel® oneAPI Math Kernel Library (oneMKL)"
DESCRIPTION = "The Intel® oneAPI Math Kernel Library (oneMKL) is a computing \
 math library of highly optimized and extensively parallelized routines \
 for applications that require maximum performance. oneMKL contains \
 the high-performance optimizations from the full Intel® Math Kernel Library \
 for CPU architectures (with C/Fortran programming language interfaces)\
 and adds to them a set of DPC++ programming language interfaces for \
 achieving performance on various CPU architectures \
 and Intel Graphics Technology for certain key functionalities."
HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/onemkl.html"

LICENSE = "ISSL"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/lib/licensing/mkl/license.txt;md5=0c8b92562c3c165187e2c15ff638855e \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-benchmarks.txt;md5=cb98e1a1f14c05ea85a979ea8982e7a4 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-ipp.txt;md5=a4b2bf15e38f5c1267cdafed18bc0b09 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-openmp.txt;md5=6b3c1aa2a11393060074c0346ce21e49 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-safestring.txt;md5=c3aeee91c6d35a0f0753aed6c2633b82 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs.txt;md5=980965cf1f086d40998ca4981968b6a4 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-mkl-${PV}_amd64.deb;subdir=${BPN};name=runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-mkl-common-${PV}_all.deb;subdir=${BPN};name=common \
            "

SRC_URI[runtime.sha256sum] = "b20e0f7400fbbc55d8489f9f3ef35a8c8df7f5af7d87903bf305703e3a2ebc3b"
SRC_URI[common.sha256sum] = "bff8b2bfedbd09c9e6d0366cca3d4de80af521302bd5938fe6fa0128c6839041"

S = "${WORKDIR}/${BPN}"

inherit bin_package

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

RDEPENDS:${PN} += "tbb intel-oneapi-compiler setup-intel-oneapi-env"
INSANE_SKIP:${PN} = "ldflags textrel dev-so"
