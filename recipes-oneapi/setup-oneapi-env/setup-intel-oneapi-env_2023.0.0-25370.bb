SUMMARY = "Intel® oneAPI runtime libraries environment set up"
DESCRIPTION = "Recipe to set up the environment for Intel® oneAPI runtime libraries \
via configuration file in ld.so.conf.d directory."

LICENSE = "EULA"
LIC_FILES_CHKSUM = "file://${CUSTOM_LICENSES_PATH}/EULA;md5=7bfc91523de2e84e7131d0eacf2827d4"

SRC_URI = "file://intel-oneapi-runtime.conf"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_install() {
    mkdir -p ${D}${sysconfdir}/ld.so.conf.d/
    install -m 644 ${S}/intel-oneapi-runtime.conf ${D}${sysconfdir}/ld.so.conf.d/
}

pkg_postinst_ontarget:${PN}() {
if [ x"$D" = "x" ]; then
    if [ -x /sbin/ldconfig ]; then /sbin/ldconfig ; fi
fi
}
BBCLASSEXTEND = "native nativesdk"
