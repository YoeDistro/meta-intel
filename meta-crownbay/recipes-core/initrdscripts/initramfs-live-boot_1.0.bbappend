FILESEXTRAPATHS := "${THISDIR}/${PN}"
S = "${WORKDIR}"

PR .= ".1"

SRC_URI_append += " file://0001-init-live.sh-add-coldplug-udev-trigger.patch"
