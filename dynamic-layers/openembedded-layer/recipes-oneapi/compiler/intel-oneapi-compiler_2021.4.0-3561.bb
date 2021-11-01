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
                     file://opt/intel/oneapi/lib/licensing/compiler/Intel%20Developer%20Tools%20EULA;md5=504ce70b21e83ed3eadf647715fe8693 \
                     file://opt/intel/oneapi/lib/licensing/compiler/openmp/third-party-programs.txt;md5=da72b17a4a1efef54faf14df4a703282 \
                     file://opt/intel/oneapi/lib/licensing/compiler/c/third-party-programs.txt;md5=e68c504274cfdae7628970517223a823 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-compilers-${PV}_amd64.deb;subdir=${BPN};name=runtime-compilers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-compilers-common-${PV}_all.deb;subdir=${BPN};name=common-compilers \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-dpcpp-cpp-${PV}_amd64.deb;subdir=${BPN};name=runtime-dpcpp-cpp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-dpcpp-cpp-common-${PV}_all.deb;subdir=${BPN};name=common-dpcpp-cpp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-openmp-${PV}_amd64.deb;subdir=${BPN};name=runtime-openmp \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-openmp-common-${PV}_all.deb;subdir=${BPN};name=common-openmp \
            "

SRC_URI[runtime-compilers.sha256sum] = "37ee9277b8b7b5486ec2a44cb66575b94dcc2b927f1825f88cdf2c1de54de656"
SRC_URI[common-compilers.sha256sum] = "9d446a793e2a3b5749c949b8985c6cc05125df107458cd9640e39e32650f9365"
SRC_URI[runtime-dpcpp-cpp.sha256sum] = "894c9d0efe069a2a2757338ce6b005f560e34319d3f6321f794733937f85718b"
SRC_URI[common-dpcpp-cpp.sha256sum] = "ff8303b4b4da9f03302cef00fb275c875a3876f34c242813707d461ad4c9628d"
SRC_URI[runtime-openmp.sha256sum] = "65dca00697b93a31df401de73ad38e2e65d8bcc662b2d2a8ad561551fbde0620"
SRC_URI[common-openmp.sha256sum] = "53a59a72051760bcf04186beaa17656082eacec2ecaee86bfde7f6e7560457bf"

S = "${WORKDIR}/${BPN}"

inherit bin_package update-alternatives

ALTERNATIVE:${PN} = "Intel_FPGA_SSG_Emulator.icd"
ALTERNATIVE_LINK_NAME[Intel_FPGA_SSG_Emulator.icd] = "/etc/OpenCL/vendors/intel64-fpgaemu.icd"
ALTERNATIVE_TARGET[Intel_FPGA_SSG_Emulator.icd] = "/opt/intel/oneapi/lib/etc/Intel_FPGA_SSG_Emulator.icd"
ALTERNATIVE_PRIORITY[Intel_FPGA_SSG_Emulator.icd] = "328736233"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

RDEPENDS:${PN} += "perl elfutils opencl-icd-loader level-zero-loader zlib tbb libelf setup-intel-oneapi-env"
INSANE_SKIP:${PN} += "textrel dev-so"

# The libomptarget.rtl.x86_64.so library is not supported on Yocto 3.1+ due to using obsolete libffi.so.6
do_install:append () {
    rm -f ${D}/opt/intel/oneapi/lib/libomptarget.rtl.x86_64.so
    echo "/opt/intel/oneapi/lib/emu/libintelocl_emu.so" > ${D}/opt/intel/oneapi/lib/etc/Intel_FPGA_SSG_Emulator.icd
}
