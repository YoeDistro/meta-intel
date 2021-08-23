#
# Common configuration for all MACHINEs of the intel-core2 type
#

DEFAULTTUNE ?= "core2-32"
require conf/machine/${X86_TUNE_DIR}/tune-core2.inc
require conf/machine/${X86_TUNE_DIR}/x86-base.inc
