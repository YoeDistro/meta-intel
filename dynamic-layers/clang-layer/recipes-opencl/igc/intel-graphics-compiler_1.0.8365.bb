SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;beginline=1;endline=23;md5=4a985f2545dd5a846e205b1e60a51cd9 \
                    file://NOTICES.txt;md5=db621145dfb627436bc90ad600386801"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https; \
           file://0001-skip-execution-of-ElfPackager.patch \
           file://0002-IGC-VectorCompiler-CMakeLists.txt-link-to-external-L.patch \
           file://0003-Improve-Reproducibility-for-src-package.patch \
           file://0004-find-external-llvm-tblgen.patch \
           file://0005-Temporary-LLVM-12-compatiblity-fix.patch \
           file://0001-LLVM-13-fixes.patch \
          "

SRCREV = "5d5672d6cc0c415dae76648390026f777004bd99"

# Used to replace with relative path in reproducibility patch
export B

S = "${WORKDIR}/git"

inherit cmake

CXXFLAGS:append = " -Wno-error=deprecated-declarations"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += " flex-native bison-native clang opencl-clang vc-intrinsics"
DEPENDS:append:class-target = " clang-cross-x86_64"

RDEPENDS:${PN} += "opencl-clang"

EXTRA_OECMAKE = "-DIGC_OPTION__LLVM_PREFERRED_VERSION=${LLVMVERSION} -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 -DIGC_BUILD__VC_ENABLED=OFF -DIGC_BUILD__USE_KHRONOS_SPIRV_TRANSLATOR=ON"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES:${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "
