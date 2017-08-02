include dpdk.inc

SRC_URI += "\
            file://dpdk-16.04-dpdk-enable-ip_fragmentation-in-common_base-config.patch \
            file://0001-examples-Fix-maybe-uninitialized-warning.patch \
            "

SRC_URI[dpdk.md5sum] = "39c4e1110dd1ef9dab33edbae820f939"
SRC_URI[dpdk.sha256sum] = "763bfb7e1765efcc949e79d645dc9f1ebd16591431ba0db5ce22becd928dcd0a"

export EXAMPLES_BUILD_DIR = "${RTE_TARGET}"
export ARCHDIR = "generic"

do_configure_prepend () {
	sed -e "s#CONFIG_RTE_LIBRTE_POWER=y#CONFIG_RTE_LIBRTE_POWER=${CONFIG_EXAMPLE_VM_POWER_MANAGER}#" -i ${S}/config/common_linuxapp
}

COMPATIBLE_HOST_linux-gnux32 = "null"
COMPATIBLE_HOST_libc-musl_class-target = "null"
