# This class brings a more generic version of the UEFI combo app from refkit to meta-intel.
# It uses a combo file, containing kernel, initramfs and
# command line, presented to the BIOS as UEFI application, by prepending
# it with the efi stub obtained from systemd-boot.

# Don't add syslinux or build an ISO
PCBIOS_forcevariable = "0"
NOISO_forcevariable  = "1"

# image-live.bbclass will default INITRD_LIVE to the image INITRD_IMAGE creates.
# We want behavior to be consistent whether or not "live" is in IMAGE_FSTYPES, so
# we default INITRD_LIVE to the INITRD_IMAGE as well.
INITRD_IMAGE ?= "core-image-minimal-initramfs"
INITRD_LIVE ?= " ${@ ('${DEPLOY_DIR_IMAGE}/' + d.getVar('INITRD_IMAGE', expand=True) + '-${MACHINE}.cpio.gz') if d.getVar('INITRD_IMAGE', True) else ''}"

do_uefiapp[depends] += " \
                         intel-microcode:do_deploy \
                         systemd-boot:do_deploy \
                         virtual/kernel:do_deploy \
                       "

# INITRD_IMAGE is added to INITRD_LIVE, which we use to create our initrd, so depend on it if it is set
do_uefiapp[depends] += "${@ '${INITRD_IMAGE}:do_image_complete' if d.getVar('INITRD_IMAGE') else ''}"

# The image does without traditional bootloader.
# In its place, instead, it uses a single UEFI executable binary, which is
# composed by:
#   - an UEFI stub
#     The linux kernel can generate a UEFI stub, however the one from systemd-boot can fetch
#     the command line from a separate section of the EFI application, avoiding the need to
#     rebuild the kernel.
#   - the kernel
#   - an initramfs (optional)

python do_uefiapp() {
    import glob, re
    from subprocess import check_call

    build_dir = d.getVar('B')
    deploy_dir_image = d.getVar('DEPLOY_DIR_IMAGE')

    cmdline = '%s/cmdline.txt' % build_dir
    linux = '%s/%s' % (deploy_dir_image, d.getVar('KERNEL_IMAGETYPE'))
    initrd = '%s/initrd' % build_dir

    stub_path = '%s/linux*.efi.stub' % deploy_dir_image
    stub = glob.glob(stub_path)[0]
    app = re.sub(r"\S*(ia32|x64)(.efi)\S*", r"boot\1\2", os.path.basename(stub))
    executable = '%s/%s' % (deploy_dir_image, app)

    if d.getVar('INITRD_LIVE'):
        with open(initrd, 'wb') as dst:
            for cpio in d.getVar('INITRD_LIVE').split():
                with open(cpio, 'rb') as src:
                    dst.write(src.read())
        initrd_cmd = "--add-section .initrd=%s --change-section-vma .initrd=0x3000000 " % initrd
    else:
        initrd_cmd = ""

    uuid = d.getVar('DISK_SIGNATURE_UUID')
    root = 'root=PARTUUID=%s' % uuid if uuid else ''

    with open(cmdline, 'w') as f:
        f.write('%s %s' % (d.getVar('APPEND'), root))

    objcopy_cmd = ("objcopy "
        "--add-section .cmdline=%s --change-section-vma .cmdline=0x30000 "
        "--add-section .linux=%s --change-section-vma .linux=0x40000 "
        "%s %s %s") % \
        (cmdline, linux, initrd_cmd, stub, executable)

    check_call(objcopy_cmd, shell=True)
}

do_uefiapp[vardeps] += "APPEND DISK_SIGNATURE_UUID INITRD_LIVE KERNEL_IMAGETYPE"

do_uefiapp_deploy() {
    rm -rf ${IMAGE_ROOTFS}/boot/*
    mkdir -p ${IMAGE_ROOTFS}/boot/EFI/BOOT
    cp  --preserve=timestamps ${DEPLOY_DIR_IMAGE}/boot*.efi ${IMAGE_ROOTFS}/boot/EFI/BOOT/
}

do_uefiapp_deploy[depends] += "${PN}:do_uefiapp"

do_uefiapp_sign() {
    if [ -f ${UEFIAPP_SIGNING_KEY} ] && [ -f ${UEFIAPP_SIGNING_CERT} ]; then
        for i in `find ${DEPLOY_DIR_IMAGE}/ -name 'boot*.efi'`; do
            sbsign --key ${UEFIAPP_SIGNING_KEY} --cert ${UEFIAPP_SIGNING_CERT} $i
            sbverify --cert ${UEFIAPP_SIGNING_CERT} $i.signed
            mv $i.signed $i
        done
    fi
}

do_uefiapp_sign[depends] += "${PN}:do_uefiapp_deploy \
                             sbsigntool-native:do_populate_sysroot"

# This decides when/how we add our tasks to the image
python () {
    import os
    import hashlib

    secureboot = bb.utils.contains('IMAGE_FEATURES', 'secureboot', True, False, d)
    # Ensure that if the signing key or cert change, we rerun the uefiapp process
    if secureboot:
        for varname in ('UEFIAPP_SIGNING_CERT', 'UEFIAPP_SIGNING_KEY'):
            filename = d.getVar(varname)
            if filename is None:
                bb.fatal('%s is not set.' % varname)
            if not os.path.isfile(filename):
                bb.fatal('%s=%s is not a file.' % (varname, filename))
            with open(filename, 'rb') as f:
                data = f.read()
            hash = hashlib.sha256(data).hexdigest()
            d.setVar('%s_HASH' % varname, hash)

            # Must reparse and thus rehash on file changes.
            bb.parse.mark_dependency(d, filename)

    image_fstypes = d.getVar('IMAGE_FSTYPES', True)
    initramfs_fstypes = d.getVar('INITRAMFS_FSTYPES', True)

    # Don't add any of these tasks to initramfs images
    if initramfs_fstypes not in image_fstypes:
        bb.build.addtask('uefiapp', 'do_image', 'do_rootfs', d)
        bb.build.addtask('uefiapp_deploy', 'do_image', 'do_rootfs', d)
        # Only sign if secureboot is enabled
        if secureboot:
            bb.build.addtask('uefiapp_sign', 'do_image', 'do_rootfs', d)
}

do_uefiapp[vardeps] += "UEFIAPP_SIGNING_CERT_HASH UEFIAPP_SIGNING_KEY_HASH"

# Legacy hddimg support below this line
efi_hddimg_populate() {
    DEST=$1
    cp  --preserve=timestamps -r ${DEPLOY_DIR_IMAGE}/boot*.efi ${DEST}/
}

build_efi_cfg() {
    # The command line is built into the combo app, so this is a null op
    :
}

populate_kernel_append() {
    # The kernel and initrd are built into the app, so we don't need these
    if [ -f $dest/initrd ]; then
        rm $dest/initrd
    fi
    if [ -f $dest/vmlinuz ]; then
        rm $dest/vmlinuz
    fi
}

IMAGE_FEATURES[validitems] += "secureboot"
