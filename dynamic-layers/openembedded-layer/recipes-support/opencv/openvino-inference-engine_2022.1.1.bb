SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=https;branch=releases/2022/1.1;lfs=0 \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/usb-ma2x8x/firmware_usb-ma2x8x_1875.zip;name=usb_ma2x8x \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/pcie-ma2x8x/firmware_pcie-ma2x8x_1875.zip;name=pcie_ma2x8x \
           git://github.com/openvinotoolkit/oneDNN.git;protocol=https;destsuffix=git/src/plugins/intel_cpu/thirdparty/mkl-dnn;name=mkl;nobranch=1 \
           git://github.com/oneapi-src/oneDNN.git;protocol=https;destsuffix=git/src/plugins/intel_gpu/thirdparty/onednn_gpu;name=onednn;branch=main \
           git://github.com/herumi/xbyak.git;protocol=https;destsuffix=git/thirdparty/xbyak;name=xbyak;branch=master \
           git://github.com/pybind/pybind11.git;protocol=https;destsuffix=git/src/bindings/python/thirdparty/pybind11;name=pybind11;branch=master \
           git://github.com/protocolbuffers/protobuf.git;protocol=https;destsuffix=git/thirdparty/protobuf/protobuf;name=protobuf;branch=3.18.x \
           git://github.com/nlohmann/json.git;protocol=https;destsuffix=git/thirdparty/json/nlohmann_json;name=json;branch=master \
           git://github.com/pboettch/json-schema-validator.git;protocol=https;destsuffix=git/thirdparty/json/nlohmann_json_schema_validator;name=jsonschema;branch=main \
           git://github.com/openvinotoolkit/open_model_zoo.git;protocol=https;destsuffix=git/thirdparty/open_model_zoo;name=omz;branch=releases/2022/1 \
           file://0001-inference-engine-use-system-installed-packages.patch \
           file://0002-inference-engine-installation-fixes.patch \
           "

SRCREV = "39aba80957e10b66a6c8f3f590c2d90e8238ca75"
SRCREV_mkl = "82ca2f931c1d588b67d154d873136d4af1ffb3a8"
SRCREV_onednn = "9e2bf22e51726ad36ddae90c7caf2898d124baa6"
SRCREV_xbyak = "8d1e41b650890080fb77548372b6236bbd4079f9"
SRCREV_pybind11 = "d71ba0cb73616c493d35699a8a9283aa64ef0f6b"
SRCREV_protobuf = "6c6b0778b70f35f93c2f0dee30e5d12ad2a83eea"
SRCREV_json = "fec56a1a16c6e1c1b1f4e116a20e79398282626c"
SRCREV_jsonschema = "b1ef8628326cf0b53612f12784fd245e5e4382f1"
SRCREV_omz = "0c94071faef095f544a228f8455718fbd653950e"

SRC_URI[usb_ma2x8x.sha256sum] = "e65fcc1c6b0f3e9d814e53022c212ec0a2b83197a9df38badb298fb85ccf3acf"
SRC_URI[pcie_ma2x8x.sha256sum] = "b11368fec2036d96fb703d2a40b171184fefe89f27e74a988ef1ca34260a2bc5"

LICENSE = "Apache-2.0 & MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://thirdparty/xbyak/COPYRIGHT;md5=03532861dad9003cc2c17f14fc7a4efa \
                    file://thirdparty/cnpy/LICENSE;md5=689f10b06d1ca2d4b1057e67b16cd580 \
                    file://thirdparty/protobuf/protobuf/LICENSE;md5=37b5762e07f0af8c74ce80a8bda4266b \
                    file://thirdparty/json/nlohmann_json/LICENSE.MIT;md5=441793d25a658d58d79a1f87516a6ad1 \
                    file://thirdparty/json/nlohmann_json_schema_validator/LICENSE;md5=c441d022da1b1663c70181a32225d006 \
                    file://thirdparty/open_model_zoo/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://src/plugins/intel_cpu/thirdparty/mkl-dnn/LICENSE;md5=b48e3de3bfd47c27882a0d85b20823f5 \
                    file://src/plugins/intel_gpu/thirdparty/onednn_gpu/LICENSE;md5=b48e3de3bfd47c27882a0d85b20823f5 \
                    file://src/bindings/python/thirdparty/pybind11/LICENSE;md5=774f65abd8a7fe3124be2cdf766cd06f \
"

inherit cmake python3native

S = "${WORKDIR}/git"
EXTRA_OECMAKE += " \
                  -DENABLE_OPENCV=OFF \
                  -DOpenCV_DIR=${STAGING_LIBDIR}/cmake \
                  -DENABLE_PLUGIN_RPATH=0 \
                  -DENABLE_INTEL_GNA=OFF \
                  -DENABLE_SYSTEM_TBB=ON \
                  -DPYTHON_EXECUTABLE=${PYTHON} \
                  -DCMAKE_BUILD_TYPE=RelWithDebInfo \
                  -DTHREADING=TBB -DTBB_DIR="${STAGING_LIBDIR}/cmake/TBB" \
                  -DENABLE_SAMPLES=ON \
                  -DTREAT_WARNING_AS_ERROR=FALSE \
                  -DENABLE_DATA=FALSE \
                  -DENABLE_SYSTEM_PUGIXML=TRUE \
                  -DENABLE_SYSTEM_PROTOBUF=TRUE \
                  -DProtobuf_LIBRARIES=protobuf \
                  -DProtobuf_LITE_LIBRARIES=protobuf-lite \
                  -DProtobuf_INCLUDE_DIR=${STAGING_INCDIR} \
                  -DSYSTEM_PROTOC=${STAGING_BINDIR_NATIVE}/protoc \
                  -DENABLE_OV_ONNX_FRONTEND=FALSE \
                  -DUSE_BUILD_TYPE_SUBFOLDER=OFF \
                  "

DEPENDS += "libusb1 \
            ade \
            opencv \
            pugixml \
            protobuf \
            protobuf-native \
            tbb \
            zlib \
            "

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

PACKAGECONFIG ?= "vpu opencl"
PACKAGECONFIG[opencl] = "-DENABLE_INTEL_GPU=TRUE -DOpenCL_INCLUDE_DIR=${STAGING_INCDIR} -DOpenCL_LIBRARY=${STAGING_LIBDIR}/libOpenCL.so, -DENABLE_INTEL_GPU=FALSE, ocl-icd opencl-headers opencl-clhpp libva,"
PACKAGECONFIG[python3] = "-DENABLE_PYTHON=ON -DPYTHON_LIBRARY=${PYTHON_LIBRARY} -DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR}, -DENABLE_PYTHON=OFF, python3-cython-native patchelf-native, python3 python3-numpy python3-opencv python3-progress python3-cython"
PACKAGECONFIG[vpu] = "-DENABLE_INTEL_MYRIAD=ON -DVPU_FIRMWARE_USB-MA2X8X_FILE=../mvnc/usb-ma2x8x.mvcmd -DVPU_FIRMWARE_PCIE-MA2X8X_FILE=../mvnc/pcie-ma2x8x.mvcmd,-DENABLE_INTEL_MYRIAD=OFF,,${PN}-vpu-firmware"
PACKAGECONFIG[verbose] = "-DVERBOSE_BUILD=1,-DVERBOSE_BUILD=0"

do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'true', 'false', d)}; then
        install -d ${D}${datadir}/openvino
        mv ${D}/usr/samples/python ${D}${datadir}/openvino/

        install -d ${D}${PYTHON_SITEPACKAGES_DIR}
        mv ${D}${prefix}/python/${PYTHON_DIR}/openvino ${D}${PYTHON_SITEPACKAGES_DIR}/
        mv ${D}${prefix}/python/${PYTHON_DIR}/ngraph ${D}${PYTHON_SITEPACKAGES_DIR}/

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

FILES:${PN}-samples = "${datadir}/openvino \
                       ${bindir} \
                       "
FILES:${PN}-vpu-firmware += "${libdir}/*.mvcmd"

# Package for inference engine python API
PACKAGES =+ "${PN}-${PYTHON_PN}"

FILES:${PN}-${PYTHON_PN} = "${PYTHON_SITEPACKAGES_DIR}"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"
