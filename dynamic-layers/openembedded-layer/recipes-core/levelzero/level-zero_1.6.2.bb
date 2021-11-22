# FIXME: the LIC_FILES_CHKSUM values have been updated by 'devtool upgrade'.
# The following is the difference between the old and the new license text.
# Please update the LICENSE value if needed, and summarize the changes in
# the commit message via 'License-Update:' tag.
# (example: 'License-Update: copyright years updated.')
#
# The changes:
#
# --- LICENSE
# +++ LICENSE
# @@ -1,6 +1,6 @@
#  MIT License
#  
# -Copyright (c) 2019 Intel Corporation
# +Copyright (C) 2019-2021 Intel Corporation
#  
#  Permission is hereby granted, free of charge, to any person obtaining a copy
#  of this software and associated documentation files (the "Software"), to deal
# 
#

SUMMARY = "oneAPI Level Zero Specification Headers and Loader"
HOMEPAGE = "https://github.com/oneapi-src/level-zero"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=97957beb2f7808ffa247e5d93e6442cc"

SRC_URI = "git://github.com/oneapi-src/level-zero.git;protocol=https;branch=master"
SRCREV = "7afa8a6dda56d4baef950c0a9d5ef8d8b0e14c4e"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "opencl-headers"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"

PACKAGES =+ "${PN}-headers ${PN}-samples ${PN}-loader"

do_install:append () {
        install -d ${D}${bindir} ${D}${libdir}
        install -m 755 ${B}/bin/zello* ${D}${bindir}

        oe_libinstall -C lib libze_null ${D}${libdir}
}


FILES:${PN}-headers = "${includedir}"
FILES:${PN}-samples = "${bindir} ${libdir}/libze_null* ${libdir}/libze_validation*"
FILES:${PN}-loader = "${libdir}"

# PN-loader (non -dev/-dbg/nativesdk- package) contains symlink .so
INSANE_SKIP:${PN}-loader = "dev-so"
INSANE_SKIP:${PN}-samples = "dev-so"
ALLOW_EMPTY:${PN} = "1"
