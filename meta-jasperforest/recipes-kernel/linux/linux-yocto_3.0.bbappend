FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_jasperforest = "jasperforest"

KMACHINE_jasperforest  = "yocto/standard/common-pc-64/jasperforest"

SRCREV_machine_pn-linux-yocto_jasperforest ?= "42e08dcf8cb1a6358b7c1b778309767e0e9cce39"
SRCREV_meta_pn-linux-yocto_jasperforest ?= "d386e09f316e03061c088d2b13a48605c20fb3a6"
