SUMMARY = "Update Intel CPU microcode"

DESCRIPTION = "iucode_tool is a program to manipulate Intel i686 and X86-64\
 processor microcode update collections, and to use the kernel facilities to\
 update the microcode on Intel system processors.  It can load microcode data\
 files in text and binary format, sort, list and filter the microcode updates\
 contained in these files, write selected microcode updates to a new file in\
 binary format, or upload them to the kernel. \
 It operates on microcode data downloaded directly from Intel:\
 http://feeds.downloadcenter.intel.com/rss/?p=2371\
"
HOMEPAGE = "https://gitlab.com/iucode-tool/"
BUGTRACKER = "https://bugs.debian.org/cgi-bin/pkgreport.cgi?ordering=normal;archive=0;src=iucode-tool;repeatmerged=0"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://iucode_tool.c;beginline=1;endline=15;md5=71eeab3190360ff0267101b570874756"

DEPENDS:append:libc-musl = " argp-standalone"

SRC_URI = "https://gitlab.com/iucode-tool/releases/raw/master/iucode-tool_${PV}.tar.xz"
SRC_URI:append:libc-musl = " file://0001-Makefile.am-Add-arg-parse-library-for-MUSL-support.patch"

SRC_URI[md5sum] = "63b33cc0ea1f8c73b443412abbf39d6f"
SRC_URI[sha256sum] = "12b88efa4d0d95af08db05a50b3dcb217c0eb2bfc67b483779e33d498ddb2f95"

inherit autotools

BBCLASSEXTEND = "native"

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"

UPSTREAM_CHECK_URI = "https://gitlab.com/iucode-tool/iucode-tool/-/tags"
UPSTREAM_CHECK_REGEX = "v(?P<pver>\d+(\.\d+)+)"
