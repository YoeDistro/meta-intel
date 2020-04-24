require opencl-clang.inc

SRC_URI = "git://github.com/intel/opencl-clang.git;branch=ocl-open-100;protocol=https \
           file://0001-don-t-redefine-LLVM_TABLEGEN_EXE.patch \
           file://0001-Building-in-tree-with-LLVM-10.0-with-the-LLVM_LINK_L.patch \
           "
SRC_URI_append_class-native = " file://0002-make-sure-only-static-libraries-linked-for-native-bu.patch"

SRCREV = "9f0c2c0f5ddea1accc921aed4c94bc52c1b85637"
