SUMMARY = "The Intel(R) Graphics Compiler for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compiler for OpenCL(TM) is an \
llvm based compiler for OpenCL(TM) targeting Intel Gen graphics \
hardware architecture."

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IGC/BiFModule/Implementation/ExternalLibraries/libclc/LICENSE.TXT;md5=311cfc1a5b54bab8ed34a0b5fba4373e \
                    file://IGC/Compiler/LegalizationPass.cpp;beginline=1;endline=23;md5=4a985f2545dd5a846e205b1e60a51cd9 \
                    file://NOTICES.txt;md5=db621145dfb627436bc90ad600386801"

SRC_URI = "git://github.com/intel/intel-graphics-compiler.git;protocol=https; \
           file://0003-Improve-Reproducibility-for-src-package.patch \
           file://0004-find-external-llvm-tblgen.patch \
          "

SRCREV = "ed2229d94e6d02d856b2492731ee032b6394f516"

# Used to replace with relative path in reproducibility patch
export B

S = "${WORKDIR}/git"

inherit cmake qemu

CXXFLAGS:append = " -Wno-error=deprecated-declarations"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS += " flex-native bison-native clang opencl-clang vc-intrinsics"
DEPENDS:append:class-target = " clang-cross-x86_64 qemu-native"

RDEPENDS:${PN} += "opencl-clang"

EXTRA_OECMAKE = " \
    -DIGC_OPTION__CLANG_MODE=Prebuilds \
    -DIGC_OPTION__LLD_MODE=Prebuilds \
    -DIGC_OPTION__LLVM_MODE=Prebuilds \
    -DIGC_OPTION__LLVM_PREFERRED_VERSION=${LLVMVERSION} \
    -DIGC_OPTION__LLVM_STOCK_SOURCES=1 \
    -DDEFAULT_IGC_LLVM_SOURCES_DIR=${TMPDIR}/work-shared/llvm-project-source-${LLVMVERSION}-r0/git \
    -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 \
    -DIGC_BUILD__VC_ENABLED=OFF \
    -DIGC_OPTION__USE_KHRONOS_SPIRV_TRANSLATOR_IN_VC=ON \
"

EXTRA_OECMAKE:prepend:class-target = " \
    -DCMAKE_CROSSCOMPILING_EMULATOR=${WORKDIR}/qemuwrapper \
"

do_configure:prepend:class-target () {
	# Write out a qemu wrapper that will be used by cmake
	# so that it can run target helper binaries through that.
	qemu_binary="${@qemu_wrapper_cmdline(d, d.getVar('STAGING_DIR_HOST'), [d.expand('${STAGING_DIR_HOST}${libdir}'),d.expand('${STAGING_DIR_HOST}${base_libdir}')])}"
	cat > ${WORKDIR}/qemuwrapper << EOF
#!/bin/sh
$qemu_binary "\$@"
EOF
	chmod +x ${WORKDIR}/qemuwrapper
}

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "^igc-(?P<pver>(?!19\..*)\d+(\.\d+)+)$"

FILES:${PN} += " \
                 ${libdir}/igc/NOTICES.txt \
                 "
