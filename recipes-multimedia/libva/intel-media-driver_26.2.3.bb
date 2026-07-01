SUMMARY = "VA driver for Intel Gen based graphics hardware"
DESCRIPTION = "Intel Media Driver for VAAPI is a new VA-API (Video Acceleration API) \
user mode driver supporting hardware accelerated decoding, encoding, \
and video post processing for GEN based graphics hardware."

HOMEPAGE = "https://github.com/intel/media-driver"
BUGTRACKER = "https://github.com/intel/media-driver/issues"

LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=6aab5363823095ce682b155fef0231f0 \
                    file://media_driver/media_libvpx.LICENSE;md5=d5b04755015be901744a78cc30d390d4 \
                    "

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

inherit features_check
REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS += "libva gmmlib"

SRC_URI = "git://github.com/intel/media-driver.git;protocol=https;nobranch=1 \
           file://0003-Force-ARGB-surface-to-tile4-for-ACM.patch \
           file://0004-Fix-failed-4k-videowalll-test-case-and-color-corrupt.patch \
          "

SRCREV = "dcccc9f760fd03e34b528b70b954c301ce509901"

# media-driver 26.x adds 700+ per-platform include dirs. With the default
# long source path (${BP}) this overflows GCC's COLLECT_GCC_OPTIONS env
# string past the kernel MAX_ARG_STRLEN limit (E2BIG) when g++ execs cc1plus.
# Unpack into a short "git" dir so each -I path is shorter, and force
# CMake/Ninja to keep the compiler include lists in response files.
BB_GIT_DEFAULT_DESTSUFFIX = "git"
S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"

# Forcing response files (below) keeps the g++ *command line* short, but g++
# still re-packs every option into the single COLLECT_GCC_OPTIONS environment
# string before exec()'ing cc1plus. With 700+ absolute -I paths that one string
# is ~175 KiB and blows past the kernel's per-string MAX_ARG_STRLEN limit
# (128 KiB), so the build fails with:
#   cc1plus: posix_spawn: Argument list too long
# Ninja runs each compile from ${B} (= ${WORKDIR}/build) and every generated
# -I path lives under ${WORKDIR}, so rewriting the absolute prefix to a
# relative "../" leaves the includes valid while cutting each path by ~180
# bytes -- shrinking the longest include string from ~172 KiB to ~54 KiB, well
# under the limit. Ninja regenerates the per-file .rsp from the INCLUDES
# variable in build.ninja on every run, so the rewrite must target build.ninja
# itself (editing the .rsp files is undone by Ninja). Do it after configure so
# it survives into do_compile; nothing is left dirty, so Ninja does not
# reconfigure and regenerate the absolute paths.
do_configure:append() {
    sed -i -e 's| -I${WORKDIR}/| -I../|g' ${B}/build.ninja
}

COMPATIBLE_HOST:x86-x32 = "null"

UPSTREAM_CHECK_GITTAGREGEX = "^intel-media-(?P<pver>(?!600\..*)\d+(\.\d+)+)$"

inherit cmake pkgconfig

MEDIA_DRIVER_ARCH:x86    = "32"
MEDIA_DRIVER_ARCH:x86-64 = "64"

EXTRA_OECMAKE += " \
                   -DMEDIA_RUN_TEST_SUITE=OFF \
                   -DARCH=${MEDIA_DRIVER_ARCH} \
                   -DMEDIA_BUILD_FATAL_WARNINGS=OFF \
                   -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
                   -DCMAKE_NINJA_FORCE_RESPONSE_FILE=ON \
		  "

CXXFLAGS:append:x86 = " -D_FILE_OFFSET_BITS=64 -D_LARGEFILE_SOURCE"

do_configure:prepend:toolchain-clang() {
    sed -i -e '/-fno-tree-pre/d' ${S}/media_driver/cmake/linux/media_compile_flags_linux.cmake
}

FILES:${PN} += " \
                 ${libdir}/dri/ \
                 "
