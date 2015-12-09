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
SRCREV_machine_valleyisland-32 = "3d438b8a85a7b58779a344ed322d8c862c648c06"
SRCREV_meta_valleyisland-32 = "477fe6a3cbe68c4d5c2fea948b15b60267363454"
SRCREV_valleyisland-io_valleyisland-32 = "55695e25685bc6bfbbfd001cb1790a1e45c626b5"

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
SRCREV_machine_valleyisland-64 = "3d438b8a85a7b58779a344ed322d8c862c648c06"
SRCREV_meta_valleyisland-64 = "477fe6a3cbe68c4d5c2fea948b15b60267363454"
SRCREV_valleyisland-io_valleyisland-64 = "55695e25685bc6bfbbfd001cb1790a1e45c626b5"

SRC_URI_valleyisland-64 = "git://git.yoctoproject.org/linux-yocto-3.10.git;protocol=git;nocheckout=1;branch=${KBRANCH},${KMETA},valleyisland-io-4.0;name=machine,meta,valleyisland-io"

KERNEL_MODULE_AUTOLOAD_append_valleyisland-32 = " i2c-dev"
KERNEL_MODULE_AUTOLOAD_append_valleyisland-64 = " i2c-dev"
