# Latest upgrades for media components depend upon libva v2.7.0
# So need this upgrade

require recipes-graphics/libva/libva_2.6.1.bb

SRC_URI_remove = "https://github.com/intel/${BPN}/releases/download/${PV}/${BP}.tar.bz2"
SRC_URI_prepend = "https://github.com/intel/libva/releases/download/${PV}/libva-${PV}.tar.bz2 "

SRC_URI[md5sum] = "bd5052569520e734eb8aeb0f503cfcae"
SRC_URI[sha256sum] = "b75be416615dea75c74314fae8919dd72ca46d06b4e009e029661c2c51d87d70"

S = "${WORKDIR}/libva-${PV}"

PROVIDES = "libva"
RPROVIDES_${PN} += "libva"
