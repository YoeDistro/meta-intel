SUMMARY  = "Collection of high-performance ray tracing kernels"
DESCRIPTION = "A collection of high-performance ray tracing kernels \
intended to graphics application engineers that want to improve the \
performance of their application."
HOMEPAGE = "https://github.com/embree/embree"

LICENSE  = "Apache-2.0 AND BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third-party-programs.txt;md5=f989f5b74cfff0d45d3ccf0e1366cbdc \
                    file://common/math/transcendental.h;beginline=6;endline=8;md5=73380bb2ab6613b30b8464f114bd0ca8"

inherit pkgconfig cmake

# ISPC generates binaries with embedded build paths
INSANE_SKIP:${PN}-dbg += "buildpaths"

# create_isa_dummy_file.cmake generates per-ISA wrappers (*.avx2.cpp etc.) that
# #include the original source by absolute path. These wrappers are collected
# into -src, so rewrite the include to the debug-source path they are mapped to.
# Done at package time (not after compile) so ninja never sees an unusable path,
# and the original mtime is preserved so a later forced compile/install does not
# consider the wrappers stale and try to rebuild them.
PACKAGE_PREPROCESS_FUNCS += "embree_fix_isa_dummy_paths"
embree_fix_isa_dummy_paths() {
    find ${B} -name '*.cpp.*.cpp' -type f | while read f; do
        touch -r "$f" "$f.mtime"
        sed -i -e 's|^#include "${S}/|#include "${TARGET_DBGSRC_DIR}/|' "$f"
        touch -r "$f.mtime" "$f"
        rm -f "$f.mtime"
    done
}

SRC_URI = "git://github.com/embree/embree.git;protocol=https;branch=master"
SRCREV = "f590db83ef6559387df7f6d8725c34fb7acf851d"

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

DEPENDS = "tbb jpeg libpng ispc-native"

EXTRA_OECMAKE += " \
                  -DEMBREE_IGNORE_CMAKE_CXX_FLAGS=OFF  \
                  -DEMBREE_MAX_ISA=DEFAULT  \
                  -DEMBREE_ISPC_SUPPORT=ON  \
                  -DEMBREE_ZIP_MODE=OFF  \
                  "
# When tutorials are enabled, glvnd needs to be enabled:
# DISTRO_FEATURES += "glvnd"
PACKAGECONFIG[tutorial] = "-DEMBREE_TUTORIALS=ON,-DEMBREE_TUTORIALS=OFF,glfw"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>(\d+(\.\d+)+))$"
