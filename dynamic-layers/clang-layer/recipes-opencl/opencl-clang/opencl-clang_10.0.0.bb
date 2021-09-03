require opencl-clang.inc

SRC_URI:append = " file://0001-don-t-redefine-LLVM_TABLEGEN_EXE.patch \
           file://0001-Building-in-tree-with-LLVM-10.0-with-the-LLVM_LINK_L.patch \
           "
SRC_URI:append:class-native = " file://0002-make-sure-only-static-libraries-linked-for-native-bu.patch"

BRANCH = "ocl-open-100"

SRCREV = "c8cd72e32b6abc18ce6da71c357ea45ba78b52f0"

EXTRA_OECMAKE += "\
                  -DLLVM_TABLEGEN_EXE=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
                  -DCMAKE_SKIP_RPATH=TRUE \
                  "
