SUMMARY = "Compatibility package for backport-iwlwifi kernel module dependencies"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "virtual/kernel"

# libarc4 is always built as module (CONFIG_CRYPTO_LIB_ARC4=m)
# firmware-class may be built-in (linux-yocto) or module (linux-intel/linux-intel-rt)
RDEPENDS:${PN} = "kernel-module-libarc4"
RRECOMMENDS:${PN} = "kernel-module-firmware-class"

inherit module-base

ALLOW_EMPTY:${PN} = "1"

# Dynamically provide the names for whatever kernel is being used
# These are kernel modules that backport-iwlwifi expects with its prefix
RPROVIDES:${PN} = " \
    backport-iwlwifikernel-module-libarc4-${KERNEL_VERSION} \
    backport-iwlwifikernel-module-firmware-class-${KERNEL_VERSION} \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
