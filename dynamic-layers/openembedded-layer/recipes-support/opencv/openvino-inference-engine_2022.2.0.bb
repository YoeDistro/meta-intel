SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=https;branch=releases/2022/2;lfs=0 \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/usb-ma2x8x/firmware_usb-ma2x8x_20220307_34.zip;name=usb_ma2x8x \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/pcie-ma2x8x/firmware_pcie-ma2x8x_20220307_34.zip;name=pcie_ma2x8x \
           git://github.com/openvinotoolkit/oneDNN.git;protocol=https;destsuffix=git/src/plugins/intel_cpu/thirdparty/onednn;name=mkl;nobranch=1 \
           git://github.com/oneapi-src/oneDNN.git;protocol=https;destsuffix=git/src/plugins/intel_gpu/thirdparty/onednn_gpu;name=onednn;nobranch=1 \
           git://github.com/herumi/xbyak.git;protocol=https;destsuffix=git/thirdparty/xbyak;name=xbyak;branch=master \
           git://github.com/pybind/pybind11.git;protocol=https;destsuffix=git/src/bindings/python/thirdparty/pybind11;name=pybind11;branch=master \
           git://github.com/nlohmann/json.git;protocol=https;destsuffix=git/thirdparty/json/nlohmann_json;name=json;branch=master \
           git://github.com/pboettch/json-schema-validator.git;protocol=https;destsuffix=git/thirdparty/json/nlohmann_json_schema_validator;name=jsonschema;branch=main \
           git://github.com/openvinotoolkit/open_model_zoo.git;protocol=https;destsuffix=git/thirdparty/open_model_zoo;name=omz;branch=releases/2022/2 \
           file://0001-Use-system-installed-dependencies.patch \
           file://0002-Fix-installation-of-binaries-and-libraries.patch \
           file://0003-Fix-build-issues-due-to-gflag-and-zlib.patch \
           file://cython-cmake.patch \
           "

SRCREV = "af16ea1d79a494503a54cff67a2856094e447931"
SRCREV_mkl = "2a749c577f8a841a396d4bd46eaf311b7e7dc089"
SRCREV_onednn = "efbf9b5e8c12666314f3484ce279cee0a1a91a44"
SRCREV_xbyak = "8d1e41b650890080fb77548372b6236bbd4079f9"
SRCREV_pybind11 = "aa304c9c7d725ffb9d10af08a3b34cb372307020"
SRCREV_json = "fec56a1a16c6e1c1b1f4e116a20e79398282626c"
SRCREV_jsonschema = "b1ef8628326cf0b53612f12784fd245e5e4382f1"
SRCREV_omz = "1919ae9d42c19d8f3bafc2417256ab3a67b6db79"

SRC_URI[usb_ma2x8x.sha256sum] = "877c4e1616d14a94dd2764f4f32f1c1aa2180dcd64ad1823b31efdc3f56ad593"
SRC_URI[pcie_ma2x8x.sha256sum] = "aabff3d817431792ef9e17056448979c2cdbb484ad4b0af9e68cb874ee10eef5"

LICENSE = "Apache-2.0 & MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://thirdparty/xbyak/COPYRIGHT;md5=03532861dad9003cc2c17f14fc7a4efa \
                    file://thirdparty/cnpy/LICENSE;md5=689f10b06d1ca2d4b1057e67b16cd580 \
                    file://thirdparty/json/nlohmann_json/LICENSE.MIT;md5=441793d25a658d58d79a1f87516a6ad1 \
                    file://thirdparty/json/nlohmann_json_schema_validator/LICENSE;md5=c441d022da1b1663c70181a32225d006 \
                    file://thirdparty/open_model_zoo/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://src/plugins/intel_cpu/thirdparty/onednn/LICENSE;md5=b48e3de3bfd47c27882a0d85b20823f5 \
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

do_configure:prepend() {
    # Dont set PROJECT_ROOT_DIR
    sed -i -e 's:\${CMAKE_CURRENT_SOURCE_DIR}::;' ${S}/src/CMakeLists.txt
}

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

    sed -i -e 's:^#include.*imp.hpp"$:#include "/usr/src/debug/${PN}/${EXTENDPE}${PV}-${PR}/git/src/plugins/intel_cpu/src/nodes/proposal_imp.hpp":g;' ${B}/src/plugins/intel_cpu/cross-compiled/proposal_imp_disp.cpp
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
