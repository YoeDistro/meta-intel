SUMMARY = "Utility for managing Intel Optane DC persistent memory modules"
DESCRIPTION = "Utility for configuring and managing Intel Optane Persistent \
Memory modules (PMem). It supports functionality to: \
Discover DCPMMs on the platform. \
Provision the platform memory configuration. \
View and update the firmware on DCPMMs. \
Configure data-at-rest security on DCPMMs. \
Track health and performance of DCPMMs. \
Debug and troubleshoot DCPMMs."

HOMEPAGE = "https://github.com/intel/ipmctl"
BUGTRACKER = "https://github.com/intel/ipmctl/issues"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3eb8e660b3e1c7257002b6fe4141d4f \
                    file://thirdpartynotice.txt;md5=cba483cb16e3fc688a1e366c75800e91"

SRC_URI = "git://github.com/intel/ipmctl.git;branch=master_2_0;protocol=https"

SRCREV = "001596fa2c6a3b16dae0a74c004443edd0d01095"
S = "${WORKDIR}/git"

inherit cmake

DEPENDS = "ndctl"

EXTRA_OECMAKE = "-DRELEASE=ON"
