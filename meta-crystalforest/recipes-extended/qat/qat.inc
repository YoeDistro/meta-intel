SUMMARY = "Intel QuickAssist Technology Driver"
DESCRIPTION = "Intel QuickAssist Technology for packet processing acceleration for both Intel Coleto Creek & Cave Creek platforms \
Software Package. "

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
COMPATIBLE_MACHINE = "crystalforest"
DEPENDS += "cryptodev-linux pkgconfig zlib"

MODULE_DIR = "${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers"
ICP_TOOLS = "accelcomp"

S = "${WORKDIR}/${ICP_DRIVER_TYPE}"
B = "${S}/build"
SAMPLE_CODE_DIR = "${S}/quickassist/lookaside/access_layer/src/sample_code"

export KERNEL_SOURCE_ROOT = "${STAGING_KERNEL_DIR}"
export ICP_ROOT = "${S}"
export ICP_BUILD_OUTPUT = "${B}"
export ICP_BUILDSYSTEM_PATH = "${S}/quickassist/build_system"
export ICP_TOOLS_TARGET = "${ICP_TOOLS}"
export ICP_ENV_DIR = "${S}/quickassist/build_system/build_files/env_files"
export FUNC_PATH = "${ICP_ROOT}/quickassist/lookaside/access_layer/src/sample_code/functional"

HOST[unexport] = "1"
HOST[unexport] = "includedir"

inherit update-rc.d
INITSCRIPT_NAME = "qat_service"

inherit module
DEPENDS += "openssl"
PARALLEL_MAKE = ""

# The list of binary built by QAT package.

LIBS = "\
	libadf_proxy.a \
	libosal.a \
	libicp_qa_al.a \
	libicp_qa_al_s.so \
	"

FUNC_KERNEL_OBJ_LIST = "\
	algchaining_sample.ko \
	ccm_sample.ko \
	cipher_sample.ko \
	dc_dp_sample.ko \
	dc_stateless_multi_op_sample.ko \
	dc_stateless_sample.ko \
	dh_sample.ko \
	drbg_sample.ko \
	gcm_sample.ko \
	hash_sample.ko \
	ipsec_sample.ko \
	nrbg_sample.ko \
	prime_sample.ko \
	ssl_sample.ko \
	sym_dp_sample.ko \
	"

FUNC_USER_APP_LIST = "\
	dh_sample \
	dc_stateful_sample \
	dc_dp_sample \
	cipher_sample \
	sym_dp_sample \
	nrbg_sample \
	drbg_sample \
	dc_stateless_multi_op_sample \
	paper4 \
	hash_file_sample \
	ccm_sample \
	ipsec_sample \
	algchaining_sample \
	ssl_sample \
	dc_stateless_sample \
	hash_sample \
	gcm_sample \
	prime_sample \
	"

do_unpack2() {
	cd ${WORKDIR}
	tar xvf qatmux.l.${PV}.tar_
	cd ${S}/
	tar xzvf ${ICP_DRIVER_TYPE}.L.${QAT_VERSION}.tar.gz
}

addtask unpack2 after do_unpack before do_patch

do_compile() {
	export LD="${LD} --hash-style=gnu"
	export MACHINE="${TARGET_ARCH}"
	cd ${S}/quickassist
	oe_runmake EXTRA_OEMAKE="'CFLAGS=${CFLAGS} -fPIC'"

	cd ${SAMPLE_CODE_DIR}
	touch ${SAMPLE_CODE_DIR}/performance/compression/calgary
	touch ${SAMPLE_CODE_DIR}/performance/compression/canterbury
	# build the whole sample code: fips, functional, performance
	oe_runmake 'all'
	oe_runmake 'fips_user_code'
}

# Overriding do_install() of yocto module.bbclass so that we
# specially handle the ingredients built by QAT
do_install() {
	install  -d	${D}${base_libdir}/${ICP_FIRMWARE_DIR} \
			${MODULE_DIR} \
			${D}${libdir} \
			${D}${includedir} \
			${D}${includedir}/dc \
			${D}${includedir}/lac \
			${D}${sysconfdir} \
			${D}${sysconfdir}/init.d \
			${D}${sysconfdir}/udev/rules.d \

	for i in ${LIBS}
	do
		install -m 644 ${B}/$i		${D}${libdir}/
	done

	install -m 640  ${B}/mof_firmware.bin		${D}${base_libdir}/${ICP_FIRMWARE_DIR}/
	install -m 640  ${B}/mmp_firmware.bin		${D}${base_libdir}/${ICP_FIRMWARE_DIR}/
	install -m 640  ${B}/icp_qa_al.ko		${MODULE_DIR}/
	install -m 660  ${S}/quickassist/config/dh*	${D}${sysconfdir}/
	install -m 0750 ${B}/qat_service		${D}${sysconfdir}/init.d/
	install -m 0750 ${B}/adf_ctl		${D}${sysconfdir}/init.d/
	install -m 0600	${WORKDIR}/00-qat_qa.rules		${D}${sysconfdir}/udev/rules.d

	# QAT include headers are required by intel-dpdk to be built properly
	install -m 0766 ${S}/quickassist/include/*.h		${D}${includedir}/
	install -m 0766 ${S}/quickassist/include/dc/*.h		${D}${includedir}/dc/
	install -m 0766 ${S}/quickassist/include/lac/*.h	${D}${includedir}/lac/

	# QAT LAC include headers are required by intel-dpdk to be built properly
	install -m 0766 ${S}/quickassist/lookaside/access_layer/include/*.h	${D}${includedir}/

	# Install QAT test application
	# Create the destination folder to contain the package content before it is split
	install -m 0755 -d	${D}${base_libdir}/firmware \
				${D}${bindir} \

	# Start putting package ingredient into each file system location
	# sample_code/performance
	install -m 640 ${SAMPLE_CODE_DIR}/build/*.ko			${MODULE_DIR}/
	install -m 0755 ${SAMPLE_CODE_DIR}/build/cpa_sample_code		${D}${bindir}/
	install -m 640 ${SAMPLE_CODE_DIR}/build/cpa_sample_code_s.so	${D}${libdir}/
	install -m 640 ${SAMPLE_CODE_DIR}/performance/build/linux_2.6/kernel_space/cpa_sample_code.a	${D}${libdir}

	# sample_code/fips
	install -m 640 ${SAMPLE_CODE_DIR}/fips/build/linux_2.6/kernel_space/fips_sample.ko	${MODULE_DIR}/
	install -m 640 ${SAMPLE_CODE_DIR}/fips/build/linux_2.6/user_space/fips_sample.a		${D}${libdir}/
	install -m 640 ${SAMPLE_CODE_DIR}/fips/build/linux_2.6/user_space/fips_sample_s.so	${D}${libdir}/

	# sample_code/functional
	for i in ${FUNC_KERNEL_OBJ_LIST}
	do
		install -m 640  ${SAMPLE_CODE_DIR}/functional/build/$i		${MODULE_DIR}/
	done

	for i in ${FUNC_USER_APP_LIST}
	do
		install -m 0755  ${SAMPLE_CODE_DIR}/functional/build/$i		${D}/${bindir}/
	done

	# Compression Calgary & Canterbury Corpus
	install -m 0755 ${SAMPLE_CODE_DIR}/performance/compression/calgary     ${D}${base_libdir}/firmware
	install -m 0755 ${SAMPLE_CODE_DIR}/performance/compression/canterbury  ${D}${base_libdir}/firmware
}

pkg_postinst_${PN}_append () {
	if [ -z "$D" ]; then
		depmod -a
		update-modules || true
	fi
}

PACKAGES += "${PN}-app"

FILES_${PN} += "\
		${base_libdir}/firmware/ \
		${sysconfdir}/ \
		${sysconfdir}/ \
		${sysconfdir}/udev/rules.d/ \
		${sysconfdir}/init.d/ \
		${libdir}/ \
		${includedir}/ \
		"

FILES_${PN}-dbg += " \
	${sysconfdir}/init.d/.debug \
	"

FILES_${PN}-app += " \
	${bindir}/* \
	"

FILES_${PN}-staticdev += "${libdir}/*.a"
