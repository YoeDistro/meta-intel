SUMMARY = "Common clang is a thin wrapper library around clang"
DESCRIPTION = "Common clang has OpenCL-oriented API and is capable \
 to compile OpenCL C kernels to SPIR-V modules."

LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8a15bf1416762a09ece07e44c79118c"

SRC_URI = "git://github.com/intel/opencl-clang.git;branch=ocl-open-80;protocol=https"

SRCREV = "daf5e4dd718477ae8cf89a283c653939d9182f15"

S = "${WORKDIR}/git"

inherit native

do_configure[noexec] = "1"

do_compile() {
        ${CC} linux_linker/linux_resource_linker.cpp -o linux_resource_linker
}

do_install() {
        install -d ${D}${bindir}
        install -m 0755 linux_resource_linker ${D}${bindir}/
}
