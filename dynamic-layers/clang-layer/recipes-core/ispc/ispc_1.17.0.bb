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
           file://0001-Enable-LLVM-15.0-support.patch \
           file://0001-CMakeLists.txt-allow-to-pick-llvm-config-from-usr-bi.patch \
           file://0001-Produce-position-independent-code-for-testing.patch \
           file://0001-Fix-build-with-GCC-13-and-possibly-Clang-15.patch \
           "
SRCREV = "7ad8429369a4d5ced6b524fdfffe623939d8fe9a"

COMPATIBLE_HOST = '(x86_64).*-linux'

DEPENDS += " clang-native bison-native "
DEPENDS:append:class-target = " clang"

EXTRA_OECMAKE += " \
                  -DISPC_INCLUDE_TESTS=OFF  \
                  -DISPC_INCLUDE_EXAMPLES=OFF  \
                  -DISPC_NO_DUMPS=ON  \
                  -DARM_ENABLED=OFF  \
                  -DISPC_CROSS=ON  \
                  -DISPC_ANDROID_TARGET=OFF  \
                  -DISPC_FREEBSD_TARGET=OFF  \
                  -DISPC_WINDOWS_TARGET=OFF  \
                  -DISPC_IOS_TARGET=OFF  \
                  -DISPC_PS4_TARGET=OFF  \
                  -DSYSROOT_DIR=${STAGING_DIR} \
                  -DCLANG_EXECUTABLE=${STAGING_BINDIR_NATIVE}/clang \
                  -DCLANGPP_EXECUTABLE=${STAGING_BINDIR_NATIVE}/clang++ \
                  -DLLVM_DIS_EXECUTABLE=${STAGING_BINDIR_NATIVE}/llvm-dis \
                  -DLLVM_AS_EXECUTABLE=${STAGING_BINDIR_NATIVE}/llvm-as \
                  "

TOOLCHAIN = "clang"
BBCLASSEXTEND = "native nativesdk"
