FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#############################
# MACHINE = valleyisland-32 #
#############################
COMPATIBLE_MACHINE_valleyisland-32 = "valleyisland-32"
KMACHINE_valleyisland-32 = "valleyisland-32"
KBRANCH_valleyisland-32 = "standard/base"

KERNEL_FEATURES_append_valleyisland-32 = "features/valleyisland-io/valleyisland-io"

LINUX_VERSION_valleyisland-32 = "3.10.34"
SRCREV_machine_valleyisland-32 = "52f701a01977965b0809703135611dd750561319"
SRCREV_meta_valleyisland-32 = "df3aa753c8826127fb5ad811d56d57168551d6e4"

#############################
# MACHINE = valleyisland-64 #
#############################
COMPATIBLE_MACHINE_valleyisland-64 = "valleyisland-64"
KMACHINE_valleyisland-64 = "valleyisland"
KBRANCH_valleyisland-64 = "standard/base"

KERNEL_FEATURES_append_valleyisland-64 = "features/valleyisland-io/valleyisland-io"

LINUX_VERSION_valleyisland-64 = "3.10.34"
SRCREV_machine_valleyisland-64 = "52f701a01977965b0809703135611dd750561319"
SRCREV_meta_valleyisland-64 = "df3aa753c8826127fb5ad811d56d57168551d6e4"
