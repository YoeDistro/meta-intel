require opencl-clang.inc

SRC_URI:append = " file://0001-don-t-redefine-LLVM_TABLEGEN_EXE.patch \
           "
SRC_URI:append:class-native = " file://0002-make-sure-only-static-libraries-linked-for-native-bu.patch"

SRCREV = "c67648d41df00ea8ee9d701d17299b86f86f0321"

BRANCH = "ocl-open-110"

EXTRA_OECMAKE += "\
                  -DLLVM_TABLEGEN_EXE=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
                  -DCMAKE_SKIP_RPATH=TRUE \
                  -DPREFERRED_LLVM_VERSION="11.1.0" \
                  "
