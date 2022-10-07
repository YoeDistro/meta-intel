FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

ICC_INC = "${@bb.utils.contains('ICCSDK', '1', 'icc-environment.inc', '', d)}"
require ${ICC_INC}
