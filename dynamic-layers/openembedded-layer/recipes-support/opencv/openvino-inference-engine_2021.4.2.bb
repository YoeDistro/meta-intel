SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=https;branch=releases/2021/4;lfs=0 \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/usb-ma2x8x/firmware_usb-ma2x8x_1875.zip;name=usb_ma2x8x \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/pcie-ma2x8x/firmware_pcie-ma2x8x_1875.zip;name=pcie_ma2x8x \
           git://github.com/openvinotoolkit/oneDNN.git;protocol=https;destsuffix=git/inference-engine/thirdparty/mkl-dnn;name=mkl;nobranch=1 \
           git://github.com/herumi/xbyak.git;protocol=https;destsuffix=git/thirdparty/xbyak;name=xbyak;branch=master \
           git://github.com/pybind/pybind11.git;protocol=https;destsuffix=git/ngraph/python/pybind11;name=pybind11;branch=master \
           file://0001-inference-engine-use-system-installed-packages.patch \
           file://0002-Disable-Werror.patch \
           file://0003-inference-engine-installation-fixes.patch \
           file://0001-dont-install-licenses-and-version-file.patch \
           "

SRCREV = "6c4462759e8974c0ce5b36ad22972a1c6ded76a8"
SRCREV_mkl = "60f41b3a9988ce7b1bc85c4f1ce7f9443bc91c9d"
SRCREV_xbyak = "8d1e41b650890080fb77548372b6236bbd4079f9"
SRCREV_pybind11 = "3b1dbebabc801c9cf6f0953a4c20b904d444f879"

SRC_URI[usb_ma2x8x.sha256sum] = "e65fcc1c6b0f3e9d814e53022c212ec0a2b83197a9df38badb298fb85ccf3acf"
SRC_URI[pcie_ma2x8x.sha256sum] = "b11368fec2036d96fb703d2a40b171184fefe89f27e74a988ef1ca34260a2bc5"

LICENSE = "Apache-2.0 & ISSL & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://inference-engine/thirdparty/mkl-dnn/LICENSE;md5=c441291ac5f15bdc6b09b4cc02ece35b \
                    file://thirdparty/xbyak/COPYRIGHT;md5=03532861dad9003cc2c17f14fc7a4efa \
                    file://inference-engine/thirdparty/clDNN/common/include/OpenCL_CLHPP_License.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
"
LICENSE:${PN}-vpu-firmware = "ISSL"

inherit cmake python3native

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
                  -DENABLE_OPENCV=0 \
                  -DENABLE_PLUGIN_RPATH=0 \
                  -DENABLE_GNA=0 \
                  -DPYTHON_EXECUTABLE=${PYTHON} \
                  -DCMAKE_BUILD_TYPE=RelWithDebInfo \
                  -DTHREADING=TBB -DTBB_DIR=${STAGING_LIBDIR}/cmake/TBB \
                  -DENABLE_SAMPLES=1 \
                  -DIE_CPACK_IE_DIR=${prefix} \
                  -DNGRAPH_UNIT_TEST_ENABLE=FALSE \
                  -DNGRAPH_TEST_UTIL_ENABLE=FALSE \
                  -DNGRAPH_ONNX_IMPORT_ENABLE=OFF \
                  -DNGRAPH_JSON_ENABLE=FALSE \
                  -DTREAT_WARNING_AS_ERROR=FALSE \
                  -DENABLE_SPEECH_DEMO=FALSE \
                  -DENABLE_DATA=FALSE \
                  -DUSE_SYSTEM_PUGIXML=TRUE \
                  "

DEPENDS += "libusb1 \
            ade \
            opencv \
            pugixml \
            protobuf-native \
            tbb \
            zlib \
            "

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

#Disable opencl temporarily. intel-compute-runtime depends on
#intel-graphics-compiler and vc-intrinsics and these two recipes fail to
#compile with LLVM13. Enable it after upstream has fixed it.
#PACKAGECONFIG ?= "vpu opencl"
PACKAGECONFIG ?= "vpu"
PACKAGECONFIG[opencl] = "-DENABLE_CLDNN=1 -DCLDNN__IOCL_ICD_INCDIRS=${STAGING_INCDIR} -DCLDNN__IOCL_ICD_STLDIRS=${STAGING_LIBDIR} -DCLDNN__IOCL_ICD_SHLDIRS=${STAGING_LIBDIR}, -DENABLE_CLDNN=0, ocl-icd opencl-headers libva, intel-compute-runtime"
PACKAGECONFIG[python3] = "-DENABLE_PYTHON=ON -DPYTHON_LIBRARY=${PYTHON_LIBRARY} -DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR}, -DENABLE_PYTHON=OFF, python3-cython-native, python3 python3-numpy python3-opencv python3-progress python3-cython"
PACKAGECONFIG[vpu] = "-DENABLE_VPU=ON -DVPU_FIRMWARE_USB-MA2X8X_FILE=../mvnc/usb-ma2x8x.mvcmd -DVPU_FIRMWARE_PCIE-MA2X8X_FILE=../mvnc/pcie-ma2x8x.mvcmd,-DENABLE_VPU=OFF,,${PN}-vpu-firmware"
PACKAGECONFIG[verbose] = "-DVERBOSE_BUILD=1,-DVERBOSE_BUILD=0"

do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'vpu', 'true', 'false', d)}; then
        install -m0644 ${WORKDIR}/mvnc/usb-ma2x8x.mvcmd ${D}${libdir}/
        install -m0644 ${WORKDIR}/mvnc/pcie-ma2x8x.mvcmd ${D}${libdir}/
    fi

    if ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'true', 'false', d)}; then
        install -d ${D}${datadir}/inference_engine
        mv ${D}/usr/samples/python ${D}${datadir}/inference_engine/

        install -d ${D}${PYTHON_SITEPACKAGES_DIR}
        mv ${D}${prefix}/python/${PYTHON_DIR}/openvino ${D}${PYTHON_SITEPACKAGES_DIR}/
        mv ${D}${prefix}/deployment_tools/tools/benchmark_tool ${D}${PYTHON_SITEPACKAGES_DIR}/openvino/
        mv ${D}${prefix}/deployment_tools/tools/cross_check_tool ${D}${PYTHON_SITEPACKAGES_DIR}/openvino/

        rm -rf ${D}${prefix}/python
    fi

    rm -rf ${D}${prefix}/deployment_tools

    # Remove the samples source directory. We install the built samples.
    rm -rf ${D}/usr/samples
}

# Otherwise e.g. ros-openvino-toolkit-dynamic-vino-sample when using dldt-inference-engine uses dldt-inference-engine WORKDIR
# instead of RSS
SSTATE_SCAN_FILES:append = " *.cmake"

FILES:${PN}-dev = "${includedir} \
                   ${libdir}/cmake \
                   "

FILES:${PN} += "${libdir}/lib*${SOLIBSDEV} \
                ${datadir}/openvino \
                ${libdir}/custom_kernels \
                ${libdir}/plugins.xml \
                ${libdir}/cache.json \
                "

# Move inference engine samples into a separate package
PACKAGES =+ "${PN}-samples ${PN}-vpu-firmware"

FILES:${PN}-samples = "${datadir}/inference_engine \
                       ${bindir} \
                       "
FILES:${PN}-vpu-firmware += "${libdir}/*.mvcmd"

# Package for inference engine python API
PACKAGES =+ "${PN}-${PYTHON_PN}"

FILES:${PN}-${PYTHON_PN} = "${PYTHON_SITEPACKAGES_DIR}/openvino"
