FILESEXTRAPATHS:prepend:intel-x86-common := "${THISDIR}/files:"

SPIRV_SRCREV = "fe4d6b767363a1995ccbfca27f79efb10dcfe110"

SRC_URI_LLVM10_PATCHES = " \
                   file://llvm10-0001-llvm-spirv-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                   file://llvm10-0002-Fix-building-in-tree-with-cmake-DLLVM_LINK_LLVM_DYLI.patch;patchdir=llvm/projects/llvm-spirv \
                   file://llvm10-0003-Add-support-for-cl_ext_float_atomics-in-SPIRVWriter.patch;patchdir=llvm/projects/llvm-spirv \
                   file://BasicBlockUtils-Add-metadata-fixing-in-SplitBlockPre.patch;patchdir=llvm \
                   file://IndVarSimplify-Do-not-use-SCEV-expander-for-IVCount-.patch;patchdir=llvm \
                   file://llvm10-0001-OpenCL-3.0-support.patch \
                   file://llvm10-0002-Add-cl_khr_extended_subgroup-extensions.patch \
                   file://llvm10-0003-Memory-leak-fix-for-Managed-Static-Mutex.patch \
                   file://llvm10-0004-Remove-repo-name-in-LLVM-IR.patch \
                   file://llvm10-0005-Remove-__IMAGE_SUPPORT__-macro-for-SPIR-since-SPIR-d.patch \
                   file://llvm10-0006-Avoid-calling-ParseCommandLineOptions-in-BackendUtil.patch \
                   file://llvm10-0007-support-cl_ext_float_atomics.patch \
                   file://llvm10-0008-ispc-10_0_9_0_fix_for_1767.patch \
                   file://llvm10-0009-ispc-10_0_fix_for_1788.patch \
                   file://llvm10-0010-ispc-10_0_fix_for_1793.patch \
                   file://llvm10-0011-ispc-10_0_fix_for_1844.patch \
                   file://llvm10-0012-ispc-10_0_i8_shuffle_avx512_i8_i16.patch \
                   file://llvm10-0013-ispc-10_0_k_reg_mov_avx512_i8_i16.patch \
                   file://llvm10-0014-ispc-10_0_packed_load_store_avx512skx.patch \
                   file://llvm10-0015-ispc-10_0_vXi1calling_avx512_i8_i16.patch \
                   file://llvm10-basic-block-sections-support.patch \
                   file://llvm10-Enable-the-call-site-info-only-for-g-optimizations.patch \
                   file://llvm10-Replace-MCTargetOptionsCommandFlags.inc-and-CommandF.patch \
                   "

SRC_URI_LLVM12_PATCHES = " \
                   file://llvm12-0001-Remove-__IMAGE_SUPPORT__-macro-for-SPIR-since-SPIR-d.patch \
                   file://llvm12-0002-Avoid-calling-ParseCommandLineOptions-in-BackendUtil.patch \
                   file://llvm12-0003-Support-cl_ext_float_atomics.patch \
                   file://llvm12-0004-ispc-12_0_disable-A-B-A-B-and-BSWAP-in-InstCombine.patch \
                   file://llvm12-0005-ispc-12_0_fix_for_2111.patch \
                   "


SPIRV_LLVM10_SRC_URI = "git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=llvm_release_100;destsuffix=git/llvm/projects/llvm-spirv;name=spirv"

SRC_URI:append:intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '10.0.1', ' ${SPIRV_LLVM10_SRC_URI} ${SRC_URI_LLVM10_PATCHES} ', '', d)}"
SRC_URI:append:intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '12.0.0', ' ${SRC_URI_LLVM12_PATCHES} ', '', d)}"

SRCREV_spirv = "${@bb.utils.contains_any('LLVMVERSION', [ '13.0.0', '12.0.0' ], '', '${SPIRV_SRCREV}', d)}"
