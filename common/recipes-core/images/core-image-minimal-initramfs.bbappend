# Use initramfs-framework instead of initramfs-live*
PACKAGE_INSTALL_remove_intel-x86-common = "initramfs-live-boot initramfs-live-install initramfs-live-install-efi"
PACKAGE_INSTALL_append_intel-x86-common = " initramfs-framework-base initramfs-module-udev initramfs-module-setup-live initramfs-module-install-efi"
