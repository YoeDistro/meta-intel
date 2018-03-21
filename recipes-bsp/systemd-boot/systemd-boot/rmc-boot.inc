# This patchset contains hooks that allows systemd-boot to use RMC capablilities.
python __anonymous () {
    import re
    target = d.getVar('TARGET_ARCH')
    prefix = "" if d.getVar('EFI_PROVIDER') == "rmc-boot" else "systemd-"
    if target == "x86_64":
        systemdimage = prefix + "bootx64.efi"
    else:
        systemdimage = prefix + "bootia32.efi"
    d.setVar("SYSTEMD_BOOT_IMAGE", systemdimage)
    prefix = "systemd-" if prefix == "" else ""
    d.setVar("SYSTEMD_BOOT_IMAGE_PREFIX", prefix)
}

DEPENDS_append_intel-x86-common = " rmc rmc-efi"
RDEPENDS_${PN}_append_intel-x86-common = " rmc-db"

EXTRA_OEMESON_append_intel-x86-common = ' \
                  -Drmc-includedir="${STAGING_INCDIR}/rmc" \
                  '

SRC_URI_append_intel-x86-common = " \
            file://0001-partially-revert-sd-boot-stub-Obtain-PE-section-offs.patch \
            file://0002-sd-boot-fix-RMC-compatibility-with-systemd-boot-and-.patch \
            file://0003-sd-boot-Load-board-specific-boot-entries-from-RMC-da.patch \
            file://0004-sd-boot-Support-global-kernel-command-line-fragment.patch \
            file://0005-sd-boot-support-global-kernel-command-line-in-EFI-st.patch \
            "

RPROVIDES_${PN} += "rmc-boot"
