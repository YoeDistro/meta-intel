FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://time-limited-kernel.cfg \
                   file://uptime-allow-the-optional-limiting-of-kernel-runtime.patch"
