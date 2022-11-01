SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=https;lfs=0 \
           https://download.01.org/opencv/2020/openvinotoolkit/2020.1/inference_engine/firmware_usb-ma2450_942_R10.15.zip;name=ma2450 \
           https://download.01.org/opencv/2020/openvinotoolkit/2020.1/inference_engine/firmware_pcie-ma248x_942_R10.15.zip;name=ma248x \
           https://download.01.org/opencv/2020/openvinotoolkit/2020.1/inference_engine/firmware_usb-ma2x8x_942_R10.15.zip;name=ma2x8x \
           file://0001-Installation-and-build-fixes.patch \
           file://run-ptest \
           "

SRCREV = "b2140c083a068a63591e8c2e9b5f6b240790519d"

SRC_URI[ma2450.sha256sum] = "32747515f0a387a8f878a88670aefe2788132fa24828b3775df791144627d9f6"
SRC_URI[ma248x.sha256sum] = "f32cd6396d0e6f0e4b24c8ee15e9f0b1b493ebbfc0f03371ca732f75b763d4a2"
SRC_URI[ma2x8x.sha256sum] = "c00f77692bfdccf92f32233b3dd1189f51dee73c15d79f35a612c7ba841d9c8d"

LICENSE = "Apache-2.0 & ISSL & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://inference-engine/thirdparty/mkl-dnn/LICENSE;md5=afa44a3d001cc203032135324f9636b7 \
                    file://inference-engine/tests/libs/gtest/googlemock/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
                    file://inference-engine/thirdparty/mkl-dnn/src/cpu/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
                    file://inference-engine/thirdparty/clDNN/common/khronos_ocl_clhpp/LICENSE.txt;md5=88b295a48d2b3244ba65d3c055472c8a \
"
LICENSE_${PN}-vpu-firmware = "ISSL"

inherit cmake ptest python3native

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
                  -DENABLE_OPENCV=0 \
                  -DENABLE_PLUGIN_RPATH=0 \
                  -DENABLE_GNA=0 \
                  -DPYTHON_EXECUTABLE=${PYTHON} \
                  -DCMAKE_BUILD_TYPE=DebugWithRelInfo \
                  -DTHREADING=TBB -DTBB_DIR=${STAGING_LIBDIR} \
                  -DENABLE_TESTS="${@bb.utils.contains('PTEST_ENABLED', '1', '1', '0', d)}" \
                  -DBUILD_GMOCK=1 \
                  -DBUILD_GTEST=0 \
                  -DINSTALL_GMOCK=0 \
                  -DINSTALL_GTEST=0 \
                  -DENABLE_SAMPLES=1 \
                  -DENABLE_NGRAPH=ON \
                  -DENABLE_MKL_DNN=ON \
                  -DIE_CPACK_IE_DIR=${prefix} \
                  "

DEPENDS += "libusb1 \
            ade \
            opencv \
            pugixml \
            ngraph \
            tbb \
            ${@bb.utils.contains('PTEST_ENABLED', '1', 'gflags', '', d)} \
            "

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

PACKAGECONFIG ?= "vpu"
PACKAGECONFIG[opencl] = "-DENABLE_CLDNN=1 -DCLDNN__IOCL_ICD_INCDIRS=${STAGING_INCDIR} -DCLDNN__IOCL_ICD_STLDIRS=${STAGING_LIBDIR} -DCLDNN__IOCL_ICD_SHLDIRS=${STAGING_LIBDIR}, -DENABLE_CLDNN=0, opencl-icd-loader libva, intel-compute-runtime"
PACKAGECONFIG[python3] = "-DENABLE_PYTHON=ON -DPYTHON_LIBRARY=${PYTHON_LIBRARY} -DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR}, -DENABLE_PYTHON=OFF, python3-cython-native, python3 python3-numpy python3-opencv python3-progress python3-cython"
PACKAGECONFIG[vpu] = "-DENABLE_VPU=ON -DVPU_FIRMWARE_USB-MA2450_FILE=../mvnc/usb-ma2450.mvcmd -DVPU_FIRMWARE_USB-MA2X8X_FILE=../mvnc/usb-ma2x8x.mvcmd -DVPU_FIRMWARE_PCIE-MA248X_FILE=../mvnc/pcie-ma248x.mvcmd,-DENABLE_VPU=OFF,,${PN}-vpu-firmware"
PACKAGECONFIG[verbose] = "-DVERBOSE_BUILD=1,-DVERBOSE_BUILD=0"

do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'vpu', 'true', 'false', d)}; then
        install -m0644 ${WORKDIR}/mvnc/usb-ma2450.mvcmd ${D}${libdir}/
        install -m0644 ${WORKDIR}/mvnc/usb-ma2x8x.mvcmd ${D}${libdir}/
        install -m0644 ${WORKDIR}/mvnc/pcie-ma248x.mvcmd ${D}${libdir}/
    fi

    if ${@bb.utils.contains('PACKAGECONFIG', 'opencl', 'true', 'false', d)}; then
        cp -r ${S}/inference-engine/src/cldnn_engine/cldnn_global_custom_kernels ${D}${libdir}/
    fi

    if ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'true', 'false', d)}; then
        install -d ${D}${datadir}/inference_engine
        mv ${D}/usr/samples/python ${D}${datadir}/inference_engine/

        install -d ${D}${PYTHON_SITEPACKAGES_DIR}
        mv ${D}${prefix}/python/${PYTHON_DIR}/openvino ${D}${PYTHON_SITEPACKAGES_DIR}/

        rm -rf ${D}${prefix}/python
    fi

    # Remove the samples source directory. We install the built samples.
    rm -rf ${D}/usr/samples
}

do_install_ptest_base_prepend() {
        # While not a Makefile based project that strictly falls into the category of
        # what ptest helps with, adding the unit tests here as ptest would help.
        # Create a dummy Makefile so installation doesn't fail.
        touch ${WORKDIR}/Makefile
        mv ${D}${bindir}/InferenceEngineUnitTests ${D}${PTEST_PATH}/
	mv ${D}${libdir}/libmock_engine.so ${D}${PTEST_PATH}/
}

# Otherwise e.g. ros-openvino-toolkit-dynamic-vino-sample when using dldt-inference-engine uses dldt-inference-engine WORKDIR
# instead of RSS
SSTATE_SCAN_FILES_append = " *.cmake"

FILES_${PN}-dev = "${includedir} \
                   ${libdir}/cmake \
                   "

FILES_${PN} += "${libdir}/lib*${SOLIBSDEV} \
                ${datadir}/openvino \
                ${libdir}/cldnn_global_custom_kernels \
                ${libdir}/custom_kernels \
                ${libdir}/plugins.xml \
                ${libdir}/cache.json \
                "

# Move inference engine samples into a separate package
PACKAGES =+ "${PN}-samples ${PN}-vpu-firmware"

FILES_${PN}-samples = "${datadir}/inference_engine \
                       ${bindir} \
                       "
FILES_${PN}-vpu-firmware += "${libdir}/*.mvcmd"

# Package for inference engine python API
PACKAGES =+ "${PN}-${PYTHON_PN}"

FILES_${PN}-${PYTHON_PN} = "${PYTHON_SITEPACKAGES_DIR}/openvino"
