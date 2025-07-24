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

LICENSE = "BSD-3-Clause | BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=72b9da60da6219d612ce30b746a0fe71  \
                    file://edk2/License.txt;md5=6123e5bf044a66db96c4ce88a36b2d08"

SRC_URI = "git://github.com/intel/ipmctl.git;protocol=https;branch=master_3_0;name=ipmctl; \
        git://github.com/tianocore/edk2.git;protocol=https;name=edk2;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/edk2;branch=master \
        file://0001-Ignore-STATIC_ASSERTs-and-NULL-define-for-os-and-ut-builds.patch;patchdir=edk2 \
        file://0001-CMakeLists-disable-Werror.patch \
"

SRCREV_ipmctl = "a71f2fb1c90dd07f9862b71c789881132193e8f9"
#tag edk2-stable202408
SRCREV_edk2 = "b158dad150bf02879668f72ce306445250838201"
SRCREV_FORMAT = "ipmctl_edk2"

inherit cmake dos2unix

DEPENDS = "ndctl pkgconfig-native"

EXTRA_OECMAKE = "-DRELEASE=ON \
                 -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
                "

do_configure:prepend() {
    for dir in BaseTools MdeModulePkg MdePkg ShellPkg ; do
        ln -sf edk2/${dir} ${S}
    done
}

do_install:append() {
    # Remove /var/log/ipmctl as anything created in /var/log will not be
    # available when tmpfs is mounted at /var/volatile/log.
    rm -rf ${D}${localstatedir}/log
} 
