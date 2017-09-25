FILESEXTRAPATHS_prepend_intel-x86-common := "${THISDIR}/${PN}:"


LTTNG_PATCH = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/kernel','linux-intel','file://0002-lttng-modules-PKT-4.9-yocto-build-failed.patch','',d)}"

SRC_URI += "${LTTNG_PATCH}"

