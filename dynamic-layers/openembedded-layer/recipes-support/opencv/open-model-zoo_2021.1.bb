SUMMARY = "OpenVINO(TM) Toolkit - Open Model Zoo repository"
HOMEPAGE = "https://github.com/opencv/open_model_zoo"
DESCRIPTION = "This repository includes optimized deep learning \
models and a set of demos to expedite development of high-performance \
deep learning inference applications."

SRC_URI = "git://github.com/opencv/open_model_zoo.git;protocol=git;branch=master \
           "

SRCREV = "75ec8ad4b6c4fd7fb161ae9d2c3056281b2443de"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
"

inherit cmake

S = "${WORKDIR}/git/demos"

DEPENDS += "openvino-inference-engine opencv gflags"

RDEPENDS_${PN} += " \
                   python3-decorator \
                   python3-defusedxml \
                   python3-networkx \
                   python3-protobuf \
                   python3-test-generator \
                   python3-requests \
                   python3-pyyaml \
"

COMPATIBLE_HOST = '(x86_64).*-linux'

EXTRA_OECMAKE += " \
                 -DIE_MAIN_SOURCE_DIR=${B} \
                 -DENABLE_SAMPLES=ON \
                 -DIE_INCLUDE_DIR=${STAGING_EXECPREFIXDIR} \
                 -DIE_RELEASE_LIBRARY=${STAGING_LIBDIR}/libinference_engine.so \
                 -DIE_C_API_RELEASE_LIBRARY=${STAGING_LIBDIR}/libinference_engine_c_api.so \
                 -DIE_LEGACY_RELEASE_LIBRARY=${STAGING_LIBDIR}/libinference_engine_legacy.so \
                 -DIE_NN_BUILDER_RELEASE_LIBRARY=${STAGING_LIBDIR}/libinference_engine_nn_builder.so \
                 -DIE_ROOT_DIR=${WORKDIR}/InferenceEngine \
"

do_configure_prepend(){
	mkdir -p ${WORKDIR}/InferenceEngine/share
	cp ${STAGING_LIBDIR}/cmake/InferenceEngine/* ${WORKDIR}/InferenceEngine/share/
}

do_install(){
	install -d ${D}${libdir}
	install -d ${D}${bindir}
	install -d ${D}${datadir}/openvino/open-model-zoo/tools
	install -d ${D}${datadir}/openvino/open-model-zoo/demos/python_demos
	cp -rf ${WORKDIR}/build/intel64/Release/lib/*.a ${D}${libdir}
	cp -rf ${WORKDIR}/build/intel64/Release/*_demo* ${D}${bindir}
	cp -rf ${WORKDIR}/git/models ${D}${datadir}/openvino/open-model-zoo
	cp -rf ${WORKDIR}/git/tools/downloader ${D}${datadir}/openvino/open-model-zoo/tools
	cp -rf ${WORKDIR}/git/demos/python_demos ${D}${datadir}/openvino/open-model-zoo/demos
}

FILES_${PN} += "${datadir}/openvino"
