PACKAGE_ARCH_intel-x86-common = "${INTEL_COMMON_PACKAGE_ARCH}"

do_compile_append_intel-x86-common() {
	ninja src/boot/efi/linux${SYSTEMD_BOOT_EFI_ARCH}.efi.stub
}

do_deploy_append_intel-x86-common() {
	install ${B}/src/boot/efi/linux*.efi.stub ${DEPLOYDIR}
}
