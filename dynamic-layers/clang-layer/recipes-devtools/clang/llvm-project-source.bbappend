FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

SRC_URI_append_intel-x86-common = " \
                                    git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=llvm_release_100;destsuffix=git/llvm/projects/llvm-spirv;name=spirv \
                                    file://llvm10-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                                    file://fix-shared-libs.patch;patchdir=llvm/projects/llvm-spirv \
                                    file://BasicBlockUtils-Add-metadata-fixing-in-SplitBlockPre.patch;patchdir=llvm \
                                    file://IndVarSimplify-Do-not-use-SCEV-expander-for-IVCount-.patch;patchdir=llvm \
                                    "

SRCREV_spirv = "4d43f68a30a510b4e7607351caab3df8e7426a6b"
