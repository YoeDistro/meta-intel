FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_sys940x = "sys940x"
KMACHINE_sys940x  = "sys940x"
KBRANCH_sys940x = "standard/sys940x"
SRCREV_machine_pn-linux-yocto_sys940x ?= "218bd8d2022b9852c60d32f0d770931e3cf343e2"
SRCREV_meta_pn-linux-yocto_sys940x ?= "68a635bf8dfb64b02263c1ac80c948647cc76d5f"

COMPATIBLE_MACHINE_sys940x-noemgd = "sys940x-noemgd"
KMACHINE_sys940x-noemgd  = "sys940x"
KBRANCH_sys940x-noemgd = "standard/sys940x"
SRCREV_machine_pn-linux-yocto_sys940x-noemgd ?= "218bd8d2022b9852c60d32f0d770931e3cf343e2"
SRCREV_meta_pn-linux-yocto_sys940x-noemgd ?= "68a635bf8dfb64b02263c1ac80c948647cc76d5f"

LINUX_VERSION = "3.4.11"
