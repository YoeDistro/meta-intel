# meta-intel maintains a recipe that installs iwlwifi's LinuxCore wifi
# driver releases. For some iwfwifi LinuxCore supported wireless chips, the
# best/latest firmware blobs are found in the iwlwifi's linux-firmware.git fork.
#
# See: https://wireless.wiki.kernel.org/en/users/drivers/iwlwifi/core_release
#
# This bbappend fetches the -31.ucode (currently, for Intel Wireless 8260
# only!) that is the best match for the iwlwifi LinuxCore release built.
#
# Note: keep these in sync when updating the iwlwifi_git.bb LinuxCore
# versions.

SRC_URI_append = " https://git.kernel.org/pub/scm/linux/kernel/git/iwlwifi/linux-firmware.git/plain/iwlwifi-8000C-31.ucode;name=iwlwifi-8260-31"

SRC_URI[iwlwifi-8260-31.md5sum] = "428a84a780bbe864a7af6a6734c4b529"
SRC_URI[iwlwifi-8260-31.sha256sum] = "5a337c52f9d7a7cb5cb0a13c93232f4de742ed0debef757d68231bdb55455406"

do_install_append() {
    # Copy the iwlwifi/LinuxCore required ucode for Intel Wireless 8260
    cp ${WORKDIR}/iwlwifi-8000C-31.ucode ${D}/${nonarch_base_libdir}/firmware/
}
