require opencl-clang.inc

SRC_URI = "git://github.com/intel/opencl-clang.git;branch=ocl-open-100;protocol=https \
           file://0001-don-t-redefine-LLVM_TABLEGEN_EXE.patch \
           file://0001-Building-in-tree-with-LLVM-10.0-with-the-LLVM_LINK_L.patch \
           "
SRC_URI_append_class-native = " file://0002-make-sure-only-static-libraries-linked-for-native-bu.patch"

SRCREV = "92f3f7f1a06f25fb13708f87c26b0fbf50924c96"
