INTEL_COMPILER_COMMON_PKGS = "intel-oneapi-runtime-compilers intel-oneapi-runtime-compilers-staticdev intel-oneapi-runtime-compilers-dev"
RDEPENDS:${PN} += "${@bb.utils.contains('ICCSDK', '1', ' ${INTEL_COMPILER_COMMON_PKGS} intel-oneapi-compiler-classic ', '', d)}"
