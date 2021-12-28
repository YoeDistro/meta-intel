SUMMARY = "Slim Bootloader"
DESCRIPTION = "Slim Bootloader is an open-source boot firmware, built from the \
ground up to be small, secure and optimized running on Intel x86 architecture."
HOMEPAGE = "https://slimbootloader.github.io"

LICENSE = "BSD-2-Clause-Patent & MIT & Apache-2.0 & Python-2.0"

SRC_URI = "git://github.com/slimbootloader/slimbootloader;protocol=https;branch=master"
SRCREV = "07b7a1f0e017de6f4041a04e12c44e9574126dfe"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef7fba7be2819ac13aaf5d0f842ce5d9 \
                    file://Licenses/EDK2_License.txt;md5=6123e5bf044a66db96c4ce88a36b2d08 \
                    file://Licenses/IPP_License.txt;md5=e3fc50a88d0a364313df4b21ef20c29e \
                    file://Licenses/Lz4_License.txt;md5=093ffc6380c6b1dadf52045a6e44a874 \
                    file://Licenses/MIT_License.txt;md5=f0f3a517d46b5f0ca048b58f503b6dc1 \
                    file://Licenses/NetBSD_License.txt;md5=1811b558fd7e03c491ca7f665eaf5529 \
                    file://Licenses/Python_License.txt;md5=dd98d01d471fac8d8dbdd975229dba03 \
                   "
PV = "0.0.0+git${SRCPV}"

inherit python3native

DEPENDS = "openssl-native nasm-native acpica-native util-linux-native"
S = "${WORKDIR}/git"

do_configure[noexec] = "1"

SLIMBOOT_TARGET ?= "qemu"
SLIMBOOT_KEY_DIR ?= "keys"

do_compile() {
    # WA: To overcome direct call to "python" in scripts of slimbootloader
    ln -sf ${PYTHON} ${STAGING_BINDIR_NATIVE}/python

    cd ${S}
    rm -rf ${SLIMBOOT_KEY_DIR}; mkdir -p ${SLIMBOOT_KEY_DIR}
    export SBL_KEY_DIR=${S}/${SLIMBOOT_KEY_DIR}
    ${PYTHON} BootloaderCorePkg/Tools/GenerateKeys.py -k ${SBL_KEY_DIR}

    # Currently use EXTRA_OPTFLAGS to pass the include directory of sysroot-native to
    # bitbake build system.
    export EXTRA_OPTFLAGS="-I${STAGING_INCDIR_NATIVE}"

    export EXTRA_LDFLAGS="-L${STAGING_LIBDIR_NATIVE}"

    for target in ${SLIMBOOT_TARGET}; do
        ${PYTHON} BuildLoader.py build ${target}
    done
}

do_install() {
    for target in ${SLIMBOOT_TARGET}; do
        install -m 0755 -d ${D}${libexecdir}/slimboot/Outputs/${target}
        install -m 0755 ${S}/Outputs/${target}/* ${D}${libexecdir}/slimboot/Outputs/${target}
    done

    install -m 0644 -d ${D}${libexecdir}/slimboot/${SLIMBOOT_KEY_DIR}
    install -m 0644 ${S}/${SLIMBOOT_KEY_DIR}/* ${D}${libexecdir}/slimboot/${SLIMBOOT_KEY_DIR}
}
