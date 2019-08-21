# Settings for the GCC(1) cpu-type "skylake":
#
#     Intel Skylake CPU with 64-bit extensions, MMX, SSE, SSE2, SSE3, SSSE3, SSE4.1,
#     SSE4.2, AVX, AVX2 and POPCNT instruction set support.
#
# This tune is recommended for Intel Skylake CPU (and beyond).
#
DEFAULTTUNE ?= "skylake-64"

# Include the previous tune to pull in PACKAGE_EXTRA_ARCHS
require conf/machine/include/tune-core2.inc

# Extra tune features
TUNEVALID[skylake] = "Enable skylake specific processor optimizations"
TUNE_CCARGS .= "${@bb.utils.contains('TUNE_FEATURES', 'skylake', ' -march=skylake -mtune=generic -mfpmath=sse -mavx2', '', d)}"

# Extra tune selections

AVAILTUNES += "skylake-64"
TUNE_FEATURES_tune-skylake-64 = "${TUNE_FEATURES_tune-x86-64} skylake"
BASE_LIB_tune-skylake-64 = "lib64"
TUNE_PKGARCH_tune-skylake-64 = "skylake-64"
PACKAGE_EXTRA_ARCHS_tune-skylake-64 = "${PACKAGE_EXTRA_ARCHS_tune-core2-64} skylake-64"
QEMU_EXTRAOPTIONS_skylake-64 = " -cpu Skylake-Client"

# Disable QEMU user
MACHINE_FEATURES_remove = "qemu-usermode"

