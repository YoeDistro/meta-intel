require opencl-clang.inc

SRC_URI = "git://github.com/intel/opencl-clang.git;branch=ocl-open-90;protocol=https \
           file://point-to-correct-llvm-tblgen.patch \
           "

SRCREV = "6f8c329bea44321aef1a1716dd206c1f7bed23cf"
