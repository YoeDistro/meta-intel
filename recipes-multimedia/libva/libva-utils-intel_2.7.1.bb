# Latest upgrades for media components depend upon libva-utils v2.7.1
# So need this upgrade

require recipes-graphics/libva/libva-utils_2.6.0.bb

SRC_URI_remove = "git://github.com/intel/libva-utils.git;branch=v2.6-branch;protocol=https"
SRC_URI_prepend = "git://github.com/intel/libva-utils.git;branch=v2.7-branch;protocol=https "

SRCREV = "5a24c635f6fb2b9ac31cab3360afca50e1860812"
S = "${WORKDIR}/git"

PROVIDES = "libva-utils"
RPROVIDES_${PN} += "libva-utils"
