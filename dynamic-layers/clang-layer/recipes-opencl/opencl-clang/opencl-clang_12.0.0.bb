require opencl-clang.inc

SRCREV = "8fc6b059248dc6c9c40c7cbe5fedcc6ebb951983"

DEPENDS += " spirv-llvm-translator"

BRANCH = "ocl-open-120"

EXTRA_OECMAKE += "\
                  -DCMAKE_SKIP_RPATH=TRUE \
                  -DPREFERRED_LLVM_VERSION="12.0.0" \
                  "
