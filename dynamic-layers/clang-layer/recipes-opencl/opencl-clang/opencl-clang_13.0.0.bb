require opencl-clang.inc

SRC_URI:append = " file://0001-don-t-redefine-LLVM_TABLEGEN_EXE.patch \
           "
SRCREV = "0f36f940b25b8e7661cfaf8a7c11fdbb7d853223"

BRANCH = "ocl-open-130"

DEPENDS += " spirv-llvm-translator"

EXTRA_OECMAKE += "\
                  -DLLVM_TABLEGEN_EXE=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
                  -DCMAKE_SKIP_RPATH=TRUE \
                  -DPREFERRED_LLVM_VERSION=${LLVMVERSION} \
                  "
