SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/opencv/dldt.git;protocol=git;branch=2019 \
           https://download.01.org/opencv/2019/openvinotoolkit/R2/inference_engine/firmware_ma2450_676.zip;name=ma2450 \
           https://download.01.org/opencv/2019/openvinotoolkit/R2/inference_engine/firmware_ma2x8x_mdk_R8_9.zip;name=ma2x8x \
           file://0001-R2-Build-fixes.patch;patchdir=../ \
           file://0002-R2-Install-DLDT-headers-libs-sample-Apps.patch;patchdir=../ \
           file://0003-use-GNUInstallDirs-on-nix.patch;patchdir=../ \
           file://0003-Supply-firmware-at-build-time.patch;patchdir=../ \
           file://0004-disable-werror.patch;patchdir=../ \
           file://0005-point-to-correct-location-of-ngraph-headers.patch;patchdir=../ \
           file://0001-Install-clDNN-plugin-to-CMAKE_INSTALL_LIBDIR.patch;patchdir=../ \
           file://run-ptest \
           "
SRCREV = "ba6e22b1b5ee4cbefcc30e8d9493cddb0bb3dfdf"

SRC_URI[ma2450.md5sum] = "a241a063db7eaa3de70ebf89817960e0"
SRC_URI[ma2450.sha256sum] = "7fb1aa10c0fde8315fe2af65356a00f09f030d811adddc98731ec28b35368786"

SRC_URI[ma2x8x.md5sum] = "cebebec8d05c70c3d69ed5ceaa11a06b"
SRC_URI[ma2x8x.sha256sum] = "93640eb13e235d3f71a83cd503c36ff8a63235349e1f528d9030eca417ba8c1e"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit cmake ptest

S = "${WORKDIR}/git/inference-engine"

EXTRA_OECMAKE += " \
                  -DVPU_FIRMWARE_MA2450_FILE=../mvnc/MvNCAPI-ma2450.mvcmd \
                  -DVPU_FIRMWARE_MA2X8X_FILE=../mvnc/MvNCAPI-ma2x8x.mvcmd \
                  -DENABLE_OPENCV=0 \
                  -DENABLE_SAMPLES_CORE=1 \
                  -DENABLE_PLUGIN_RPATH=0 \
                  -DENABLE_GNA=0 \
                  -DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 \
                  -DTHREADING=TBB \
                  -DCMAKE_INSTALL_LOCAL_ONLY=OFF \
                  -DCMAKE_BUILD_TYPE=DebugWithRelInfo \
                  -DNGRAPH_INCLUDES=${STAGING_INCDIR}/ngraph \
                  -DENABLE_TESTS="${@bb.utils.contains('PTEST_ENABLED', '1', '1', '0', d)}" \
                  -DBUILD_GMOCK=1 \
                  -DBUILD_GTEST=0 \
                  -DINSTALL_GMOCK=0 \
                  -DINSTALL_GTEST=0 \
                  "

DEPENDS += "libusb1 \
            ade \
            mkl-dnn \
            opencv \
            pugixml \
            ngraph \
            tbb \
            ${@bb.utils.contains('PTEST_ENABLED', '1', 'gflags', '0', d)} \
            "

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

PACKAGECONFIG ?= ""
PACKAGECONFIG[opencl] = "-DENABLE_CLDNN=1, -DENABLE_CLDNN=0, opencl-icd-loader, opencl-icd-loader intel-compute-runtime"

do_install_ptest_base_prepend() {
        # While not a Makefile based project that strictly falls into the category of
        # what ptest helps with, adding the unit tests here as ptest would help.
        # Create a dummy Makefile so installation doesn't fail.
        touch ${WORKDIR}/Makefile
        mv ${D}${bindir}/InferenceEngineUnitTests ${D}${PTEST_PATH}/
}

# Move inference engine samples into a separate package
PACKAGES =+ "${PN}-samples"

FILES_${PN}-dev = "${includedir} \
                   ${libdir}/cmake \
                   ${libdir}/libinference_engine.so \
                   "

FILES_${PN} += "${libdir}/lib*${SOLIBSDEV} \
                ${datadir}/openvino \
                "

FILES_${PN}-samples = "${bindir}"
