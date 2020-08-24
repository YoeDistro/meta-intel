SUMMARY = "Slim Bootloader Host Tools"
DESCRIPTION = "Native host tools to generate the container images in a \
binary file format understood by Slim Bootloader to load and initialize\
Operating Systems or Hypervisors."
HOMEPAGE = "https://slimbootloader.github.io/tools/index.html"

SRC_URI = "git://github.com/slimbootloader/slimbootloader;protocol=https"
SRCREV = "9f146afd47e0ca204521826a583d55388850b216"
PV = "0.0.0+git${SRCPV}"
LICENSE = "BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d1ed89007e7aa232a4dc1c59b6c9efc4"
S = "${WORKDIR}/git"

inherit python3native
BBCLASSEXTEND = "native"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
	install -m 755 -d ${D}${libexecdir}/slimboot/Tools
	install -m 755 ${S}/BootloaderCorePkg/Tools/*.py ${D}${libexecdir}/slimboot/Tools
}
