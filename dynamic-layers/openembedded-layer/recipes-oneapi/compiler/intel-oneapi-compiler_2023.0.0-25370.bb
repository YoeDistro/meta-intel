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
                     file://opt/intel/oneapi/lib/licensing/compiler/openmp/third-party-programs.txt;md5=609c84e7d3e09211abf0dff68d29f7f0 \
                     file://opt/intel/oneapi/lib/licensing/compiler/c/third-party-programs.txt;md5=0c2600ab9b332805bca9d7f86b772cbc \
                     file://opt/intel/oneapi/lib/licensing/opencl/EULA.txt;md5=7bfc91523de2e84e7131d0eacf2827d4 \
                     file://opt/intel/oneapi/lib/licensing/opencl/TBB_TPP.txt;md5=0a69156543e3e115c673c6041b6aee92 \
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

SRC_URI[runtime-compilers.sha256sum] = "46ef0bb45b3d0e812350f0e3666f41067b2850c8ba6fc819f64a489c9d620326"
SRC_URI[common-compilers.sha256sum] = "40da8f3a627b14b333c8992659502e52162fb71267d032f94d5952a1ba5b5571"
SRC_URI[runtime-dpcpp-cpp.sha256sum] = "108b4147a4dee19ae0fdd24e534a9c207230d813948205373cd14b82bdd4f68c"
SRC_URI[common-dpcpp-cpp.sha256sum] = "0ab43f8a24e84fa094f01b2ecdc0d74394273e60291f2f725bd49734dd4d0050"
SRC_URI[runtime-openmp.sha256sum] = "709f9a294db834e21d76ffc925adfa00108c0e862d6a21e484e0e43fbd5d2c17"
SRC_URI[common-openmp.sha256sum] = "f14e031161bd732d7cd4a8b0ae4aa9ee81c7588c6d63bbea9efb3ddeffe72298"
SRC_URI[runtime-opencl.sha256sum] = "9464fe5ef7454fd86868503afc4eafedffcae8356e1ebce9db1a32e8e3376abe"

S = "${WORKDIR}/${BPN}"

inherit bin_package update-alternatives

ALTERNATIVE:${PN} = "Intel_FPGA_SSG_Emulator.icd"
ALTERNATIVE_LINK_NAME[Intel_FPGA_SSG_Emulator.icd] = "/etc/OpenCL/vendors/intel64-fpgaemu.icd"
ALTERNATIVE_TARGET[Intel_FPGA_SSG_Emulator.icd] = "/opt/intel/oneapi/lib/etc/Intel_FPGA_SSG_Emulator.icd"
ALTERNATIVE_PRIORITY[Intel_FPGA_SSG_Emulator.icd] = "328736233"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

RDEPENDS:${PN} += "perl elfutils ocl-icd level-zero-loader zlib tbb libelf setup-intel-oneapi-env"
INSANE_SKIP:${PN} += "textrel dev-so file-rdeps staticdev"

# The libomptarget.rtl.x86_64.so library is not supported on Yocto 3.1+ due to using obsolete libffi.so.6
do_install:append () {
    rm -f ${D}/opt/intel/oneapi/lib/libomptarget.rtl.x86_64.so
    echo "/opt/intel/oneapi/lib/emu/libintelocl_emu.so" > ${D}/opt/intel/oneapi/lib/etc/Intel_FPGA_SSG_Emulator.icd
}
SKIP_FILEDEPS:${PN} = '1'
