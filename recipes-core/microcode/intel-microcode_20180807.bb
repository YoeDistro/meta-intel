SUMMARY = "Intel Processor Microcode Datafile for Linux"
HOMEPAGE = "http://www.intel.com/"
DESCRIPTION = "The microcode data file contains the latest microcode\
 definitions for all Intel processors. Intel releases microcode updates\
 to correct processor behavior as documented in the respective processor\
 specification updates. While the regular approach to getting this microcode\
 update is via a BIOS upgrade, Intel realizes that this can be an\
 administrative hassle. The Linux operating system and VMware ESX\
 products have a mechanism to update the microcode after booting.\
 For example, this file will be used by the operating system mechanism\
 if the file is placed in the /etc/firmware directory of the Linux system."

LICENSE = "Intel-Microcode-License"
LIC_FILES_CHKSUM = "file://Intel-Microcode-License;md5=9052c0dbf7250dc0bef70bd8212b0573"

SRC_URI = "https://downloadmirror.intel.com/28039/eng/microcode-${PV}.tgz \
           file://Intel-Microcode-License \
           "

SRC_URI[md5sum] = "49f534f1079d3c5bc178a150c1c105aa"
SRC_URI[sha256sum] = "29f9e8dc27e6c9b6488cecd7fe2394030307799e511db2d197d9e6553a7f9e40"
DEPENDS = "iucode-tool-native"
S = "${WORKDIR}"

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

# Use any of the iucode_tool parameters to filter specific microcodes from the data file
# For further information, check the iucode-tool's manpage : http://manned.org/iucode-tool
UCODE_FILTER_PARAMETERS ?= ""

do_compile() {
	rm -f ${WORKDIR}/intel-ucode/list
	${STAGING_DIR_NATIVE}${sbindir_native}/iucode_tool \
		${UCODE_FILTER_PARAMETERS} \
		--overwrite \
		--write-to=${WORKDIR}/microcode_${PV}.bin \
		${WORKDIR}/intel-ucode/*

	${STAGING_DIR_NATIVE}${sbindir_native}/iucode_tool \
		${UCODE_FILTER_PARAMETERS} \
		--overwrite \
		--write-earlyfw=${WORKDIR}/microcode_${PV}.cpio \
		${WORKDIR}/intel-ucode/*
}

do_install() {
	install -d ${D}${base_libdir}/firmware/intel-ucode/
	install ${WORKDIR}/microcode_${PV}.bin ${D}${base_libdir}/firmware/intel-ucode/
	cd ${D}${base_libdir}/firmware/intel-ucode/
	ln -sf microcode_${PV}.bin microcode.bin
}

do_deploy() {
	install -d ${DEPLOYDIR}
	install ${S}/microcode_${PV}.cpio ${DEPLOYDIR}/
	cd ${DEPLOYDIR}
	rm -f microcode.cpio
	ln -sf microcode_${PV}.cpio microcode.cpio
}

addtask deploy before do_build after do_compile

PACKAGES = "${PN}"

FILES_${PN} = "${base_libdir}"
