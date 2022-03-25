# For runqemu
IMAGE_CLASSES += "qemuboot"
QB_SYSTEM_NAME:intel-core2-32 = "qemu-system-i386"
QB_CPU:intel-core2-32 = "-cpu coreduo"
QB_CPU_KVM:intel-core2-32 = "-cpu kvm32"

QB_SYSTEM_NAME:intel-corei7-64 = "qemu-system-x86_64"
QB_CPU:intel-corei7-64 = "-cpu Nehalem"
QB_CPU_KVM:intel-corei7-64 = "-cpu kvm64"

QB_SYSTEM_NAME:intel-skylake-64 = "qemu-system-x86_64"
QB_CPU:intel-skylake-64 = "-cpu Skylake-Client"
QB_CPU_KVM:intel-skylake-64 = "-cpu Skylake-Client"

QB_AUDIO_DRV = "alsa"
QB_AUDIO_OPT = "-device AC97"
QB_KERNEL_CMDLINE_APPEND = " oprofile.timer=1"
# Add the 'virtio-rng-pci' device otherwise the guest may run out of entropy
QB_OPT_APPEND = " -usb -usbdevice tablet -device virtio-rng-pci"
