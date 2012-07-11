SUMMARY = "ogg file format music samples"
DESCRIPTION = "Installs ogg file format music samples in /home/Music dir"

LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7"

DEPENDS += " " 


PR = r0
SRC_URI = "http://downloads.yoctoproject.org/releases/media/music/ogg-CC-BY-3.0-music-samples-${PV}.tar.bz2 \
            "

do_install() {

install -d ${D}${base_prefix}/home/music
install -m 0644  ${WORKDIR}/ogg-CC-BY-3.0-music-samples-${PV}/*/*.ogg ${D}${base_prefix}/home/music
}

FILES_${PN} += "${base_prefix}/home/music/*.ogg"
