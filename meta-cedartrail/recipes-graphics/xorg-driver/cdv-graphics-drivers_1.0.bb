SUMMARY = "Cedartrail PowerVR Graphics Driver version [Gold] 1.0 binaries"
DESCRIPTION = "2D, 3D and Media user space driver for Cedartrail platform \
The binaries are covered by the Intel Free Distribution Binary License. \ 
The user must make himself/herself aware of the Licensing terms \
before enabling build of the Cedartrail PowerVR Graphics Driver via \
this recipe.  Please see the README in meta-cedartrail for instructions \
for enabling the build of the driver "

 
PR = r0

S  = ${WORKDIR}/cdv-graphics-drivers_${PV}

LICENSE = "Intel Free Distribution Binary License"
LIC_FILES_CHKSUM = " \
    file://${S}/usr/share/doc/psb-video-cdv-0.12/license.txt;md5=b14d99f8d4ed664e9ce95057f0bb5b65  \
    file://${S}/usr/share/doc/pvr-bin-cdv-1.7.788837_05/license.txt;md5=b14d99f8d4ed664e9ce95057f0bb5b65"

DEPENDS = "libva"

CDR_TRAIL = "download.meego.com/live/MeeGo:/1.2.0:/CedarTrail:"

SRC_URI = " \
  http://${CDR_TRAIL}/non-oss/MeeGo_1.2.0_CedarTrail/i586/psb-video-cdv-0.12-1.1.i586.rpm;name=psb  \
  http://${CDR_TRAIL}/non-oss/MeeGo_1.2.0_CedarTrail/i586/pvr-bin-cdv-1.7.788837_05-1.1.i586.rpm;name=pvr \
  http://${CDR_TRAIL}/oss/standard/i586/libwsbm-cdv-1.1.0-3.1.i586.rpm;name=libwsbm \
            "
SRC_URI[psb.md5sum] = "d4b6b383722264f3b781aeb240c88037"
SRC_URI[psb.sha256sum] = "e88f95fc73a79adf76ee33d3d9874cec23bb1afe8149d7dc5842d67e58da72f5"
SRC_URI[pvr.md5sum] = "951fa9edcbc2a3ddb30450079869362e"
SRC_URI[pvr.sha256sum] = "537dd8a98ac2e3a101063abc62682c3be8c37ac29782a876eafce113ffa5b421"
SRC_URI[libwsbm.md5sum] = "8d90436b151ddf72f620771f2552b597"
SRC_URI[libwsbm.sha256sum] = "82f78f47c151f0e7d567574ee372504e5b395fb13796caa765f9c30754b5bf63"

do_configure () {

# Extract  license files from rpms
rpm2cpio ${WORKDIR}/psb-video-cdv-0.12-1.1.i586.rpm |cpio -ivd ./usr/share/doc/psb-video-cdv-0.12/license.txt
rpm2cpio ${WORKDIR}/pvr-bin-cdv-1.7.788837_05-1.1.i586.rpm |cpio -ivd ./usr/share/doc/pvr-bin-cdv-1.7.788837_05/license.txt

}

do_install() {

install -m 0644 ${WORKDIR}/psb-video-cdv-0.12-1.1.i586.rpm ${D}
install -m 0644 ${WORKDIR}/pvr-bin-cdv-1.7.788837_05-1.1.i586.rpm ${D}
install -m 0644 ${WORKDIR}/libwsbm-cdv-1.1.0-3.1.i586.rpm ${D}

cd ${D}

rpm2cpio psb-video-cdv-0.12-1.1.i586.rpm | cpio -idmv
rpm2cpio pvr-bin-cdv-1.7.788837_05-1.1.i586.rpm | cpio -idmv
rpm2cpio libwsbm-cdv-1.1.0-3.1.i586.rpm | cpio -idmv

install -m 0755 ${D}/${libdir}/libpvr2d.so.1.7.788837  ${D}/${libdir}/libpvr2d.so
install -m 0755 ${D}/${libdir}/libsrv_um.so.1.7.788837 ${D}/${libdir}/libsrv_um.so
install -m 0755 ${D}/${libdir}/libegl4ogl.so.1.7.788837 ${D}/${libdir}/libegl4ogl.so
install -m 0755 ${D}/${libdir}/libPVROGL_MESA.so.1.7.788837 ${D}/${libdir}/libPVROGL_MESA.so
install -m 0755 ${D}/${libdir}/libIMGegl.so.1.7.788837 ${D}/${libdir}/libIMGegl.so
install -m 0755 ${D}/${libdir}/libusc.so.1.7.788837 ${D}/${libdir}/libusc.so
install -m 0755 ${D}/${libdir}/libOpenVG.so.1.7.788837 ${D}/${libdir}/libOpenVG.so

install -m 0644 ${S}/usr/share/doc/psb-video-cdv-0.12/license.txt ${WORKDIR}/license-destdir/cdv-graphics-drivers/license.txt  

rm -f *.rpm
}

FILES_${PN} += "${libdir}/pvr/cdv/lib*.so.*"
FILES_${PN} += "${base_libdir}/firmware"
FILES_${PN} += "${libdir}/debug/usr/bin"
FILES_${PN} += "${libdir}/debug/usr/lib"
FILES_${PN} += "${libdir}/lib*.so"

FILES_${PN} += "${libdir}/dri/*.so"
FILES_${PN} += "${libdir}/pvr/cdv/dri/*.so"
FILES_${PN} += "${libdir}/xorg/modules/drivers/*.so"
FILES_${PN} += "${libdir}/pvr/cdv/xorg/modules/drivers/*.so"

FILES_${PN}-dbg += "${libdir}/dri/.debug/*"

addtask check_pvr_license before do_fetch

python do_check_pvr_license() {
    pn = bb.data.getVar('PN', d, 1)
    pvr_license = bb.data.getVar('PVR_LICENSE', d, 1)
    if not pvr_license or not pvr_license.lower() == "yes":
        bb.debug(1, "Skipping %s because it may have a non-free license" % pn)
        raise bb.parse.SkipPackage("because it requires PVR_LICENSE = \"yes\" in local.conf to ship")
}
