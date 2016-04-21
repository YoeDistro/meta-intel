FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KMETA ?= "kernel-meta"

KMACHINE_valleyisland-32 = "valleyisland-32"
KMACHINE_valleyisland-64 = "valleyisland"

KBRANCH_valleyisland-32 = "standard/valleyisland"
KBRANCH_valleyisland-64 = "standard/valleyisland"

SRCREV_machine_valleyisland-32 = "b19dce5f446bf47c22898b3836c218d7d9d8cf09"
SRCREV_machine_valleyisland-64 = "b19dce5f446bf47c22898b3836c218d7d9d8cf09"
SRCREV_meta_valleyisland-32 = "189154c0ed2d77e211f3646e7b599a71e02d7fe1"
SRCREV_meta_valleyisland-64 = "189154c0ed2d77e211f3646e7b599a71e02d7fe1"

LINUX_VERSION_valleyisland-32 ?= "3.14.62"
LINUX_VERSION_valleyisland-64 ?= "3.14.62"

COMPATIBLE_MACHINE = "valleyisland-32|valleyisland-64"
KERNEL_FEATURES_append_valleyisland-32 = " features/soc/baytrail/baytrail.scc"
KERNEL_FEATURES_append_valleyisland-64 = " features/soc/baytrail/baytrail.scc"

KERNEL_MODULE_AUTOLOAD_append_valleyisland-32 = " i2c-dev"
KERNEL_MODULE_AUTOLOAD_append_valleyisland-64 = " i2c-dev"
