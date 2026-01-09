SUMMARY  = "Intel OSPray, Ray Tracing based Rendering Engine"
DESCRIPTION = "Intel OSPRay is an open source, scalable, and portable ray \
tracing engine for high-performance, high-fidelity visualization on \
Intel Architecture CPUs."
HOMEPAGE = "https://www.ospray.org/"

LICENSE  = "Apache-2.0 & BSD-3-Clause & MIT & Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1dece7821bf3fd70fe1309eaa37d52a2 \
                    file://third-party-programs.txt;md5=e37b77e3bd997abccc359c710fb1f1db \
                    "

inherit pkgconfig cmake

SRC_URI = "git://github.com/ospray/ospray.git;protocol=https;branch=master;name=ospray  \
           git://github.com/nothings/stb;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/apps/common/external/stb_image/stb;name=stb;nobranch=1 \
          "
SRCREV_ospray ?= "85af2929937d516997451cbd52d352cf93125ed2"
SRCREV_stb ?= "af1a5bc36dff44f0a019df21c271db697d51f1a6"
SRCREV_FORMAT = "ospray_stb"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS = "rkcommon ispc ispc-native openvkl embree"

EXTRA_OECMAKE += " \
                  -DISPC_EXECUTABLE=${STAGING_BINDIR_NATIVE}/ispc \
                  -DOSPRAY_ENABLE_APPS_BENCHMARK=OFF \
                  -DOSPRAY_ENABLE_APPS_TESTING=OFF \
                  -DOSPRAY_ENABLE_APPS_EXAMPLES=OFF \
                  "

PACKAGES =+ "${PN}-apps"
FILES:${PN}-apps = "\
                     ${bindir} \
                     "

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"
