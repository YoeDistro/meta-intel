SUMMARY  = "oneAPI DPC++ Library (oneDPL)"
DESCRIPTION = "The oneAPI DPC++ Library (oneDPL) aims to work with the \
oneAPI DPC++ Compiler to provide high-productivity APIs to developers, \
which can minimize DPC++ programming efforts across devices for high \
performance parallel applications."
HOMEPAGE = "https://github.com/oneapi-src/oneDPL"

LICENSE  = "Apache-2.0-with-LLVM-exception"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2e982d844baa4df1c80de75470e0c5cb \
                    file://third-party-programs.txt;md5=409cd5c825a23043b6bb347861d34b35"

SRC_URI = "git://github.com/uxlfoundation/oneDPL.git;protocol=https;branch=release/2022.8.0 \
            "
SRCREV = "89d8d8befd4da2cb52f3724f668d17d7e39d42cf"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
     install -d -m 755 ${D}${includedir}/onedpl
     cp -r ${S}/include/* ${D}${includedir}/onedpl
}

UPSTREAM_CHECK_GITTAGREGEX = "^oneDPL-(?P<pver>(\d+(\.\d+)+))-release$"
