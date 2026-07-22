SUMMARY = "User Mode Driver for Intel® NPU device"
HOMEPAGE = "https://github.com/intel/linux-npu-driver"
LICENSE = "Apache-2.0 AND MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=7b256470048be42466f7d10e1d6482e6 \
                    file://third-party-programs.txt;md5=709a31be7df69741673b3d97027abe8b \
                    file://third_party/npu_compiler_elf/LICENSE;md5=5f51ea09f42b161b3013558e48d0fb20 \
                   "

SRC_URI = "git://github.com/intel/linux-npu-driver.git;protocol=https;name=linux-npu-driver;branch=main;lfs=1 \
            git://github.com/openvinotoolkit/npu_plugin_elf.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/npu_compiler_elf;name=npu-compiler-elf;nobranch=1 \
            git://github.com/jbeder/yaml-cpp.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/yaml-cpp;name=yaml-cpp;nobranch=1 \
            git://github.com/intel/level-zero-vpu-extensions.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/level-zero-npu-extensions;name=lzvext;nobranch=1 \
            git://github.com/google/googletest.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/googletest;name=googletest;nobranch=1 \
            file://0001-linux-npu-driver-fix-multilib-install-issue.patch \
            file://0002-cmake-do-not-error-on-missing-field-initializers.patch \
        "

SRCREV_linux-npu-driver = "81a0ceb5e4c57ea6802174a070fbdd2738d0a235"
SRCREV_npu-compiler-elf = "82c444bcb9feb0f55fa33e18fbd711ec35426fba"
SRCREV_yaml-cpp = "f7320141120f720aecc4c32be25586e7da9eb978"
SRCREV_lzvext = "c7cb5d218ca14f6a81b3ef0bb89e718e9fcdba8e"
SRCREV_googletest = "b514bdc898e2951020cbdca1304b75f5950d1f59"
SRCREV_FORMAT = "linux-npu-driver_npu-compiler-elf_yaml-cpp_lzvext_googletest"

inherit cmake pkgconfig

# Upstream installs the NPU firmware under a dedicated "fw-npu" CMake component
# marked EXCLUDE_FROM_ALL, so the default "cmake --install" skips it and the
# ${PN}-firmware package ends up empty. Install that component explicitly.
do_install:append() {
    DESTDIR='${D}' cmake --install '${B}' --component fw-npu
}

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = 'null'

# Fix warning _FORTIFY_SOURCE requires compiling with optimization (-O)
EXTRA_OECMAKE += " -DCMAKE_BUILD_TYPE=Release "
EXTRA_OECMAKE += " -DCMAKE_CXX_FLAGS_RELEASE=-O2 "

EXTRA_OECMAKE += " -DCMAKE_INSTALL_FIRMWARE_DIR=${nonarch_base_libdir}"
EXTRA_OECMAKE += " -DCMAKE_POLICY_VERSION_MINIMUM=3.5"
EXTRA_OECMAKE += " -DCMAKE_CXX_FLAGS='-I${STAGING_INCDIR}/level_zero'"

DEPENDS = "level-zero dpkg-native level-zero-native"

PACKAGES =+ "${PN}-firmware ${PN}-tests"

FILES:${PN}-firmware = "${nonarch_base_libdir}/firmware/updates/intel/vpu/*"
FILES:${PN}-tests = "${bindir}"

INSANE_SKIP:${PN} += "buildpaths"
INSANE_SKIP:${PN}-dbg += "buildpaths"
INSANE_SKIP:${PN}-tests += "buildpaths"
INSANE_SKIP:${PN}-firmware += "buildpaths"
