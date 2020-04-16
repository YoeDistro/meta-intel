#
# BSP variables and settings specific to the meta-intel layer.
# You must include the meta-intel layer in your bblayers.conf
# to use them.
#

PREFERRED_PROVIDER_virtual/kernel ?= "linux-intel"
PREFERRED_PROVIDER_virtual/kernel_poky-tiny ?= "linux-intel"

# Only use the Intel-tuned zlib for target builds to improve reuse
PREFERRED_PROVIDER_zlib = "zlib-intel"
PREFERRED_PROVIDER_zlib-native = "zlib-native"
PREFERRED_PROVIDER_nativesdk-zlib = "nativesdk-zlib"

PREFERRED_PROVIDER_libva = "libva-intel"
PREFERRED_PROVIDER_libva-native = "libva-intel-native"
PREFERRED_PROVIDER_nativesdk-libva = "nativesdk-libva-intel"

PREFERRED_PROVIDER_libva-utils = "libva-utils-intel"
PREFERRED_PROVIDER_libva-native = "libva-utils-intel-native"
PREFERRED_PROVIDER_nativesdk-libva-utils = "nativesdk-libva-utils-intel"

PREFERRED_VERSION_linux-intel ?= "5.4%"
PREFERRED_VERSION_linux-intel-rt ?= "5.4%"
PREFERRED_VERSION_linux-intel_poky-altcfg ?= "5.4%"
PREFERRED_VERSION_linux-intel-rt_poky-altcfg ?= "5.4%"

XSERVER_X86_ASPEED_AST = "xf86-video-ast \
           "

# include the user space intel microcode loading support in the generated images.
MACHINE_ESSENTIAL_EXTRA_RDEPENDS_append = "${@bb.utils.contains('MACHINE_FEATURES', 'intel-ucode', ' intel-microcode', '', d)} thermald"

# recommended extra packages common to all intel machines
MACHINE_EXTRA_RRECOMMENDS_append = " kernel-modules linux-firmware"
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS_append = " kernel-module-i915 linux-firmware-i915"

# for the early boot time kernel microcode loading support,
# merge the microcode data in the final initrd image.
INITRD_LIVE_prepend = "${@bb.utils.contains('MACHINE_FEATURES', 'intel-ucode', '${DEPLOY_DIR_IMAGE}/microcode.cpio ', '', d)}"

DISK_SIGNATURE_UUID ??= "deadbeef-dead-beef-dead-beefdeadbeef"

EFI_PROVIDER ?= "systemd-boot"
EFI_PROVIDER_x86-x32 = "grub-efi"

# Add general MACHINEOVERRIDE for meta-intel
MACHINEOVERRIDES =. "intel-x86-common:"

# Definition of (more or less) suitable virtual machines.
require conf/machine/include/qemuboot-intel.inc

# Ensure that the extra tools needed by qemu are built when building images
# and tweak machine definition to make the result more usable under qemu.
require conf/machine/include/qemu-intel.inc
