SUMMARY = "OpenVINO(TM) Toolkit - Deep Learning Deployment Toolkit"
HOMEPAGE = "https://github.com/opencv/dldt"
DESCRIPTION = "This toolkit allows developers to deploy pre-trained \
deep learning models through a high-level C++ Inference Engine API \
integrated with application logic."

SRC_URI = "git://github.com/opencv/dldt.git;protocol=git;branch=2019 \
           https://download.01.org/opencv/2019/openvinotoolkit/R3/inference_engine/firmware_ma2450_759W.zip;name=ma2450 \
           https://download.01.org/opencv/2019/openvinotoolkit/R3/inference_engine/firmware_ma2x8x_mdk_R9.8.zip;name=ma2x8x \
           https://download.01.org/opencv/2019/openvinotoolkit/R3/inference_engine/firmware_mv0262_mdk_R9.8.zip;name=mv0262 \
           file://0001-R2-Build-fixes.patch;patchdir=.. \
           file://0002-R2-Install-DLDT-headers-libs-sample-Apps.patch;patchdir=.. \
           file://0003-use-GNUInstallDirs-on-nix.patch;patchdir=.. \
           file://0004-disable-werror.patch;patchdir=.. \
           file://0005-point-to-correct-location-of-ngraph-headers.patch;patchdir=.. \
           file://0006-Install-clDNN-plugin-to-CMAKE_INSTALL_LIBDIR.patch;patchdir=.. \
           file://0007-Install-mock_engine-to-CMAKE_INSTALL_LIBDIR.patch;patchdir=.. \
           file://0008-Simplify-searching-for-firmware-files.patch;patchdir=.. \
           file://run-ptest \
           "
SRCREV = "1c794d971cdd3c94de5ea4a6c9588eac63f4cc57"

SRC_URI[ma2450.md5sum] = "852f80c3248c451a6c8bfd96d871318f"
SRC_URI[ma2450.sha256sum] = "ba1b23b45cd8e548ead77af49b0e378100cf4f8dcda41a491868d3d4e7bf8cba"

SRC_URI[ma2x8x.md5sum] = "2d9ca6894cdd25e9b10f7908c415dbc9"
SRC_URI[ma2x8x.sha256sum] = "961ce4c7b14823a528185f5e75b3b6898a7e8f866b4b5b8af5f4f05f72226909"

SRC_URI[mv0262.md5sum] = "c495f0ee0313e9d8d993e846a7868c4f"
SRC_URI[mv0262.sha256sum] = "0c0c8d7782f49dd4928837b52b4b6f25c1b088bfee23e464729057a28323ab77"

LICENSE = "Apache-2.0 & ISSL"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
    file://../../ISSL.txt;md5=ab31aad35d740d800ae1e332d6290f50 \
"
LICENSE_${PN}-vpu-firmware = "ISSL"

inherit cmake ptest python3native

S = "${WORKDIR}/git/inference-engine"

EXTRA_OECMAKE += " \
                  -DENABLE_OPENCV=0 \
                  -DENABLE_SAMPLES_CORE=1 \
                  -DENABLE_PLUGIN_RPATH=0 \
                  -DENABLE_GNA=0 \
                  -DPYTHON_EXECUTABLE=${PYTHON} \
                  -DTHREADING=TBB \
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
            opencv \
            pugixml \
            ngraph \
            tbb \
            ${@bb.utils.contains('PTEST_ENABLED', '1', 'gflags', '0', d)} \
            "

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST_libc-musl = "null"

PACKAGECONFIG ?= "vpu"
PACKAGECONFIG[opencl] = "-DENABLE_CLDNN=1 -DCLDNN__IOCL_ICD_INCDIRS=${STAGING_INCDIR} -DCLDNN__IOCL_ICD_STLDIRS=${STAGING_LIBDIR} -DCLDNN__IOCL_ICD_SHLDIRS=${STAGING_LIBDIR}, -DENABLE_CLDNN=0, opencl-icd-loader, opencl-icd-loader intel-compute-runtime"
PACKAGECONFIG[python3] = "-DENABLE_PYTHON=ON -DPYTHON_LIBRARY=${PYTHON_LIBRARY} -DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR}, -DENABLE_PYTHON=OFF, python3-cython-native, python3"
PACKAGECONFIG[vpu] = "-DENABLE_VPU=ON -DVPU_FIRMWARE_MA2450_FILE=../mvnc/MvNCAPI-ma2450.mvcmd -DVPU_FIRMWARE_MA2X8X_FILE=../mvnc/MvNCAPI-ma2x8x.mvcmd -DVPU_FIRMWARE_MV0262_FILE=../mvnc/MvNCAPI-mv0262.mvcmd,-DENABLE_VPU=OFF,,${PN}-vpu-firmware"
PACKAGECONFIG[verbose] = "-DVERBOSE_BUILD=1,-DVERBOSE_BUILD=0"

do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'vpu', 'true', 'false', d)}; then
        install -m0644 ${WORKDIR}/mvnc/MvNCAPI-ma2450.mvcmd ${D}${libdir}
        install -m0644 ${WORKDIR}/mvnc/MvNCAPI-ma2x8x.mvcmd ${D}${libdir}
        install -m0644 ${WORKDIR}/mvnc/MvNCAPI-mv0262.mvcmd ${D}${libdir}

        install -D -m0644 ${S}/thirdparty/movidius/mvnc/src/97-myriad-usbboot.rules ${D}${sysconfdir}/udev/rules.d/97-myriad-usbboot.rules
    fi
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
                   ${libdir}/libinference_engine.so \
                   "

FILES_${PN} += "${libdir}/lib*${SOLIBSDEV} \
                ${datadir}/openvino \
                "

# Move inference engine samples into a separate package
PACKAGES =+ "${PN}-samples ${PN}-vpu-firmware"

FILES_${PN}-samples = "${bindir}"
FILES_${PN}-vpu-firmware += "${libdir}/*.mvcmd"

# Package for inference engine python API
PACKAGES =+ "${PN}-${PYTHON_PN}"

FILES_${PN}-${PYTHON_PN} = "${PYTHON_SITEPACKAGES_DIR}/openvino"
