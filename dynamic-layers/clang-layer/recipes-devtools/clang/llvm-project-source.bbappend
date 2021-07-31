FILESEXTRAPATHS:prepend:intel-x86-common := "${THISDIR}/files:"

SPIRV10_SRCREV = "576abae62cecd171992017a4a786e3831221ab8d"
SPIRV11_SRCREV = "2a8c1e6c9778deaa720a23e08c293006dc5d56fd"

SPIRV_SRCREV = "${@bb.utils.contains('LLVMVERSION', '10.0.1', '${SPIRV10_SRCREV}', '${SPIRV11_SRCREV}', d)}"

SRC_URI_LLVM10_PATCHES = " \
                   file://llvm10-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                   file://fix-shared-libs.patch;patchdir=llvm/projects/llvm-spirv \
                   file://BasicBlockUtils-Add-metadata-fixing-in-SplitBlockPre.patch;patchdir=llvm \
                   file://IndVarSimplify-Do-not-use-SCEV-expander-for-IVCount-.patch;patchdir=llvm \
                   file://llvm10-OpenCL-3.0-support.patch \
                   file://0002-Add-cl_khr_extended_subgroup-extensions.patch \
                   file://0001-Memory-leak-fix-for-Managed-Static-Mutex.patch \
                   file://llvm10-Remove-repo-name-in-LLVM-IR.patch \
                   file://0001-Fix-debug-info-of-work-item-builtin-translation-745.patch;patchdir=llvm/projects/llvm-spirv \
                   "

SRC_URI_LLVM11_PATCHES = " \
                   file://llvm11-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                   file://llvm11-OpenCL-3.0-support.patch \
                   file://0001-Memory-leak-fix-for-Managed-Static-Mutex.patch \
                   file://llvm11-Remove-repo-name-in-LLVM-IR.patch \
                   "
SRC_URI_LLVM12_PATCHES = " \
                   file://0001-Remove-__IMAGE_SUPPORT__-macro-for-SPIR.patch \
                   "


SPIRV_LLVM10_SRC_URI = "git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=llvm_release_100;destsuffix=git/llvm/projects/llvm-spirv;name=spirv"

SPIRV_LLVM11_SRC_URI = "git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=llvm_release_110;destsuffix=git/llvm/projects/llvm-spirv;name=spirv"



SRC_URI:append:intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '10.0.1', ' ${SPIRV_LLVM10_SRC_URI} ${SRC_URI_LLVM10_PATCHES} ', '', d)}"
SRC_URI:append:intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '11.1.0', ' ${SPIRV_LLVM11_SRC_URI} ${SRC_URI_LLVM11_PATCHES} ', '', d)}"
SRC_URI:append:intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '12.0.0', ' ${SRC_URI_LLVM12_PATCHES} ', '', d)}"

SRCREV_spirv = "${@bb.utils.contains_any('LLVMVERSION', [ '13.0.0', '12.0.0' ], '', '${SPIRV_SRCREV}', d)}"
