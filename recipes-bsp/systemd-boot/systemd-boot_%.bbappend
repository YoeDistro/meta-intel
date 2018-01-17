FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/systemd-boot:"

include systemd-boot/${EFI_PROVIDER}.inc

PACKAGE_ARCH_intel-x86-common = "${INTEL_COMMON_PACKAGE_ARCH}"

do_compile_append_intel-x86-common() {
	oe_runmake linux${SYSTEMD_BOOT_EFI_ARCH}.efi.stub
}

do_deploy_append_intel-x86-common() {
	install ${B}/linux*.efi.stub ${DEPLOYDIR}
}
