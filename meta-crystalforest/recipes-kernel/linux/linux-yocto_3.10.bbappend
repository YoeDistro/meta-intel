FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_crystalforest-gladden = "crystalforest-gladden"
KMACHINE_crystalforest-gladden  = "crystalforest"
KBRANCH_crystalforest-gladden  = "standard/common-pc-64/crystalforest"

SRCREV_machine_pn-linux-yocto_crystalforest-gladden ?= "78afd3095c9b37efbbfbfdc25eb3833ef3c6a718"
SRCREV_meta_pn-linux-yocto_crystalforest-gladden ?= "6e0e756d51372c8b176c5d1e6f786545bceed351"

COMPATIBLE_MACHINE_crystalforest-server = "crystalforest-server"
KMACHINE_crystalforest-server  = "crystalforest"
KBRANCH_crystalforest-server  = "standard/common-pc-64/crystalforest"

SRCREV_machine_pn-linux-yocto_crystalforest-server ?= "78afd3095c9b37efbbfbfdc25eb3833ef3c6a718"
SRCREV_meta_pn-linux-yocto_crystalforest-server ?= "6e0e756d51372c8b176c5d1e6f786545bceed351"

LINUX_VERSION = "3.10.32"

module_autoload_uio = "uio"
