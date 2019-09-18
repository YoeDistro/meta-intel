FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/files:"

LLVM_TARGETS_TO_BUILD = "X86"

do_install_append_intel-x86-common() {
        DESTDIR=${D} ninja -v install-cmake-exports
}

LIBCPLUSPLUS = ""

