FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_sugarbay = "sugarbay"
KMACHINE_sugarbay  = "yocto/standard/common-pc-64/sugarbay"

SRCREV_machine_pn-linux-yocto_sugarbay ?= "ba0bb96c32235bca6fefccc2d1d92ed9e75df98b"
SRCREV_meta_pn-linux-yocto_sugarbay ?= "d386e09f316e03061c088d2b13a48605c20fb3a6"
