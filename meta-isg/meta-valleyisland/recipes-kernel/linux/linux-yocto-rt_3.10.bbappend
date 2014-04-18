FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#############################
# MACHINE = valleyisland-32 #
#############################
COMPATIBLE_MACHINE_valleyisland-32 = "valleyisland-32"
KMACHINE_valleyisland-32 = "valleyisland-32"
KBRANCH_valleyisland-32 = "standard/preempt-rt/base"

KERNEL_FEATURES_append_valleyisland-32 = "features/valleyisland-io/valleyisland-io"

LINUX_VERSION_valleyisland-32 = "3.10.35"
SRCREV_machine_pn-linux-yocto-rt_valleyisland-32 = "d1e73a1e09f9641c44e7d713d1a6b5b088411459"
SRCREV_meta_pn-linux-yocto-rt_valleyisland-32 = "7df9ef8ee47dc9023044614210f4c1d9d916dd5f"

#############################
# MACHINE = valleyisland-64 #
#############################
COMPATIBLE_MACHINE_valleyisland-64 = "valleyisland-64"
KMACHINE_valleyisland-64 = "valleyisland"
KBRANCH_valleyisland-64 = "standard/preempt-rt/base"

KERNEL_FEATURES_append_valleyisland-64 = "features/valleyisland-io/valleyisland-io"

LINUX_VERSION_valleyisland-64 = "3.10.35"
SRCREV_machine_pn-linux-yocto-rt_valleyisland-64 = "d1e73a1e09f9641c44e7d713d1a6b5b088411459"
SRCREV_meta_pn-linux-yocto-rt_valleyisland-64 ="7df9ef8ee47dc9023044614210f4c1d9d916dd5f"

