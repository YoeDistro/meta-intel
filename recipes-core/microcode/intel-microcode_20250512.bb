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
LIC_FILES_CHKSUM = "file://license;md5=d8405101ec6e90c1d84b082b0c40c721"

SRC_URI = "git://github.com/intel/Intel-Linux-Processor-Microcode-Data-Files.git;protocol=https;branch=main \
           "

SRCREV = "eeb93b7a818bb27cb6b7a2be0454f8a0a75f1bd6"

DEPENDS = "iucode-tool-native"
S = "${WORKDIR}/git"

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

# Use any of the iucode_tool parameters to filter specific microcodes from the data file
# For further information, check the iucode-tool's manpage : http://manned.org/iucode-tool
UCODE_FILTER_PARAMETERS ?= ""

do_compile() {
	${STAGING_DIR_NATIVE}${sbindir_native}/iucode_tool \
		${UCODE_FILTER_PARAMETERS} \
		--overwrite \
		--write-earlyfw=${WORKDIR}/microcode_${PV}.cpio \
		${S}/intel-ucode/* ${S}/intel-ucode-with-caveats/*
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/intel-ucode/
	${STAGING_DIR_NATIVE}${sbindir_native}/iucode_tool \
	${UCODE_FILTER_PARAMETERS} \
	--write-firmware=${D}${nonarch_base_libdir}/firmware/intel-ucode \
	${S}/intel-ucode/* ${S}/intel-ucode-with-caveats/*
}

do_deploy() {
	install -d ${DEPLOYDIR}
	install ${WORKDIR}/microcode_${PV}.cpio ${DEPLOYDIR}/
	cd ${DEPLOYDIR}
	rm -f microcode.cpio
	ln -sf microcode_${PV}.cpio microcode.cpio
}

addtask deploy before do_build after do_compile

PACKAGES = "${PN}"

FILES:${PN} = "${nonarch_base_libdir}"

UPSTREAM_CHECK_GITTAGREGEX = "^microcode-(?P<pver>(\d+)[a-z]*)$"
