FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#############################
# MACHINE = valleyisland-32 #
#############################
COMPATIBLE_MACHINE_valleyisland-32 = "valleyisland-32"
KMACHINE_valleyisland-32 = "valleyisland-32"
KBRANCH_valleyisland-32 = "standard/base"
KERNEL_FEATURES_valleyisland-32 = " features/valleyisland-io/valleyisland-io.scc \
				    features/valleyisland-io/valleyisland-io-pci.scc"

LINUX_VERSION_valleyisland-32 = "3.10.65"
SRCREV_machine_valleyisland-32 = "6d28dec479d475d4f3ab1682ee0d5e1e3afc22c7"
SRCREV_meta_valleyisland-32 = "f90490d50fb51f7e1206c838731185670276d108"
SRCREV_valleyisland-io_valleyisland-32 = "f15b3c66b653f8a021b0276f0d54451f9e713863"

SRC_URI_valleyisland-32 = "git://git.yoctoproject.org/linux-yocto-3.10.git;protocol=git;nocheckout=1;branch=${KBRANCH},${KMETA},valleyisland-io-4.0;name=machine,meta,valleyisland-io"

#############################
# MACHINE = valleyisland-64 #
#############################
COMPATIBLE_MACHINE_valleyisland-64 = "valleyisland-64"
KMACHINE_valleyisland-64 = "valleyisland"
KBRANCH_valleyisland-64 = "standard/base"
KERNEL_FEATURES_valleyisland-64 = " features/valleyisland-io/valleyisland-io.scc \
				    features/valleyisland-io/valleyisland-io-pci.scc"

LINUX_VERSION_valleyisland-64 = "3.10.65"
SRCREV_machine_valleyisland-64 = "6d28dec479d475d4f3ab1682ee0d5e1e3afc22c7"
SRCREV_meta_valleyisland-64 = "f90490d50fb51f7e1206c838731185670276d108"
SRCREV_valleyisland-io_valleyisland-64 = "f15b3c66b653f8a021b0276f0d54451f9e713863"

SRC_URI_valleyisland-64 = "git://git.yoctoproject.org/linux-yocto-3.10.git;protocol=git;nocheckout=1;branch=${KBRANCH},${KMETA},valleyisland-io-4.0;name=machine,meta,valleyisland-io"

KERNEL_MODULE_AUTOLOAD_append_valleyisland-32 = " i2c-dev"
KERNEL_MODULE_AUTOLOAD_append_valleyisland-64 = " i2c-dev"
