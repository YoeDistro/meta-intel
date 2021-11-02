SUMMARY = "Crypto Multi-buffer Library"
DESCRIPTION = "Intel® Integrated Performance Primitives (Intel® IPP) Cryptography \
is a secure, fast and lightweight library of building blocks for cryptography, \
highly-optimized for various Intel® CPUs."
HOMEPAGE = "https://github.com/intel/ipp-crypto"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://../../../LICENSE;md5=e787af283468feca985d6b865d27d95b"

SRC_URI = " \
            git://github.com/intel/ipp-crypto;protocol=https;branch=ipp-crypto_2021_3 \
            file://0001-GNU.cmake-allow-to-pass-compiler-and-linker-flags.patch;striplevel=4 \
            file://0003-CMakeLists.txt-exclude-host-system-headers.patch;striplevel=4 \
            "

SRCREV = "d9d13aaaf8889753fb58a13c2652c39b67c2076b"

S = "${WORKDIR}/git/sources/ippcp/crypto_mb"

DEPENDS = "openssl"

inherit cmake pkgconfig
COMPATIBLE_HOST = '(x86_64).*-linux'

# error: 'SHA512_Init' is deprecated: Since OpenSSL 3.0 [-Werror=deprecated-declarations]
CFLAGS:append = " -Wno-error=deprecated-declarations"

EXTRA_OECMAKE += " -DARCH=intel64"
