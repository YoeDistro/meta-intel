FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

SRC_URI_append_intel-x86-common = " \
                                    git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=llvm_release_100;destsuffix=git/llvm/projects/llvm-spirv;name=spirv \
                                    file://0001-skip-building-tests.patch;patchdir=llvm/projects/llvm-spirv \
                                    file://fix-shared-libs.patch;patchdir=llvm/projects/llvm-spirv \
                                    "

SRCREV_spirv = "7743482f2053582be990e93ca46d15239c509c9d"
