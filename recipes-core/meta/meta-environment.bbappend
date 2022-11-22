FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

require ${@bb.utils.contains('ICXSDK', '1', 'icx-environment.inc', '', d)}
