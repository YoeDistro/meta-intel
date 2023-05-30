SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=https;branch=releases/2022/3;lfs=0 \
           https://storage.openvinotoolkit.org/dependencies/myriad/firmware_usb-ma2x8x_20230121_38.zip;name=usb_ma2x8x \
           https://storage.openvinotoolkit.org/dependencies/myriad/firmware_pcie-ma2x8x_20230121_38.zip;name=pcie_ma2x8x \
           git://github.com/openvinotoolkit/oneDNN.git;protocol=https;destsuffix=git/src/plugins/intel_cpu/thirdparty/onednn;name=mkl;nobranch=1 \
           git://github.com/oneapi-src/oneDNN.git;protocol=https;destsuffix=git/src/plugins/intel_gpu/thirdparty/onednn_gpu;name=onednn;nobranch=1 \
           git://github.com/herumi/xbyak.git;protocol=https;destsuffix=git/thirdparty/xbyak;name=xbyak;branch=master \
           git://github.com/nlohmann/json.git;protocol=https;destsuffix=git/thirdparty/json/nlohmann_json;name=json;branch=develop \
           git://github.com/opencv/ade.git;protocol=https;destsuffix=git/thirdparty/ade;name=ade;nobranch=1 \
           file://fix-build.patch \
           file://cython-cmake.patch \
           file://7cecc9138b89e1946e3e515727bb69b2ab119806.patch;patchdir=thirdparty/ade \
           file://fix-build-with-gcc13.patch \
           file://onednn-fix-build-with-gcc13.patch;patchdir=src/plugins/intel_gpu/thirdparty/onednn_gpu \
           "

SRCREV = "0a5ca5375265f0f12cdaee68574030408dd1c352"
SRCREV_mkl = "44de3c3698b687c26e487fc8f0213fa487e8fe2c"
SRCREV_onednn = "fbec3e25a559ee252022ae066817b204e106a6ba"
SRCREV_xbyak = "f8ea5c28dfcdc98585881d0ca9e499580ca077ae"
SRCREV_json = "bc889afb4c5bf1c0d8ee29ef35eaaf4c8bef8a5d"
SRCREV_ade = "58b2595a1a95cc807be8bf6222f266a9a1f393a9"

SRC_URI[usb_ma2x8x.sha256sum] = "f7351b2e26f25d652a0539f6ace5797d7700735d52479c3e6ef354c236abbd3a"
SRC_URI[pcie_ma2x8x.sha256sum] = "439219aeac010f8b85f19838420e9a247f2cdf23a6d00e7727cf92d96dbdaeeb"

LICENSE = "Apache-2.0 & MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://thirdparty/xbyak/COPYRIGHT;md5=3c98edfaa50a86eeaef4c6109e803f16 \
                    file://thirdparty/cnpy/LICENSE;md5=689f10b06d1ca2d4b1057e67b16cd580 \
                    file://thirdparty/json/nlohmann_json/LICENSE.MIT;md5=f969127d7b7ed0a8a63c2bbeae002588 \
                    file://thirdparty/ade/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://src/plugins/intel_cpu/thirdparty/onednn/LICENSE;md5=b48e3de3bfd47c27882a0d85b20823f5 \
                    file://src/plugins/intel_gpu/thirdparty/onednn_gpu/LICENSE;md5=b48e3de3bfd47c27882a0d85b20823f5 \
"

inherit cmake python3native pkgconfig

S = "${WORKDIR}/git"
EXTRA_OECMAKE += " \
                  -DENABLE_OPENCV=OFF \
                  -DENABLE_INTEL_GNA=OFF \
                  -DENABLE_SYSTEM_TBB=ON \
                  -DPYTHON_EXECUTABLE=${PYTHON} \
                  -DCMAKE_BUILD_TYPE=RelWithDebInfo \
                  -DTHREADING=TBB -DTBB_DIR="${STAGING_LIBDIR}/cmake/TBB" \
                  -DTREAT_WARNING_AS_ERROR=FALSE \
                  -DENABLE_DATA=FALSE \
                  -DENABLE_SYSTEM_PUGIXML=TRUE \
                  -DENABLE_SYSTEM_PROTOBUF=TRUE \
                  -DENABLE_OV_ONNX_FRONTEND=FALSE \
                  -DUSE_BUILD_TYPE_SUBFOLDER=OFF \
                  -DENABLE_FUZZING=OFF \
                  -DENABLE_TBBBIND_2_5=OFF \
                  -DCPACK_GENERATOR=RPM \
                  "


DEPENDS += "\
            gflags \
            libusb1 \
            protobuf \
            protobuf-native \
            protobuf-c \
            pugixml \
            python3-pybind11 \
            tbb \
            zlib \
            "

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

PACKAGECONFIG ?= "vpu opencl samples"
PACKAGECONFIG[opencl] = "-DENABLE_INTEL_GPU=TRUE, -DENABLE_INTEL_GPU=FALSE, virtual/opencl-icd opencl-headers opencl-clhpp,"
PACKAGECONFIG[python3] = "-DENABLE_PYTHON=ON -DPYTHON_LIBRARY=${PYTHON_LIBRARY} -DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR}, -DENABLE_PYTHON=OFF, python3-cython-native patchelf-native, python3 python3-numpy python3-progress python3-cython"
PACKAGECONFIG[samples] = "-DENABLE_SAMPLES=ON -DENABLE_COMPILE_TOOL=ON, -DENABLE_SAMPLES=OFF -DENABLE_COMPILE_TOOL=OFF, opencv"
PACKAGECONFIG[vpu] = "-DENABLE_INTEL_MYRIAD=ON -DVPU_FIRMWARE_USB-MA2X8X_FILE=../usb-ma2x8x.mvcmd -DVPU_FIRMWARE_PCIE-MA2X8X_FILE=../pcie-ma2x8x.mvcmd,-DENABLE_INTEL_MYRIAD=OFF,,${PN}-vpu-firmware"
PACKAGECONFIG[verbose] = "-DVERBOSE_BUILD=1,-DVERBOSE_BUILD=0"

do_configure:prepend() {
    # Dont set PROJECT_ROOT_DIR
    sed -i -e 's:\${OpenVINO_SOURCE_DIR}::;' ${S}/src/CMakeLists.txt
}

do_install:append() {
    rm -rf ${D}${prefix}/install_dependencies
    rm -rf ${D}${prefix}/setupvars.sh

    sed -i -e 's:^#include.*imp.hpp"$:#include "/usr/src/debug/${PN}/${EXTENDPE}${PV}-${PR}/git/src/plugins/intel_cpu/src/nodes/proposal_imp.hpp":g;' ${B}/src/plugins/intel_cpu/cross-compiled/proposal_imp_disp.cpp
}

# Otherwise e.g. ros-openvino-toolkit-dynamic-vino-sample when using dldt-inference-engine uses dldt-inference-engine WORKDIR
# instead of RSS
SSTATE_SCAN_FILES:append = " *.cmake"

FILES:${PN} += "\
                ${libdir}/openvino-${PV}/lib*${SOLIBSDEV} \
                ${libdir}/openvino-${PV}/plugins.xml \
                ${libdir}/openvino-${PV}/cache.json \
                "

# Move inference engine samples into a separate package
PACKAGES =+ "${PN}-samples ${PN}-vpu-firmware"

FILES:${PN}-samples = "${datadir}/openvino \
                       ${bindir} \
                       ${libdir}/libformat_reader.so \
                       ${libdir}/libopencv_c_wrapper.so \
                       "
FILES:${PN}-vpu-firmware += "${libdir}/openvino-${PV}/*.mvcmd"

# Package for inference engine python API
PACKAGES =+ "${PN}-${PYTHON_PN}"

FILES:${PN}-${PYTHON_PN} = "${PYTHON_SITEPACKAGES_DIR}"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+\.\d+\.\d+))$"
