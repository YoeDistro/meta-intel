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

MKLMAINVER = "2024.0"

LIC_FILES_CHKSUM = " \
                     file://opt/intel/oneapi/mkl/${MKLMAINVER}/share/doc/mkl/licensing/license.txt;md5=8510d21bf355a76e378c3216c3929ccd  \
                     file://opt/intel/oneapi/mkl/${MKLMAINVER}/share/doc/mkl/licensing/third-party-programs-benchmarks.txt;md5=cb98e1a1f14c05ea85a979ea8982e7a4 \
                     file://opt/intel/oneapi/mkl/${MKLMAINVER}/share/doc/mkl/licensing/third-party-programs-ipp.txt;md5=a4b2bf15e38f5c1267cdafed18bc0b09 \
                     file://opt/intel/oneapi/mkl/${MKLMAINVER}/share/doc/mkl/licensing/third-party-programs-openmp.txt;md5=6b3c1aa2a11393060074c0346ce21e49 \
                     file://opt/intel/oneapi/mkl/${MKLMAINVER}/share/doc/mkl/licensing/third-party-programs-safestring.txt;md5=c3aeee91c6d35a0f0753aed6c2633b82 \
                     file://opt/intel/oneapi/mkl/${MKLMAINVER}/share/doc/mkl/licensing/third-party-programs.txt;md5=27de873e4084d62530fe828406b33ca9 \
                     "

SRC_URI = " \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-mkl-${MKLMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=runtime \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-mkl-common-devel-${MKLMAINVER}-${PV}_all.deb;subdir=${BPN};name=common-devel \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-mkl-devel-${MKLMAINVER}-${PV}_amd64.deb;subdir=${BPN};name=devel \
            https://apt.repos.intel.com/oneapi/pool/main/intel-oneapi-mkl-common-${MKLMAINVER}-${PV}_all.deb;subdir=${BPN};name=common-vars \
            "

SRC_URI[runtime.sha256sum] = "10a86e24051d6ef4a80fd839c570e629190638a3c0ac9bcca99ab855f534b959"
SRC_URI[common-devel.sha256sum] = "adbf0ea946f63946d29b7f9c750c38a42ea7a65d8c81655b268aa2c7bb908192"
SRC_URI[devel.sha256sum] = "fab2a6f15e18bfd9b4d425f2703e4e98928c57f52c4feebc9ed886f097062e84"
SRC_URI[common-vars.sha256sum] = "ec2b67813739fa4a2895f63479a41acba2174afe2d0cb8a0c1c9119d1317d8ef"

S = "${WORKDIR}/${BPN}"

inherit bin_package

do_install:append () {
	install -d ${D}${bindir}
	(cd ${D}${bindir} ; ln -s ../../opt/intel/oneapi/mkl/${MKLMAINVER}/bin/* .)
	install -d ${D}${libdir}
	(cd ${D}${libdir} ; ln -s ../../opt/intel/oneapi/mkl/${MKLMAINVER}/lib/intel64/*.so* .)
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

AUTO_LIBNAME_PKGS = ""
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

RDEPENDS:${PN} += "bash tbb intel-oneapi-dpcpp-cpp-runtime setup-intel-oneapi-env virtual-opencl-icd"
INSANE_SKIP:${PN} = "ldflags textrel dev-so staticdev arch already-stripped"

FILES:${PN}-staticdev += "/opt/intel/oneapi/mkl/${MKLMAINVER}/lib/*.a*"

SKIP_FILEDEPS:${PN} = '1'

SYSROOT_DIRS += "/opt"
