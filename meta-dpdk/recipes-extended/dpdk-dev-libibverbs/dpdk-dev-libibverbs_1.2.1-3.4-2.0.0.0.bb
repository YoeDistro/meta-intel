DESCRIPTION = "libibverbs library to support Mellanox config"
HOMEPAGE = "https://github.com/Mellanox/dpdk-dev-libibverbs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM =  "file://COPYING;md5=7c557f27dd795ba77cc419dddc656b51"

SRC_URI = "https://github.com/Mellanox/dpdk-dev-libibverbs/archive/libibverbs-${PV}.tar.gz;name=${PN} \
           file://init_c.patch \
           file://0001-Fix-build-with-clang.patch \
           file://0002-typecast-enum-to-int-before-comparison.patch \
           file://0003-initialize-use_config_mr.patch \
           file://0004-Fix-clang-warnings.patch \
           "

SRC_URI[dpdk-dev-libibverbs.md5sum] = "65234ee278eb437a7069326f37cd4d86"
SRC_URI[dpdk-dev-libibverbs.sha256sum] = "a6471515556cb8d10ad471bb7efb8cf760b248a28aceb57d4534d50d572f56cd"

# A machine needs to enable this using:
# COMPATIBLE_MACHINE_pn-dpdk-dev-libibverbs = "<machine name>"

COMPATIBLE_MACHINE = "null"
COMPATIBLE_HOST_libc-musl_class-target = "null"

S = "${WORKDIR}/${PN}-libibverbs-${PV}"
COMPATIBLE_HOST = '(i.86|x86_64).*-linux'
DEPENDS = "libnl"

inherit pkgconfig autotools
