SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/openvinotoolkit/openvino.git;protocol=git;branch=releases/2020/4;lfs=0 \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/usb-ma2450/firmware_usb-ma2450_1223.zip;name=ma2450 \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/pcie-ma248x/firmware_pcie-ma248x_1223.zip;name=ma248x \
           https://download.01.org/opencv/master/openvinotoolkit/thirdparty/unified/VPU/usb-ma2x8x/firmware_usb-ma2x8x_1223.zip;name=ma2x8x \
           git://github.com/openvinotoolkit/oneDNN.git;protocol=https;destsuffix=git/inference-engine/thirdparty/mkl-dnn;name=mkl;nobranch=1 \
           file://0001-inference-engine-use-system-installed-packages.patch \
           file://0002-cldNN-disable-Werror.patch \
           file://0003-inference-engine-installation-fixes.patch \
           file://0004-fix-compilation-errors.patch \
           file://0005-cldnn-fix-inclusion-of-headers.patch \
           file://0001-dont-install-licenses-and-version-file.patch;patchdir=ngraph \
           "

SRCREV = "023e7c2c3f8a8ac83564db09799d2049115d9cf6"
SRCREV_mkl = "2706f56ebab54415be48add2751072065f4b52ab"

SRC_URI[ma2450.sha256sum] = "4dc246bd12d7a21c1b10ac3e090b30043777c4ea862e1e4119536ba03c5878ef"
SRC_URI[ma248x.sha256sum] = "64dd77ecd2f7172421414a388a87be4e6271894a982a58b3829f9de1a1869abd"
SRC_URI[ma2x8x.sha256sum] = "d1d209221c1389a9a04e14ffeeaff1c3308f6ab105c7bd22f0e300df01cce4d8"

LICENSE = "Apache-2.0 & ISSL & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://inference-engine/thirdparty/mkl-dnn/LICENSE;md5=afa44a3d001cc203032135324f9636b7 \
                    file://inference-engine/thirdparty/mkl-dnn/src/cpu/xbyak/COPYRIGHT;md5=03532861dad9003cc2c17f14fc7a4efa \
                    file://inference-engine/thirdparty/clDNN/common/khronos_ocl_clhpp/LICENSE.txt;md5=88b295a48d2b3244ba65d3c055472c8a \
"
LICENSE_${PN}-vpu-firmware = "ISSL"

inherit cmake python3native

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
                  -DENABLE_OPENCV=0 \
                  -DENABLE_PLUGIN_RPATH=0 \
                  -DENABLE_GNA=0 \
                  -DPYTHON_EXECUTABLE=${PYTHON} \
                  -DCMAKE_BUILD_TYPE=RelWithDebInfo \
                  -DTHREADING=TBB -DTBB_DIR=${STAGING_LIBDIR} \
                  -DENABLE_SAMPLES=1 \
                  -DIE_CPACK_IE_DIR=${prefix} \
                  -DNGRAPH_UNIT_TEST_ENABLE=FALSE \
                  -DNGRAPH_TEST_UTIL_ENABLE=FALSE \
                  -DNGRAPH_ONNX_IMPORT_ENABLE=OFF \
                  -DNGRAPH_JSON_ENABLE=FALSE \
                  -DTREAT_WARNING_AS_ERROR=FALSE \
                  -DENABLE_SPEECH_DEMO=FALSE \
                  -DENABLE_DATA=FALSE \
                  "

DEPENDS += "libusb1 \
            ade \
            opencv \
            pugixml \
            protobuf-native \
            tbb \
            onednn \
            "

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

PACKAGECONFIG ?= "vpu"
PACKAGECONFIG[opencl] = "-DENABLE_CLDNN=1 -DCLDNN__IOCL_ICD_INCDIRS=${STAGING_INCDIR} -DCLDNN__IOCL_ICD_STLDIRS=${STAGING_LIBDIR} -DCLDNN__IOCL_ICD_SHLDIRS=${STAGING_LIBDIR}, -DENABLE_CLDNN=0, ocl-icd opencl-headers libva, intel-compute-runtime"
PACKAGECONFIG[python3] = "-DENABLE_PYTHON=ON -DPYTHON_LIBRARY=${PYTHON_LIBRARY} -DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR}, -DENABLE_PYTHON=OFF, python3-cython-native, python3 python3-numpy python3-opencv python3-progress python3-cython"
PACKAGECONFIG[vpu] = "-DENABLE_VPU=ON -DVPU_FIRMWARE_USB-MA2450_FILE=../mvnc/usb-ma2450.mvcmd -DVPU_FIRMWARE_USB-MA2X8X_FILE=../mvnc/usb-ma2x8x.mvcmd -DVPU_FIRMWARE_PCIE-MA248X_FILE=../mvnc/pcie-ma248x.mvcmd,-DENABLE_VPU=OFF,,${PN}-vpu-firmware"
PACKAGECONFIG[verbose] = "-DVERBOSE_BUILD=1,-DVERBOSE_BUILD=0"

do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'vpu', 'true', 'false', d)}; then
        install -m0644 ${WORKDIR}/mvnc/usb-ma2450.mvcmd ${D}${libdir}/
        install -m0644 ${WORKDIR}/mvnc/usb-ma2x8x.mvcmd ${D}${libdir}/
        install -m0644 ${WORKDIR}/mvnc/pcie-ma248x.mvcmd ${D}${libdir}/
    fi

    if ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'true', 'false', d)}; then
        install -d ${D}${datadir}/inference_engine
        mv ${D}/usr/samples/python ${D}${datadir}/inference_engine/

        install -d ${D}${PYTHON_SITEPACKAGES_DIR}
        mv ${D}${prefix}/python/${PYTHON_DIR}/openvino ${D}${PYTHON_SITEPACKAGES_DIR}/
        mv ${D}${prefix}/deployment_tools/tools/benchmark_tool ${D}${PYTHON_SITEPACKAGES_DIR}/openvino/

        rm -rf ${D}${prefix}/python
        rm -rf ${D}${prefix}/deployment_tools
    fi

    # Remove the samples source directory. We install the built samples.
    rm -rf ${D}/usr/samples
}

# Otherwise e.g. ros-openvino-toolkit-dynamic-vino-sample when using dldt-inference-engine uses dldt-inference-engine WORKDIR
# instead of RSS
SSTATE_SCAN_FILES_append = " *.cmake"

FILES_${PN}-dev = "${includedir} \
                   ${libdir}/cmake \
                   "

FILES_${PN} += "${libdir}/lib*${SOLIBSDEV} \
                ${datadir}/openvino \
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
