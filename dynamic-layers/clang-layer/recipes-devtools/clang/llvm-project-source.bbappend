FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

SRC_URI_append_intel-x86-common = " \
                                    file://0001-OpenCL-Change-type-of-block-pointer-for-OpenCL.patch;patchdir=clang \
                                    file://0002-OpenCL-Simplify-LLVM-IR-generated-for-OpenCL-blocks.patch;patchdir=clang \
                                    file://0003-OpenCL-Fix-assertion-due-to-blocks.patch;patchdir=clang \
                                    file://0001-dont-export-targets-for-binaries.patch \
                                    git://github.com/KhronosGroup/SPIRV-LLVM-Translator.git;protocol=https;branch=llvm_release_80;destsuffix=git/llvm/projects/llvm-spirv;name=spirv \
                                    file://0001-Update-LowerOpenCL-pass-to-handle-new-blocks-represn.patch;patchdir=llvm/projects/llvm-spirv \
                                    "

SRCREV_spirv = "bd0f28fb92061d49c0f120b4dac3fd8956006745"
