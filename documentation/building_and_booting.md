### Building the Intel BSP layers

The intel-common BSP provide a few carefully selected tune options and
generic hardware support to cover the majority of current Intel CPUs and
devices. The naming follows the convention of intel-<TUNE>-<BITS>, where
TUNE is the gcc cpu-type (used with mtune and march typically) and BITS
is either 32 bit or 64 bit.

In order to build an image with BSP support for a given release, you
need to clone the meta-intel layer from git repository:
```
git clone https://git.yoctoproject.org/meta-intel
```

Check out the appropriate branch or release tags. The branch name and tags
would align with Yocto Project
[Release Codenames](https://wiki.yoctoproject.org/wiki/Releases).
Assuming meta-intel repository is cloned at the top-level of
OE-Core build tree, you can build a BSP image by adding the location of
the meta-intel layer to bblayers.conf:
```
BBLAYERS = " \
  /openembedded-core/meta \
  /openembedded-core/meta-intel "
```

To enable a particular machine, add a MACHINE line naming the BSP
to the local.conf file:
```
MACHINE ?= "intel-corei7-64"
```

where this can be replaced by other MACHINE types available:

 - intel-core2-32

   This BSP is optimized for the Core2 family of CPUs as well as all
   Atom CPUs prior to the Silvermont core.

 - intel-corei7-64

   This BSP is optimized for Nehalem and later Core and Xeon CPUs as
   well as Silvermont and later Atom CPUs, such as the Baytrail SoCs.

 - intel-skylake-64

   This BSP uses [x86-64-v3 tuning](https://gcc.gnu.org/onlinedocs/gcc/x86-Options.html).

You should then be able to build an image as such:
```
$ source oe-init-build-env
$ bitbake core-image-sato
```

At the end of a successful build, you should have an image that
you can boot from a USB flash drive.


## Booting the intel-common BSP images

If you've built your own image, you'll find the bootable
image in the build/tmp/deploy/images/{MACHINE} directory, where
'MACHINE' refers to the machine name used in the build.

Under Linux, insert a USB flash drive.  Assuming the USB flash drive
takes device /dev/sdf, use dd to copy the image to it.  Before the image
can be burned onto a USB drive, it should be un-mounted. Some Linux distros
may automatically mount a USB drive when it is plugged in. Using USB device
/dev/sdf as an example, find all mounted partitions:
```
$ mount | grep sdf
```

and un-mount those that are mounted, for example:
```
$ umount /dev/sdf1
$ umount /dev/sdf2
```

Now burn the image onto the USB drive:
```
$ sudo dd if=core-image-sato-intel-corei7-64.wic of=/dev/sdf status=progress
$ sync
$ eject /dev/sdf
```

This should give you a bootable USB flash device. Insert the device
into a bootable USB socket on the target, and power on.  This should
result in a system booted to the Sato graphical desktop.

If you want a terminal, use the arrows at the top of the UI to move to
different pages of available applications, one of which is named
'Terminal'.  Clicking that should give you a root terminal.

If you want to ssh into the system, you can use the root terminal to
ifconfig the IP address and use that to ssh in.  The root password is
empty, so to log in type 'root' for the user name and hit 'Enter' at
the Password prompt: and you should be in.

If you find you're getting corrupt images on the USB (it doesn't show
the syslinux boot: prompt, or the boot: prompt contains strange
characters), try doing this first:
```
$ dd if=/dev/zero of=/dev/sdf bs=1M count=512
```

## Building the installer image

If you plan to install your image to your target machine, you can build a wic
based installer image instead of default wic image. To build it, you need to
add below configuration to local.conf :

```
WKS_FILE = "image-installer.wks.in"
IMAGE_FSTYPES:append = " ext4"
IMAGE_TYPEDEP:wic = "ext4"
INITRD_IMAGE_LIVE="core-image-minimal-initramfs"
do_image_wic[depends] += "${INITRD_IMAGE_LIVE}:do_image_complete"
do_rootfs[depends] += "virtual/kernel:do_deploy"
IMAGE_BOOT_FILES:append = "\
    ${KERNEL_IMAGETYPE} \
     microcode.cpio \
    ${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.rootfs.ext4;rootfs.img \
    ${@bb.utils.contains('EFI_PROVIDER', 'grub-efi', 'grub-efi-bootx64.efi;EFI/BOOT/bootx64.efi', '', d)} \
    ${@bb.utils.contains('EFI_PROVIDER', 'grub-efi', '${IMAGE_ROOTFS}/boot/EFI/BOOT/grub.cfg;EFI/BOOT/grub.cfg', '', d)} \
    ${@bb.utils.contains('EFI_PROVIDER', 'systemd-boot', 'systemd-bootx64.efi;EFI/BOOT/bootx64.efi', '', d)} \
    ${@bb.utils.contains('EFI_PROVIDER', 'systemd-boot', '${IMAGE_ROOTFS}/boot/loader/loader.conf;loader/loader.conf ', '', d)} \
    ${@bb.utils.contains('EFI_PROVIDER', 'systemd-boot', '${IMAGE_ROOTFS}/boot/loader/entries/boot.conf;loader/entries/boot.conf', '', d)} "
```

Burn the wic image onto USB flash device, insert the device to target machine
and power on. This should start the installation process.


