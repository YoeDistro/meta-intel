SUMMARY = "Common clang is a thin wrapper library around clang"
DESCRIPTION = "Common clang has OpenCL-oriented API and is capable \
 to compile OpenCL C kernels to SPIR-V modules."

LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8a15bf1416762a09ece07e44c79118c"

SRC_URI = "git://github.com/intel/opencl-clang.git;branch=ocl-open-100;protocol=https \
           file://0001-don-t-redefine-LLVM_TABLEGEN_EXE.patch \
           file://0001-Building-in-tree-with-LLVM-10.0-with-the-LLVM_LINK_L.patch \
           "
SRC_URI_append_class-native = " file://0002-make-sure-only-static-libraries-linked-for-native-bu.patch"

SRCREV = "9f0c2c0f5ddea1accc921aed4c94bc52c1b85637"

S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "clang"
DEPENDS_append_class-target = " opencl-clang-native"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

EXTRA_OECMAKE += "\
                  -DLLVM_TABLEGEN_EXE=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
                  -DCMAKE_SKIP_RPATH=TRUE \
                  "

do_install_append_class-native() {
        install -d ${D}${bindir}
        install -m 0755 ${B}/linux_linker/linux_resource_linker ${D}${bindir}/
}

BBCLASSEXTEND = "native nativesdk"
