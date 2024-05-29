SUMMARY = "User Mode Driver for Intel® NPU device"
HOMEPAGE = "https://github.com/intel/linux-npu-driver"
LICENSE = "MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=37acda99f3f9c108e62d970fe0e08027 \
                    file://third-party-programs.txt;md5=dbf0d7a91947cccc5410e9760d9acae5 \
                    file://third_party/vpux_elf/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/intel/linux-npu-driver.git;protocol=https;name=linux-npu-driver;branch=main;lfs=1 \
            git://github.com/openvinotoolkit/npu_plugin_elf.git;protocol=https;destsuffix=git/third_party/vpux_elf;name=vpux-elf;nobranch=1 \
            git://github.com/jbeder/yaml-cpp.git;protocol=https;destsuffix=git/third_party/yaml-cpp;name=yaml-cpp;nobranch=1 \
            git://github.com/intel/level-zero-npu-extensions.git;protocol=https;destsuffix=git/third_party/level-zero-vpu-extensions;name=lzvext;nobranch=1 \
            git://github.com/google/googletest.git;protocol=https;destsuffix=git/third_party/googletest;name=googletest;nobranch=1 \
            file://0001-Fix-the-compilation-warning-when-using-gcc-13-25.patch \
            file://0002-Fix-compilation-failure-with-GCC-14.patch \
        "

SRCREV_linux-npu-driver = "9d1dd3daa01ebd97a4ac2e8279ddd6e2cb109244"
SRCREV_vpux-elf = "03878c115d13aa1ce6af5329c5759fc1cc94a3fb"
SRCREV_yaml-cpp = "0579ae3d976091d7d664aa9d2527e0d0cff25763"
SRCREV_lzvext = "0e1c471356a724ef6d176ba027a68e210d90939e"
SRCREV_googletest = "b796f7d44681514f58a683a3a71ff17c94edb0c1"
SRCREV_FORMAT = "linux-npu-driver_vpux-elf_yaml-cpp_lzvext_googletest"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS = "level-zero"

PACKAGES =+ "${PN}-firmware ${PN}-tests"

FILES:${PN}-firmware = "${libdir}/firmware/updates/intel/vpu/*"
FILES:${PN}-tests = "${bindir}"
