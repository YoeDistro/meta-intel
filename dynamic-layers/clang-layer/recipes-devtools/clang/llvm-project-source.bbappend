FILESEXTRAPATHS:prepend:intel-x86-common := "${THISDIR}/files:"

SRC_URI_LLVM12_PATCHES = " \
                   file://llvm12-0001-Remove-__IMAGE_SUPPORT__-macro-for-SPIR-since-SPIR-d.patch \
                   file://llvm12-0002-Avoid-calling-ParseCommandLineOptions-in-BackendUtil.patch \
                   file://llvm12-0003-Support-cl_ext_float_atomics.patch \
                   file://llvm12-0004-ispc-12_0_disable-A-B-A-B-and-BSWAP-in-InstCombine.patch \
                   file://llvm12-0005-ispc-12_0_fix_for_2111.patch \
                   file://llvm12-0006-OpenCL-Add-cl_khr_integer_dot_product.patch \
                   file://llvm12-0007-OpenCL-3.0-support.patch \
                   "

SRC_URI:append:intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '12.0.0', ' ${SRC_URI_LLVM12_PATCHES} ', '', d)}"
