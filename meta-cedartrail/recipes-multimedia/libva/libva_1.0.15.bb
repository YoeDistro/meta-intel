require libva.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "R0" 

CDR_TRAIL = "download.meego.com/live/MeeGo:/1.2.0:/CedarTrail:"

SRC_URI = " \
    http://${CDR_TRAIL}/oss/standard/i586/libva-1.0.15-1.1.i586.rpm;name=binary  \
    http://${CDR_TRAIL}/oss/standard/i586/libva-devel-1.0.15-1.1.i586.rpm;name=devel  \
"

SRC_URI[binary.md5sum] = "cb326945cec5ea4d1d369efc7a56e4f4"
SRC_URI[binary.sha256sum] = "e96f44647d5e9a4e2a7c769ed24d6bc142d928041300ea3e2e76e6af2d154926"
SRC_URI[devel.md5sum] = "799348cf8b6d239076d1b11844b94299"
SRC_URI[devel.sha256sum] = "f1ae4028d471790a1e7d601b69106824e4628e6db380e91eaaf08fa493e1a802"

do_install() {

install -m 0644 ${WORKDIR}/libva-1.0.15-1.1.i586.rpm ${D}
install -m 0644 ${WORKDIR}/libva-devel-1.0.15-1.1.i586.rpm ${D}

cd ${D}

rpm2cpio libva-1.0.15-1.1.i586.rpm | cpio -idmv
rpm2cpio libva-devel-1.0.15-1.1.i586.rpm | cpio -idmv

rm -f *.rpm
}
