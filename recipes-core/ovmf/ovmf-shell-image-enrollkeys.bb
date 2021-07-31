require recipes-core/ovmf/ovmf-shell-image.bb

WKS_SEARCH_PATH:append = ":${COREBASE}/meta/recipes-core/ovmf"

QB_DRIVE_TYPE = "/dev/vd"

do_image:append() {
    cat > ${IMAGE_ROOTFS}/startup.nsh << EOF
EnrollDefaultKeys
reset
EOF

}
