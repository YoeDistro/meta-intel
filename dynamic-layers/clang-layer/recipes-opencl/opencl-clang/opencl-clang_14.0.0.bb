require opencl-clang.inc

SRC_URI:append = " file://0001-Fix-standalone-build-415.patch \
                   "
SRCREV = "cf95b338d14685e4f3402ab1828bef31d48f1fd6"

BRANCH = "ocl-open-140"

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
