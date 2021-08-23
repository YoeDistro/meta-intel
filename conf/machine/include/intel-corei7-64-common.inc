#
# Common configuration for all MACHINES of the intel-corei7-64 type
#

DEFAULTTUNE ?= "corei7-64"
require conf/machine/${X86_TUNE_DIR}/tune-corei7.inc
require conf/machine/${X86_TUNE_DIR}/x86-base.inc
