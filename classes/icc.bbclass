TOOLCHAINOVERRIDES = ":toolchain-${TOOLCHAIN}"
TOOLCHAINOVERRIDES[vardepsexclude] = "TOOLCHAIN"

OVERRIDES .= "${TOOLCHAINOVERRIDES}"
OVERRIDES[vardepsexclude] += "TOOLCHAINOVERRIDES"

ICC_PREFIX_OPTION = ""
ICCQ_PREFIX_OPTION = ""

python(){
    hostprefix = d.getVar('HOST_PREFIX', True)
    if hostprefix and hostprefix != "":
        d.setVar("ICC_PREFIX_OPTION", "-gnu-prefix=${HOST_PREFIX}")
        d.setVar("ICCQ_PREFIX_OPTION","-qgnu-prefix=${HOST_PREFIX}")
    else:
        d.setVar("ICC_PREFIX_OPTION", "")
        d.setVar("ICCQ_PREFIX_OPTION","")

}

ICC_GCC_OPTION = "-gcc-name=${STAGING_BINDIR_TOOLCHAIN}/${TARGET_PREFIX}gcc"
ICC_GXX_OPTION = "-gxx-name=${STAGING_BINDIR_TOOLCHAIN}/${TARGET_PREFIX}g++"
CC:toolchain-icc  = "icc ${ICC_PREFIX_OPTION}  ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} ${ICC_GCC_OPTION}"
CXX:toolchain-icc = "icpc ${ICC_PREFIX_OPTION} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} ${ICC_GXX_OPTION}"
CPP:toolchain-icc = "icc ${ICC_PREFIX_OPTION} -E${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH} ${ICC_GXX_OPTION}"
LD:toolchain-icc  = "xild ${TOOLCHAIN_OPTIONS} ${HOST_LD_ARCH}"
CCLD:toolchain-icc  = "icc ${ICC_PREFIX_OPTION}  ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} ${ICC_GCC_OPTION}"
AR:toolchain-icc = "xiar"

DEBUG_FLAGS=" -g -feliminate-unused-debug-types"
TARGET_LDFLAGS = "-Wl,-O1 ${TARGET_LINK_HASH_STYLE} ${ASNEEDED}"
CFLAGS:append:toolchain-icc = " ${ICC_PREFIX_OPTION}"
CXXFLAGS:append:toolchain-icc = " ${ICC_PREFIX_OPTION}"

OECMAKE_AR:toolchain-icc = "${AR}"

DEPENDS:append:toolchain-icc:class-target = " intel-oneapi-runtime-compilers"
DEPENDS:append:toolchain-icc:class-target = " intel-oneapi-compiler-classic-native"
TOOLCHAIN:class-native = "gcc"
TOOLCHAIN:class-nativesdk = "gcc"
TOOLCHAIN:class-cross-canadian = "gcc"
TOOLCHAIN:class-crosssdk = "gcc"
TOOLCHAIN:class-cross = "gcc"
