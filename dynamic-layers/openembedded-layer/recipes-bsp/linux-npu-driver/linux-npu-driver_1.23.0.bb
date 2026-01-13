SUMMARY = "User Mode Driver for Intel® NPU device"
HOMEPAGE = "https://github.com/intel/linux-npu-driver"
LICENSE = "MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=7b256470048be42466f7d10e1d6482e6 \
                    file://third-party-programs.txt;md5=0ae40d7f1ef3bbd509197e427fdd7e70 \
                    file://third_party/vpux_elf/LICENSE;md5=5f51ea09f42b161b3013558e48d0fb20 \
                   "

SRC_URI = "git://github.com/intel/linux-npu-driver.git;protocol=https;name=linux-npu-driver;branch=main;lfs=1 \
            git://github.com/openvinotoolkit/npu_plugin_elf.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/vpux_elf;name=vpux-elf;nobranch=1 \
            git://github.com/jbeder/yaml-cpp.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/yaml-cpp;name=yaml-cpp;nobranch=1 \
            git://github.com/intel/level-zero-npu-extensions.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/level-zero-npu-extensions;name=lzvext;nobranch=1 \
            git://github.com/google/googletest.git;protocol=https;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/third_party/googletest;name=googletest;nobranch=1 \
            file://0001-linux-npu-driver-fix-multilib-install-issue.patch \
        "

SRCREV_linux-npu-driver = "59845101f5445e66dcc691f4f8be6371700fbdf5"
SRCREV_vpux-elf = "4b0a4a06ae09c0c3a973f8f18761c549ec2309eb"
SRCREV_yaml-cpp = "f7320141120f720aecc4c32be25586e7da9eb978"
SRCREV_lzvext = "8cf113bd4a4568f6555d81f316504d7ac3b82ee8"
SRCREV_googletest = "b514bdc898e2951020cbdca1304b75f5950d1f59"
SRCREV_FORMAT = "linux-npu-driver_vpux-elf_yaml-cpp_lzvext_googletest"

inherit cmake


# Fix warning _FORTIFY_SOURCE requires compiling with optimization (-O)
EXTRA_OECMAKE += " -DCMAKE_BUILD_TYPE=Release "
EXTRA_OECMAKE += " -DCMAKE_CXX_FLAGS_RELEASE=-O2 "

EXTRA_OECMAKE += " -DCMAKE_INSTALL_FIRMWARE_DIR=${nonarch_base_libdir}"
EXTRA_OECMAKE += " -DCMAKE_POLICY_VERSION_MINIMUM=3.5"
EXTRA_OECMAKE += " -DCMAKE_CXX_FLAGS='-I${STAGING_INCDIR}/level_zero'"

DEPENDS = "level-zero dpkg-native pkgconfig-native level-zero-native"

PACKAGES =+ "${PN}-firmware ${PN}-tests"

FILES:${PN}-firmware = "${nonarch_base_libdir}/firmware/updates/intel/vpu/*"
FILES:${PN}-tests = "${bindir}"

INSANE_SKIP:${PN} += "buildpaths"
INSANE_SKIP:${PN}-dbg += "buildpaths"
INSANE_SKIP:${PN}-tests += "buildpaths"
INSANE_SKIP:${PN}-firmware += "buildpaths"
