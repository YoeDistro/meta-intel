SUMMARY  = "oneAPI DPC++ Library (oneDPL)"
DESCRIPTION = "The oneAPI DPC++ Library (oneDPL) aims to work with the \
oneAPI DPC++ Compiler to provide high-productivity APIs to developers, \
which can minimize DPC++ programming efforts across devices for high \
performance parallel applications."
HOMEPAGE = "https://github.com/oneapi-src/oneDPL"

LICENSE  = "Apache-2.0-with-LLVM-exception"
LIC_FILES_CHKSUM = "file://licensing/LICENSE.txt;md5=2e982d844baa4df1c80de75470e0c5cb \
                    file://licensing/third-party-programs.txt;md5=74cbe473c3521af32a92f662c44aa6bd"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/oneapi-src/oneDPL.git;protocol=https;branch=release/2021.7 \
            "
SRCREV = "df96969b76f075bd9ce14c303659595c747c7223"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
     install -d -m 755 ${D}${includedir}/onedpl
     cp -r ${S}/include/* ${D}${includedir}/onedpl
}

UPSTREAM_CHECK_GITTAGREGEX = "^oneDPL-(?P<pver>(\d+(\.\d+)+))-release$"
