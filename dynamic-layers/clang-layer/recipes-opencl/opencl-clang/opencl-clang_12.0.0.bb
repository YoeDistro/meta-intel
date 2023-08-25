require opencl-clang.inc

SRCREV = "8fc6b059248dc6c9c40c7cbe5fedcc6ebb951983"

DEPENDS += " spirv-llvm-translator"

BRANCH = "ocl-open-120"

EXTRA_OECMAKE += "\
                  -DCMAKE_SKIP_RPATH=TRUE \
                  -DPREFERRED_LLVM_VERSION="12.0.0" \
                  "

do_install:append:class-native() {
        install -d ${D}${bindir}
        install -m 0755 ${B}/linux_linker/linux_resource_linker ${D}${bindir}/
}
