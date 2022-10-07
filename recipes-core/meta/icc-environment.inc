export TARGET_ICC_ARCH = "${TARGET_CC_ARCH}"
export ICCSDK_PREFIX_OPTION = "-gnu-prefix=${TARGET_PREFIX}"
export ICCQSDK_PREFIX_OPTION = "-qgnu-prefix=${TARGET_PREFIX}"
export ICCSDK_GCC_OPTION = "-gcc-name=${bindir}/${TARGET_PREFIX}gcc"
export ICCSDK_GXX_OPTION = "-gxx-name=${bindir}/${TARGET_PREFIX}g++"

create_sdk_files:append() {
        script=${SDK_OUTPUT}/${SDKPATH}/environment-setup-${REAL_MULTIMACH_TARGET_SYS}
        if ${@bb.utils.contains('ICCSDK', '1', 'true', 'false', d)}; then
            echo 'export ICC="icc ${ICCSDK_PREFIX_OPTION} ${HOST_CC_ARCH} ${TARGET_ICC_ARCH} --sysroot=$SDKTARGETSYSROOT ${ICCSDK_GCC_OPTION}"' >> $script
            echo 'export ICCCXX="icpc ${ICCSDK_PREFIX_OPTION} ${HOST_CC_ARCH} ${TARGET_ICC_ARCH} --sysroot=$SDKTARGETSYSROOT ${ICCSDK_GXX_OPTION}"' >> $script
            echo 'export ICCCPP="icc ${ICCSDK_PREFIX_OPTION} -E ${HOST_CC_ARCH} ${TARGET_ICC_ARCH} --sysroot=$SDKTARGETSYSROOT ${ICCSDK_GXX_OPTION}"' >> $script
            echo 'export ICCLD="xild  --sysroot=$SDKTARGETSYSROOT ${HOST_LD_ARCH}"' >> $script
            echo 'export ICCCCLD="icc ${ICCSDK_PREFIX_OPTION} ${HOST_CC_ARCH} --sysroot=$SDKTARGETSYSROOT ${ICCSDK_GCC_OPTION}"' >> $script
            echo 'export ICCAR="xiar"' >> $script
        fi
}
