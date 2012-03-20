SUMMARY = "Big Buck Bunny video OGG sample "
DESCRIPTION = "Installs Big Buck Bunny Video OGG file samples in /home/video dir "

LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7"

DEPENDS += " " 


PR = r0
SRC_URI = "http://blender-mirror.kino3d.org/peach/bigbuckbunny_movies/big_buck_bunny_720p_stereo.ogg \
            "

do_install() {

install -d ${D}${base_prefix}/home/Videos
install -m 0644  ${WORKDIR}/*.ogg ${D}${base_prefix}/home/Videos
}

FILES_${PN} += "${base_prefix}/home/Videos/*.ogg"
