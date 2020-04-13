# Latest upgrades for media components depend upon libva-utils v2.7.1
# So need this upgrade

require recipes-graphics/libva/libva-utils_2.6.0.bb

SRC_URI = "git://github.com/intel/libva-utils.git;branch=v2.7-branch"
SRCREV = "5a24c635f6fb2b9ac31cab3360afca50e1860812"
S = "${WORKDIR}/git"
