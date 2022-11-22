export ICX_LDFLAGS_OPTION = " -Wl,-dynamic-linker,/lib/ld-linux-x86-64.so.2"
export ICXSDK_PREFIX_OPTION = "-B ${TARGET_PREFIX}"
export ICXQSDK_PREFIX_OPTION = "-qgnu-prefix=${TARGET_PREFIX}"

create_sdk_files:append() {
        script=${SDK_OUTPUT}/${SDKPATH}/environment-setup-${REAL_MULTIMACH_TARGET_SYS}
        if ${@bb.utils.contains('ICXSDK', '1', 'true', 'false', d)}; then
            echo 'export ICX="icx ${ICXSDK_PREFIX_OPTION} --target=${TARGET_SYS} ${TARGET_CC_ARCH} --sysroot=$SDKTARGETSYSROOT ${ICX_LDFLAGS_OPTION}"' >> $script
            echo 'export ICXCXX="icpx ${ICXSDK_PREFIX_OPTION} --target=${TARGET_SYS} ${TARGET_CC_ARCH} --sysroot=$SDKTARGETSYSROOT ${ICX_LDFLAGS_OPTION}"' >> $script
            echo 'export ICXCPP="icx ${ICXSDK_PREFIX_OPTION} -E --target=${TARGET_SYS} ${TARGET_CC_ARCH} --sysroot=$SDKTARGETSYSROOT ${ICX_LDFLAGS_OPTION}"' >> $script
            echo 'export ICXLD="xild  --sysroot=$SDKTARGETSYSROOT "' >> $script
            echo 'export ICXCCLD="icx ${ICXSDK_PREFIX_OPTION} --sysroot=$SDKTARGETSYSROOT"' >> $script
            echo 'export ICXAR="xiar ${ICXQSDK_PREFIX_OPTION}"' >> $script
        fi
}
