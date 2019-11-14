FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

SRC_URI_append_intel-x86-common = " \
                                    file://0001-dont-export-targets-for-binaries.patch \
                                    file://BasicBlockUtils-Add-metadata-fixing-in-SplitBlockPre.patch;patchdir=llvm \
                                    file://IndVarSimplify-Do-not-use-SCEV-expander-for-IVCount-.patch;patchdir=llvm \
                                    git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=llvm_release_90;destsuffix=git/llvm/projects/llvm-spirv;name=spirv \
                                    file://0001-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                                    "

SRCREV_spirv = "07f29780e5c4128924508d5d1c00bdf9ff7fd43d"
