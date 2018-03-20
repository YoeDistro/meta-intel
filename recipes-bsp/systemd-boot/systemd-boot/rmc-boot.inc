# This patchset contains hooks that allows systemd-boot to use RMC capablilities.

DEPENDS_append_intel-x86-common = " rmc rmc-efi"

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
