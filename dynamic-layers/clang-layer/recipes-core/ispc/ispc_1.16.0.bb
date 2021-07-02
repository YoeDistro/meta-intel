SUMMARY  = "Intel(R) Implicit SPMD Program Compiler"
DESCRIPTION = "ispc is a compiler for a variant of the C programming language, \
with extensions for single program, multiple data programming."
HOMEPAGE = "https://github.com/ispc/ispc"

LICENSE  = "BSD-3-Clause & Apache-2.0-with-LLVM-exception"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=da5ecffdd210b3cf776b32b41c182e87 \
                    file://third-party-programs.txt;md5=3cd6f8a7c3bd9d2bb898fcb27c75221a"

inherit cmake python3native

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ispc/ispc.git;protocol=https;branch=main \
           file://0001-CMakeLists.txt-link-with-libclang-cpp-library-instea.patch \
           file://0002-cmake-don-t-build-for-32-bit-targets.patch \
           file://8b5d0f26916e776bc3664e6a4dc68eff3a198d7a.patch \
           "
SRCREV = "bdd411085d3e398cf7927cb3b94b00af676737ba"

COMPATIBLE_HOST = '(x86_64).*-linux'

DEPENDS += " clang-native bison-native "
RDEPENDS_${PN} += " clang-libllvm clang"

EXTRA_OECMAKE += " \
                  -DISPC_INCLUDE_TESTS=OFF  \
                  -DISPC_INCLUDE_EXAMPLES=OFF  \
                  -DISPC_NO_DUMPS=ON  \
                  -DARM_ENABLED=OFF  \
                  -DISPC_CROSS=ON  \
                  -DSYSROOT_DIR=${STAGING_DIR_NATIVE}  \
                  "

TOOLCHAIN = "clang"
BBCLASSEXTEND = "native nativesdk"
