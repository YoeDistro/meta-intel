SUMMARY = "Crypto Multi-buffer Library"
DESCRIPTION = "Intel® Integrated Performance Primitives (Intel® IPP) Cryptography \
is a secure, fast and lightweight library of building blocks for cryptography, \
highly-optimized for various Intel® CPUs."
HOMEPAGE = "https://github.com/intel/ipp-crypto"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://../../../LICENSE;md5=e787af283468feca985d6b865d27d95b"

SRC_URI = " \
            git://github.com/intel/ipp-crypto;protocol=https;branch=develop \
            file://0001-CMakeLists.txt-exclude-host-system-headers.patch;striplevel=4 \
            file://0002-cmake-exclude-Yocto-build-flags.patch;striplevel=4 \
            "
SRCREV = "46944bd18e6dbad491ef9b9a3404303ef7680c09"

S = "${WORKDIR}/git/sources/ippcp/crypto_mb"
PV = "2021.5+git${SRCPV}"

DEPENDS = "openssl"

inherit cmake pkgconfig
COMPATIBLE_HOST = '(x86_64).*-linux'

EXTRA_OECMAKE += " -DARCH=intel64"
EXTRA_OECMAKE += " -DTOOLCHAIN_OPTIONS='${TOOLCHAIN_OPTIONS}'"
