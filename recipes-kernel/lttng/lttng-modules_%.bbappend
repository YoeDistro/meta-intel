FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/${PN}:"

LTTNG_PATCH_SET = " \
            file://0001-Fix-mm-create-the-new-vm_fault_t-type-v5.1.patch \
            file://0002-Fix-rcu-Remove-wrapper-definitions-for-obsolete-RCU..patch \
            file://0003-Fix-pipe-stop-using-can_merge-v5.1.patch \
            file://0004-Fix-Revert-KVM-MMU-show-mmu_valid_gen.-v5.1.patch \
            file://0005-Fix-Remove-start-and-number-from-syscall_get_argumen.patch \
            "

SRC_URI_append_intel-x86-common = "${@bb.utils.contains_any('PREFERRED_PROVIDER_virtual/kernel','linux-intel-dev','${LTTNG_PATCH_SET}','',d)}"

