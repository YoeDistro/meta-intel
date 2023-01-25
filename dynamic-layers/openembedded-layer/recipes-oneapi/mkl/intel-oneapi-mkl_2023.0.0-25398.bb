SUMMARY = "Intel® oneAPI Math Kernel Library (oneMKL)"
DESCRIPTION = "The Intel® oneAPI Math Kernel Library (oneMKL) is a computing \
 math library of highly optimized and extensively parallelized routines \
 for applications that require maximum performance. oneMKL contains \
 the high-performance optimizations from the full Intel® Math Kernel Library \
 for CPU architectures (with C/Fortran programming language interfaces)\
 and adds to them a set of DPC++ programming language interfaces for \
 achieving performance on various CPU architectures \
 and Intel Graphics Technology for certain key functionalities."
HOMEPAGE = "https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/onemkl.html"

LICENSE = "ISSL"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/lib/licensing/mkl/license.txt;md5=8510d21bf355a76e378c3216c3929ccd \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-benchmarks.txt;md5=cb98e1a1f14c05ea85a979ea8982e7a4 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-ipp.txt;md5=a4b2bf15e38f5c1267cdafed18bc0b09 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-openmp.txt;md5=6b3c1aa2a11393060074c0346ce21e49 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs-safestring.txt;md5=c3aeee91c6d35a0f0753aed6c2633b82 \
                     file://opt/intel/oneapi/lib/licensing/mkl/third-party-programs.txt;md5=980965cf1f086d40998ca4981968b6a4 \
                     "

MKLMAINVER = "2023.0.0"

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-mkl-${PV}_amd64.deb;subdir=${BPN};name=runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-runtime-mkl-common-${PV}_all.deb;subdir=${BPN};name=common \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-mkl-common-devel-${MKLMAINVER}-${PV}_all.deb;subdir=${BPN};name=common-devel \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-mkl-devel-${MKLMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=devel \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-mkl-common-${MKLMAINVER}-${PV}_all.deb;subdir=${BPN};name=common-vars \
            "

SRC_URI[runtime.sha256sum] = "e681b760aa3ca669f56c535cae5bf5b990fbc768301d0d4885b4a6acffd51f3b"
SRC_URI[common.sha256sum] = "5983af05c8f8873d3df772392b87612c09594ca0ee7c2f667857c517b0024062"
SRC_URI[common-devel.sha256sum] = "114861a697500e5ee5e0e5f1e920bc0c96dbacd80145877e4974c582acb26c52"
SRC_URI[devel.sha256sum] = "ee582b8e443b6104e3cc8813f990e8081a0e44d203aa0f65edca0f3406107793"
SRC_URI[common-vars.sha256sum] = "47fc873a5c0b736016ed76b7b99fd93b6bd9c27ee052a23e1944d8095b1b0a6c"

S = "${WORKDIR}/${BPN}"

inherit bin_package

do_install:append () {
	install -d ${D}${bindir}
	(cd ${D}${bindir} ; ln -s ../../opt/intel/oneapi/mkl/${MKLMAINVER}/bin/intel64/* .)
	install -d ${D}${libdir}
	(cd ${D}${libdir} ; ln -s ../../opt/intel/oneapi/lib/intel64/*.so* .)
	(cd ${D}${libdir} ; ln -s ../../opt/intel/oneapi/mkl/${MKLMAINVER}/lib/intel64/*.a* .)
	install -d ${D}${libdir}/pkgconfig
	(cd ${D}${libdir}/pkgconfig ; ln -s ../../../opt/intel/oneapi/mkl/${MKLMAINVER}/lib/pkgconfig/* .)
	install -d ${D}${libdir}/cmake
	(cd ${D}${libdir}/cmake ; ln -s ../../../opt/intel/oneapi/mkl/${MKLMAINVER}/lib/cmake/* .)

	install -d ${D}${includedir}
	find ${D}/opt/intel/oneapi/mkl/${MKLMAINVER}/include/ -mindepth 1 -maxdepth 1 -type d -printf '%f\n' | while read srcdir; do
            install -d ${D}${includedir}/$srcdir
            (cd ${D}${includedir} ; ln -s ../../opt/intel/oneapi/mkl/${MKLMAINVER}/include/$srcdir/* ./$srcdir/)
	done

	find ${D}/opt/intel/oneapi/mkl/${MKLMAINVER}/include/ -mindepth 1 -maxdepth 1 -type f -printf '%f\n' | while read srcfile; do
            (cd ${D}${includedir} ; ln -s ../../opt/intel/oneapi/mkl/${MKLMAINVER}/include/$srcfile .)
	done
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

RDEPENDS:${PN} += "bash tbb intel-oneapi-compiler setup-intel-oneapi-env ocl-icd"
INSANE_SKIP:${PN} = "ldflags textrel dev-so staticdev"

FILES:${PN}-staticdev += "/opt/intel/oneapi/mkl/${MKLMAINVER}/lib/intel64/*.a*"

SKIP_FILEDEPS:${PN} = '1'

SYSROOT_DIRS += "/opt"
