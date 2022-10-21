require opencl-clang.inc

SRCREV = "c78c1f884ffe8b40e1681a90ebde1a919c08ddb1"

BRANCH = "ocl-open-150"

DEPENDS += " spirv-llvm-translator"

EXTRA_OECMAKE += "\
                  -DLLVM_TABLEGEN_EXE=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
                  -DCMAKE_SKIP_RPATH=TRUE \
                  -DPREFERRED_LLVM_VERSION=${LLVMVERSION} \
                  "

do_install:append:class-native() {
        install -d ${D}${bindir}
        install -m 0755 ${B}/bin/linux_resource_linker ${D}${bindir}/
}
