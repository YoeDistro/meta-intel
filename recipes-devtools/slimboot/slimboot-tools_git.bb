SUMMARY = "Slim Bootloader Host Tools"
DESCRIPTION = "Native host tools to generate the container images in a \
binary file format understood by Slim Bootloader to load and initialize\
Operating Systems or Hypervisors."
HOMEPAGE = "https://slimbootloader.github.io/tools/index.html"

SRC_URI = "git://github.com/slimbootloader/slimbootloader;protocol=https;branch=master"
SRCREV = "df5bd0bc2a522afcb8945a6797592b04838db753"
PV = "0.0.0+git${SRCPV}"
LICENSE = "BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef7fba7be2819ac13aaf5d0f842ce5d9"
S = "${WORKDIR}/git"

inherit python3native
BBCLASSEXTEND = "native"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
	install -m 755 -d ${D}${libexecdir}/slimboot/Tools
	install -m 755 ${S}/BootloaderCorePkg/Tools/*.py ${D}${libexecdir}/slimboot/Tools
}
