FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

SPIRV_BRANCH = "${@bb.utils.contains('LLVMVERSION', '9.0.1', 'llvm_release_90', 'llvm_release_100', d)}"

SPIRV9_SRCREV = "70420631144a6a25613ae37178f2cc1d3607b65b"
SPIRV10_SRCREV = "7743482f2053582be990e93ca46d15239c509c9d"
SPIRV_SRCREV = "${@bb.utils.contains('LLVMVERSION', '9.0.1', '${SPIRV9_SRCREV}', '${SPIRV10_SRCREV}', d)}"

LLVM9_PATCH_LIST = " file://0001-dont-export-targets-for-binaries.patch \
                    file://BasicBlockUtils-Add-metadata-fixing-in-SplitBlockPre.patch;patchdir=llvm \
                    file://IndVarSimplify-Do-not-use-SCEV-expander-for-IVCount-.patch;patchdir=llvm \
                    file://llvm9-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                    "
LLVM10_PATCH_LIST = " file://llvm10-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                    file://fix-shared-libs.patch;patchdir=llvm/projects/llvm-spirv \
                    "

SRC_URI_append_intel-x86-common = " \
                                    git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=${SPIRV_BRANCH};destsuffix=git/llvm/projects/llvm-spirv;name=spirv \
                                    "
SRC_URI_append_intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '9.0.1', '${LLVM9_PATCH_LIST}', '${LLVM10_PATCH_LIST}', d)}"

SRCREV_spirv = "${SPIRV_SRCREV}"
