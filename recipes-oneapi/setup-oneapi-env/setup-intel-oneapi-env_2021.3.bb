SUMMARY = "Intel® oneAPI runtime libraries environment set up"
DESCRIPTION = "Recipe to set up the environment for Intel® oneAPI runtime libraries \
via configuration file in ld.so.conf.d directory."

LICENSE = "EULA"
LIC_FILES_CHKSUM = "file://${CUSTOM_LICENSES_PATH}/EULA;md5=9057fba3b8ada79f1bce0d1c195c7d1f"

SRC_URI = "file://intel-oneapi-runtime.conf"

do_install() {
    mkdir -p ${D}${sysconfdir}/ld.so.conf.d/
    install -m 644 ${WORKDIR}/intel-oneapi-runtime.conf ${D}${sysconfdir}/ld.so.conf.d/
}

pkg_postinst_ontarget:${PN}() {
if [ x"$D" = "x" ]; then
    if [ -x /sbin/ldconfig ]; then /sbin/ldconfig ; fi
fi
}
