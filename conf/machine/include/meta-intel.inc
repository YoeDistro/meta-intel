#
# BSP variables and settings specific to the meta-intel layer.
# You must include the meta-intel layer in your bblayers.conf
# to use them.
#

PREFERRED_PROVIDER_virtual/kernel ?= "linux-intel"
PREFERRED_PROVIDER_virtual/kernel:poky-tiny ?= "linux-intel"

PREFERRED_VERSION_linux-intel ?= "5.15%"
PREFERRED_VERSION_linux-intel-rt ?= "5.15%"
PREFERRED_VERSION_linux-intel:poky-altcfg ?= "5.10%"
PREFERRED_VERSION_linux-intel-rt:poky-altcfg ?= "5.10%"

# Need to point to latest version of libva needed for media components
PREFERRED_PROVIDER_libva = "libva-intel"
PREFERRED_PROVIDER_libva-native = "libva-intel-native"
PREFERRED_PROVIDER_nativesdk-libva = "nativesdk-libva-intel"

PREFERRED_PROVIDER_libva-utils = "libva-intel-utils"
PREFERRED_PROVIDER_libva-utils-native = "libva-intel-utils-native"
PREFERRED_PROVIDER_nativesdk-libva-utils = "nativesdk-libva-intel-utils"

PREFERRED_VERSION_opencl-clang ?= "${@bb.utils.contains('LLVMVERSION', '12.0.0', '12.0.0', '14.0.0', d)}"
PREFERRED_VERSION_opencl-clang-native ?= "${@bb.utils.contains('LLVMVERSION', '12.0.0', '12.0.0', '14.0.0', d)}"

XSERVER_X86_ASPEED_AST = "xf86-video-ast \
           "

# include the user space intel microcode loading support in the generated images.
MACHINE_ESSENTIAL_EXTRA_RDEPENDS:append = "${@bb.utils.contains('MACHINE_FEATURES', 'intel-ucode', ' intel-microcode', '', d)}"

# recommended extra packages common to all intel machines
MACHINE_EXTRA_RRECOMMENDS:append = " kernel-modules linux-firmware"
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS:append = " kernel-module-i915 linux-firmware-i915"

# for the early boot time kernel microcode loading support,
# merge the microcode data in the final initrd image.
INITRD_LIVE:prepend = "${@bb.utils.contains('MACHINE_FEATURES', 'intel-ucode', '${DEPLOY_DIR_IMAGE}/microcode.cpio ', '', d)}"

DISK_SIGNATURE_UUID ??= "deadbeef-dead-beef-dead-beefdeadbeef"

EFI_PROVIDER ?= "systemd-boot"
EFI_PROVIDER:x86-x32 = "grub-efi"

# Add general MACHINEOVERRIDE for meta-intel
MACHINEOVERRIDES =. "intel-x86-common:"

# Definition of (more or less) suitable virtual machines.
require conf/machine/include/qemuboot-intel.inc

# Ensure that the extra tools needed by qemu are built when building images
# and tweak machine definition to make the result more usable under qemu.
require conf/machine/include/qemu-intel.inc
