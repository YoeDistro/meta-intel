FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

SPIRV_BRANCH = "${@bb.utils.contains('LLVMVERSION', '10.0.1', 'llvm_release_100', 'llvm_release_110', d)}"

SPIRV10_SRCREV = "576abae62cecd171992017a4a786e3831221ab8d"
SPIRV11_SRCREV = "2a8c1e6c9778deaa720a23e08c293006dc5d56fd"

SPIRV_SRCREV = "${@bb.utils.contains('LLVMVERSION', '10.0.1', '${SPIRV10_SRCREV}', '${SPIRV11_SRCREV}', d)}"

SRC_URI_LLVM10 = " \
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

SRC_URI_LLVM11 = " \
                   file://llvm11-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                   file://llvm11-OpenCL-3.0-support.patch \
                   file://0001-Memory-leak-fix-for-Managed-Static-Mutex.patch \
                   file://llvm11-Remove-repo-name-in-LLVM-IR.patch \
                   "

SPIRV_LLVM_SRC_URI = "git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=${SPIRV_BRANCH};destsuffix=git/llvm/projects/llvm-spirv;name=spirv"

SPIRV_LLVM_PATCHES = "${@bb.utils.contains('LLVMVERSION', '10.0.1', '${SRC_URI_LLVM10}', '${SRC_URI_LLVM11}', d)}"


SRC_URI_append_intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '12.0.0', '', ' ${SPIRV_LLVM_SRC_URI} ${SPIRV_LLVM_PATCHES} ', d)}"
SRCREV_spirv = "${@bb.utils.contains('LLVMVERSION', '12.0.0', '', '${SPIRV_SRCREV}', d)}"
