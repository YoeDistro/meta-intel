SUMMARY = "The Intel(R) Graphics Compute Runtime for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compute Runtime for OpenCL(TM) \
is an open source project to converge Intel's development efforts \
on OpenCL(TM) compute stacks supporting the GEN graphics hardware \
architecture."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=983b0c493ea3dc3c21a90ff743bf90e4 \
                    file://third_party/opencl_headers/LICENSE;md5=dcefc90f4c3c689ec0c2489064e7273b"

SRC_URI = "git://github.com/intel/compute-runtime.git;protocol=https \
          "

SRC_URI:append:class-target = " \
           file://0001-Remove-indirection-from-ocloc_lib-and-ocloc-names.patch \
           file://allow-to-find-cpp-generation-tool.patch \
"

SRCREV = "b2909fef563ef0f701dc40be4f5192b7675d2750"

S = "${WORKDIR}/git"

DEPENDS += " intel-graphics-compiler gmmlib"
DEPENDS:append:class-target = " qemu-native libva"

RDEPENDS:${PN} += " intel-graphics-compiler gmmlib"

inherit cmake pkgconfig qemu

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

EXTRA_OECMAKE = " \
                 -DIGC_DIR=${STAGING_INCDIR}/igc \
                 -DBUILD_TYPE=Release \
                 -DNEO_DISABLE_BUILTINS_COMPILATION=1 \
                 -DSKIP_UNIT_TESTS=1 \
                 -DCCACHE_ALLOWED=FALSE \
                 "
EXTRA_OECMAKE:append:class-target = " \
                 -DCMAKE_CROSSCOMPILING_EMULATOR=${WORKDIR}/qemuwrapper \
                 "

PACKAGECONFIG ??= ""
PACKAGECONFIG[levelzero] = "-DBUILD_WITH_L0=ON, -DBUILD_WITH_L0=OFF, level-zero"

do_configure:prepend:class-target () {
	# Write out a qemu wrapper that will be used by cmake
	# so that it can run target helper binaries through that.
	qemu_binary="${@qemu_wrapper_cmdline(d, d.getVar('STAGING_DIR_HOST'), [d.expand('${B}/bin'),d.expand('${STAGING_DIR_HOST}${libdir}'),d.expand('${STAGING_DIR_HOST}${base_libdir}')])}"
	cat > ${WORKDIR}/qemuwrapper << EOF
#!/bin/sh
$qemu_binary "\$@"
EOF
	chmod +x ${WORKDIR}/qemuwrapper
}

do_install:append:class-native() {
    install -d ${D}${bindir}
    install ${B}/bin/cpp_generate_tool ${D}${bindir}/
}

FILES:${PN} += " \
                 ${libdir}/intel-opencl/libigdrcl.so \
                 ${libdir}/libocloc.so \
                 "

FILES:${PN}-dev = "${includedir}"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+(\.\d+)+)"
