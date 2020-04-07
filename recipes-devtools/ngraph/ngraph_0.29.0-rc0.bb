SUMMARY = "nGraph Compiler Stack"
DESCRIPTION = "nGraph Compiler aims to accelerate developing AI workloads using \
any deep learning framework and deploying to a variety of \
hardware targets."
HOMEPAGE = "https://www.ngraph.ai"

SRC_URI = "git://github.com/NervanaSystems/ngraph.git;protocol=https \
           file://0001-install-cmake-modules-correctly.patch \
           "

SRCREV = "b8419c354e5fc70805f1501d7dfff533ac790bec"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

S = "${WORKDIR}/git"

inherit cmake

OECMAKE_GENERATOR = "Unix Makefiles"

EXTRA_OECMAKE += " \
                   -DNGRAPH_CPU_ENABLE=FALSE \
                   -DNGRAPH_UNIT_TEST_ENABLE=FALSE \
                   -DNGRAPH_TEST_UTIL_ENABLE=FALSE \
                   -DNGRAPH_INTERPRETER_ENABLE=FALSE \
                   -DNGRAPH_JSON_ENABLE=FALSE \
                   -DNGRAPH_NATIVE_ARCH_ENABLE=FALSE \
                   -DNGRAPH_TOOLS_ENABLE=FALSE \
                   -DNGRAPH_NOP_ENABLE=FALSE \
                   -DNGRAPH_GENERIC_CPU_ENABLE=FALSE \
                   -DNGRAPH_LIB_VERSIONING_ENABLE=TRUE \
                   -DNGRAPH_CURRENT_RELEASE_TAG=0.29.0-rc0 \
                   "
