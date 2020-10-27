FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

SPIRV_BRANCH = "${@bb.utils.contains('LLVMVERSION', '10.0.1', 'llvm_release_100', 'llvm_release_110', d)}"

SPIRV10_SRCREV = "4d43f68a30a510b4e7607351caab3df8e7426a6b"
SPIRV11_SRCREV = "d6dc999eee381158a26f048a333467c9ce7e77f2"

SPIRV_SRCREV = "${@bb.utils.contains('LLVMVERSION', '10.0.1', '${SPIRV10_SRCREV}', '${SPIRV11_SRCREV}', d)}"

SRC_URI_LLVM10 = " \
                   file://llvm10-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                   file://fix-shared-libs.patch;patchdir=llvm/projects/llvm-spirv \
                   file://BasicBlockUtils-Add-metadata-fixing-in-SplitBlockPre.patch;patchdir=llvm \
                   file://IndVarSimplify-Do-not-use-SCEV-expander-for-IVCount-.patch;patchdir=llvm \
                   "

SRC_URI_LLVM11 = " \
                   file://llvm11-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                   "

SRC_URI_append_intel-x86-common = " \
                                    git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=${SPIRV_BRANCH};destsuffix=git/llvm/projects/llvm-spirv;name=spirv \
                                    "

SRC_URI_append_intel-x86-common = "${@bb.utils.contains('LLVMVERSION', '10.0.1', '${SRC_URI_LLVM10}', '${SRC_URI_LLVM11}', d)}"

SRCREV_spirv = "${SPIRV_SRCREV}"
