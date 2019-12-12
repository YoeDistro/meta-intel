SUMMARY = "C++ framework for implementing distributed and networked applications"
DESCRIPTION = "C++ network programming framework that implements many core \
patterns for concurrent communication software"
LICENSE = "ACE-TAO-CIAO"
HOMEPAGE = "http://www.dre.vanderbilt.edu/~schmidt/ACE.html"
LIC_FILES_CHKSUM = "file://COPYING;md5=407a202d1b887b998dc9480442840630"

DEPENDS += "openssl gperf-native"

SRC_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE%2BTAO-6_5_3/ACE-${PV}.tar.bz2 \
           file://ace_config.patch \
           file://config_linux.patch \
          "

SRC_URI[md5sum] = "4cc5f109ebd17cd56f0539d1b47d16b3"
SRC_URI[sha256sum] = "b1d6a716394bd15c21bb90037b8a12a4d8034cc9d8878b0ad39b3c467df19b1a"

COMPATIBLE_HOST_libc-musl = "null"

S = "${WORKDIR}/ACE_wrappers"
B = "${WORKDIR}/ACE_wrappers/ace"
export ACE_ROOT="${WORKDIR}/ACE_wrappers"

inherit pkgconfig

CXXFLAGS_append = " -fpermissive -Wnodeprecated-declarations"

do_install() {
    export D="${D}"
    oe_runmake install

    for i in $(find ${D} -name "*.pc") ; do
        sed -i -e s:${D}::g \
               -e s:/${TARGET_SYS}::g \
                  $i
    done

    rm -r ${D}/usr/share
}

