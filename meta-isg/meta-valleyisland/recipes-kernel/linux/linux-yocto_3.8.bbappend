FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KMETA = "meta"

#############################
# MACHINE = valleyisland-32 #
#############################
COMPATIBLE_MACHINE_valleyisland-32 = "valleyisland-32"
KMACHINE_valleyisland-32 = "valleyisland-32"
KBRANCH_valleyisland-32 = "standard/common-pc/base"
KERNEL_FEATURES_valleyisland-32 = "features/valleyisland-io/valleyisland-io \
				   features/valleyisland-io/valleyisland-io-pci.scc \
				  "

LINUX_VERSION_valleyisland-32 = "3.8.13"
SRCREV_machine_valleyisland-32 ?= "6f3e338aa9496cf68ad03a98f66c2e98975829c7"
SRCREV_meta_valleyisland-32 ?= "4134410f6467a55ea735c46feefc649ef643650a"

#############################
# MACHINE = valleyisland-64 #
#############################
COMPATIBLE_MACHINE_valleyisland-64 = "valleyisland-64"
KMACHINE_valleyisland-64 = "valleyisland"
KBRANCH_valleyisland-64 = "standard/common-pc-64/base"
KERNEL_FEATURES_valleyisland-64 = "features/valleyisland-io/valleyisland-io \
				   features/valleyisland-io/valleyisland-io-pci.scc \
				  "

LINUX_VERSION_valleyisland-64 = "3.8.13"
SRCREV_machine_valleyisland-64 ?= "6f3e338aa9496cf68ad03a98f66c2e98975829c7"
SRCREV_meta_valleyisland-64 ?= "4134410f6467a55ea735c46feefc649ef643650a"

module_autoload_i2c-dev = "i2c-dev"
