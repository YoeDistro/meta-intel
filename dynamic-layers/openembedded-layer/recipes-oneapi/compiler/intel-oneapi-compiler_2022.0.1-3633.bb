SUMMARY = "Intel® oneAPI DPC++/C++ Compiler & Intel® C++ Compiler Classic runtime common files"
DESCRIPTION = "Intel® C++ Compiler Classic generates applications\
 that can run on Intel® 64 architecture and the IA-32 architecture.\
 Intel® oneAPI Data Parallel C++ (DPC++) is an open alternative \
 to single-architecture proprietary languages. It allows developers to reuse \
 code across hardware targets (CPUs and accelerators such as GPUs and FPGAs) \
 and also perform custom tuning for a specific accelerator."
HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/documentation/cpp-compiler-developer-guide-and-reference/top/introducing-the-intel-c-compiler-classic.html"

LICENSE="EULA"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/lib/licensing/compiler/Intel%20Developer%20Tools%20EULA;md5=7bfc91523de2e84e7131d0eacf2827d4 \
                     file://opt/intel/oneapi/lib/licensing/compiler/openmp/third-party-programs.txt;md5=da72b17a4a1efef54faf14df4a703282 \
                     file://opt/intel/oneapi/lib/licensing/compiler/c/third-party-programs.txt;md5=3414f5649b09160ade46d4981f3452fe \
                     file://opt/intel/oneapi/lib/licensing/opencl/EULA.txt;md5=7bfc91523de2e84e7131d0eacf2827d4 \
                     file://opt/intel/oneapi/lib/licensing/opencl/TBB_TPP.txt;md5=0a69156543e3e115c673c6041b6aee92 \
                     file://opt/intel/oneapi/lib/licensing/opencl/redist.txt;md5=32fb3b7d1c0a92ea577fe2e4b0dfa663 \
                     file://opt/intel/oneapi/lib/licensing/opencl/third-party-programs.txt;md5=c660b8b0891e780a62da6a198e80e9ac \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-compilers-${PV}_amd64.deb;subdir=${BPN};name=runtime-compilers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-compilers-common-${PV}_all.deb;subdir=${BPN};name=common-compilers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-dpcpp-cpp-${PV}_amd64.deb;subdir=${BPN};name=runtime-dpcpp-cpp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-dpcpp-cpp-common-${PV}_all.deb;subdir=${BPN};name=common-dpcpp-cpp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-openmp-${PV}_amd64.deb;subdir=${BPN};name=runtime-openmp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-openmp-common-${PV}_all.deb;subdir=${BPN};name=common-openmp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-opencl-${PV}_amd64.deb;subdir=${BPN};name=runtime-opencl \
            "

SRC_URI[runtime-compilers.sha256sum] = "8951fd8a0946095609f0ef7bb2b51c698fc0315455a76d268a54851936c07db9"
SRC_URI[common-compilers.sha256sum] = "c592482f68b131fff0623fdc978c592950f02d7f35f6795587e93a28fe1d3c31"
SRC_URI[runtime-dpcpp-cpp.sha256sum] = "9e26b9c5321281d829d62542c22552182d00255ebc2f8a6e179b95515c63c24d"
SRC_URI[common-dpcpp-cpp.sha256sum] = "9cd0957a75f5ad45b208f2c1c8ee2aa032e335ada9193ca69f8ac5a7cb548d02"
SRC_URI[runtime-openmp.sha256sum] = "86fb0d6e3a34217cc30594e9f21e89070425c6c56773edae4d5a86730239c171"
SRC_URI[common-openmp.sha256sum] = "5bd2fc7a1605545fc8536ec0e8d80c982f1feb7dc6eed5b45adf3cd0370e0a98"
SRC_URI[runtime-opencl.sha256sum] = "65d73c23f07c2419b8a1d22043b73346f25b387d3ec07b48f08177de1835a360"

S = "${WORKDIR}/${BPN}"

inherit bin_package update-alternatives

ALTERNATIVE:${PN} = "Intel_FPGA_SSG_Emulator.icd"
ALTERNATIVE_LINK_NAME[Intel_FPGA_SSG_Emulator.icd] = "/etc/OpenCL/vendors/intel64-fpgaemu.icd"
ALTERNATIVE_TARGET[Intel_FPGA_SSG_Emulator.icd] = "/opt/intel/oneapi/lib/etc/Intel_FPGA_SSG_Emulator.icd"
ALTERNATIVE_PRIORITY[Intel_FPGA_SSG_Emulator.icd] = "328736233"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

RDEPENDS:${PN} += "perl elfutils ocl-icd level-zero-loader zlib tbb libelf setup-intel-oneapi-env"
INSANE_SKIP:${PN} += "textrel dev-so"

# The libomptarget.rtl.x86_64.so library is not supported on Yocto 3.1+ due to using obsolete libffi.so.6
do_install:append () {
    rm -f ${D}/opt/intel/oneapi/lib/libomptarget.rtl.x86_64.so
    echo "/opt/intel/oneapi/lib/emu/libintelocl_emu.so" > ${D}/opt/intel/oneapi/lib/etc/Intel_FPGA_SSG_Emulator.icd
}
