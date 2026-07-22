SUMMARY = "Intel® oneAPI Toolkit (2026.0) - unified Base + HPC toolkit"
DESCRIPTION = "The Intel® oneAPI Toolkit unifies the former Base and HPC \
toolkits into a single bundle. It ships the DPC++/C++ compiler (icx, \
icpx, dpcpp), Fortran compiler (ifx), DPC++ Compatibility Tool, Intel® \
Distribution for GDB*, oneAPI DPC++ Library (oneDPL), oneAPI Threading \
Building Blocks (oneTBB), oneAPI Math Kernel Library (oneMKL), oneAPI \
Deep Neural Network Library (oneDNN), oneCCL, Intel® IPP, Intel® \
Cryptography Primitives Library, and VTune Profiler. \
\
This recipe consumes the upstream offline self-extracting installer \
(intel-oneapi-toolkit-${PV}_offline.sh) and unpacks it into the target \
rootfs under /opt/intel/oneapi via Intel's silent installer. Each \
component is split into its own sub-package (intel-oneapi-toolkit-\
{compiler,runtime,mkl,tbb,dpl,debugger,ipp,ccl,vtune,onednn,common,\
licensing}). \
\
Note: oneDAL is no longer included by Intel and must be installed \
separately."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/tools/oneapi/oneapi-toolkit-download.html"

LICENSE = "LicenseRef-EULA"
LIC_FILES_CHKSUM = "\
    file://license.txt;md5=cff4e57efd53801fcdfcb4c8aead1245 \
"

# Self-extracting installer; treat as opaque blob (no auto-unpack).
SRC_URI = " \
    https://registrationcenter-download.intel.com/akdlm/IRC_NAS/71180075-e4e3-4c6f-bbbb-19017ed0cf7d/intel-oneapi-toolkit-${PV}_offline.sh;unpack=0;name=installer \
    file://license.txt \
"
SRC_URI[installer.sha256sum] = "155a52896bd24239ddc733f487eccb2af5cad9957eed1e2bfe60ce1453694e5b"

# Distributed as an opaque offline installer from a per-release download UUID
# URL; there is no version listing for the checker to scan.
RECIPE_NO_UPDATE_REASON = "Opaque offline installer; no upstream version listing"

S = "${UNPACKDIR}"

inherit bin_package

B = "${WORKDIR}/build"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
SKIP_FILEDEPS:${PN} = "1"
SKIP_FILEDEPS:${PN}-runtime = "1"
SKIP_FILEDEPS:${PN}-compiler = "1"
SKIP_FILEDEPS:${PN}-common = "1"
SKIP_FILEDEPS:${PN}-licensing = "1"
SKIP_FILEDEPS:${PN}-mkl = "1"
SKIP_FILEDEPS:${PN}-mkl-sycl = "1"
SKIP_FILEDEPS:${PN}-mkl-staticdev = "1"
SKIP_FILEDEPS:${PN}-mkl-dev = "1"
SKIP_FILEDEPS:${PN}-tbb = "1"
SKIP_FILEDEPS:${PN}-dpl = "1"
SKIP_FILEDEPS:${PN}-debugger = "1"
SKIP_FILEDEPS:${PN}-ipp = "1"
SKIP_FILEDEPS:${PN}-ipp-staticdev = "1"
SKIP_FILEDEPS:${PN}-ipp-dev = "1"
SKIP_FILEDEPS:${PN}-ccl = "1"
SKIP_FILEDEPS:${PN}-vtune = "1"
SKIP_FILEDEPS:${PN}-onednn = "1"

# dev-deps is set on the meta-package and -common because the umbrella
# RDEPENDS pulls the -mkl-dev / -ipp-dev sub-packages so a single
# IMAGE_INSTALL of intel-oneapi-toolkit still ships the full SDK.
ONEAPI_INSANE = "textrel dev-so dev-elf ldflags already-stripped staticdev rpaths arch useless-rpaths file-rdeps libdir buildpaths host-user-contaminated installed-vs-shipped 32bit-time dev-deps"
INSANE_SKIP:${PN} += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-runtime += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-compiler += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-common += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-licensing += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-mkl += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-mkl-sycl += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-mkl-staticdev += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-mkl-dev += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-tbb += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-dpl += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-debugger += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-ipp += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-ipp-staticdev += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-ipp-dev += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-ccl += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-vtune += "${ONEAPI_INSANE}"
INSANE_SKIP:${PN}-onednn += "${ONEAPI_INSANE}"

FILES_SOLIBSDEV = ""
EXCLUDE_FROM_SHLIBS = "1"
PACKAGE_DEBUG_FLAGS = ""

# Versions of components used in do_install runtime registration.
# (Component sub-directories are matched by name in FILES, so per-component
# version variables are not needed.)
COMPILERMAINVER = "2026.0"

ONEAPI_INSTALLER = "${S}/intel-oneapi-toolkit-${PV}_offline.sh"
ONEAPI_STAGE     = "${B}/oneapi-staging"

# do_unpack_vendor runs without pseudo. ${B} (B = ${WORKDIR}/build) is a
# sibling of ${T}, so the installer's cleanup never reaches the fifo.
do_unpack_vendor() {
    rm -rf "${ONEAPI_STAGE}"
    install -d "${ONEAPI_STAGE}/opt/intel/oneapi"

    # WARNING: never reference ${HOME} or ${TMPDIR} as bitbake-expanded
    # variables inside this function — bitbake expands them at parse
    # time to the build host's real values, and a later `rm -rf` would
    # nuke the developer's home directory. Use plain shell locals only.
    oneapi_home="${B}/oneapi-installer-home"
    oneapi_tmp="${B}/oneapi-installer-tmp"
    oneapi_extract="${B}/oneapi-installer-extract"
    rm -rf "$oneapi_home" "$oneapi_tmp" "$oneapi_extract"
    install -d "$oneapi_home" "$oneapi_tmp" "$oneapi_extract"

    # The installer needs a writable HOME and TMPDIR.
    export HOME="$oneapi_home"
    export TMPDIR="$oneapi_tmp"

    cd "$oneapi_extract"

    bash ${ONEAPI_INSTALLER} \
        --extract-folder "$oneapi_extract" \
        --remove-extracted-files no \
        -a --silent --eula accept \
        --install-dir "${ONEAPI_STAGE}/opt/intel/oneapi" \
        --log-dir "$oneapi_tmp" \
        --intel-sw-improvement-program-consent decline

    # Free the makeself extraction tree once the install completed.
    rm -rf "$oneapi_extract" "$oneapi_home" "$oneapi_tmp"

    # Rewrite any absolute build-time paths the installer baked into
    # generated wrapper scripts so the on-target tree is self-contained.
    find "${ONEAPI_STAGE}/opt/intel/oneapi" -type f \
        \( -name '*.sh' -o -name '*.cfg' -o -name 'modulefiles-setup.sh' \) \
        -exec sed -i "s|${ONEAPI_STAGE}/opt/intel/oneapi|/opt/intel/oneapi|g" {} +
}
do_unpack_vendor[network] = "0"
do_unpack_vendor[cleandirs] = "${B}"
addtask unpack_vendor after do_patch before do_install

do_install() {
    install -d ${D}
    cp -a ${ONEAPI_STAGE}/. ${D}/

    # The vendor installer in do_unpack_vendor runs outside pseudo, so
    # all staged files inherit the build host's uid/gid. Reset ownership
    # to root:root under pseudo to avoid host-contamination QA failures
    # ("getpwuid(): uid not found ...") in do_package.
    chown -R 0:0 ${D}

    # Drop the installer's transient log directory (build-time noise).
    rm -rf ${D}/opt/intel/oneapi/logs

    # Auto-register the Intel CPU OpenCL runtime as an ICD.
    install -d ${D}${sysconfdir}/OpenCL/vendors
    cpu_icd="/opt/intel/oneapi/compiler/${COMPILERMAINVER}/lib/libintelocl.so"
    if [ -e "${D}${cpu_icd}" ]; then
        echo "${cpu_icd}" > ${D}${sysconfdir}/OpenCL/vendors/intel-cpu.icd
    fi

    # libxml2.so.2 SONAME compat shim (oe-core ships libxml2 3.x = .so.16).
    install -d ${D}${libdir}
    if [ ! -e ${D}${libdir}/libxml2.so.2 ]; then
        ln -s libxml2.so.16 ${D}${libdir}/libxml2.so.2
    fi
}

SYSROOT_DIRS += "/opt"

# Sub-packages. Order matters in PACKAGES — first-match wins for FILES
# patterns, so put specific component packages before the catch-all ${PN}.
# Within a component, list the more specific sub-subpackages (-sycl,
# -staticdev, -dev) before the component's own catch-all so the
# heavyweight bits land in their dedicated package and not in the
# default runtime package.
PACKAGES =+ " \
    ${PN}-runtime \
    ${PN}-compiler \
    ${PN}-mkl-sycl \
    ${PN}-mkl-staticdev \
    ${PN}-mkl-dev \
    ${PN}-mkl \
    ${PN}-tbb \
    ${PN}-dpl \
    ${PN}-debugger \
    ${PN}-ipp-staticdev \
    ${PN}-ipp-dev \
    ${PN}-ipp \
    ${PN}-ccl \
    ${PN}-vtune \
    ${PN}-onednn \
    ${PN}-licensing \
    ${PN}-common \
"

FILES:${PN}-licensing = "/opt/intel/oneapi/licensing"
FILES:${PN}-common = " \
    /opt/intel/oneapi/setvars.sh \
    /opt/intel/oneapi/modulefiles-setup.sh \
    /opt/intel/oneapi/support.txt \
    /opt/intel/oneapi/common \
    /opt/intel/oneapi/2026.0 \
    /opt/intel/oneapi/oneapi-toolkit \
"
FILES:${PN}-onednn = "/opt/intel/oneapi/dnnl"
FILES:${PN}-tbb = "/opt/intel/oneapi/tbb"
FILES:${PN}-dpl = "/opt/intel/oneapi/dpl"
# MKL is split four ways. Order in PACKAGES above ensures the specific
# patterns match before the catch-all /opt/intel/oneapi/mkl.
#
#   -mkl-sycl       GPU/SYCL offload libraries (~470 MB)
#   -mkl-staticdev  Static archives, lib/intel64/*.a (~900 MB)
#   -mkl-dev        Headers, cmake/pkgconfig modules, share/ examples
#   -mkl            CPU runtime shared libs + env/etc/bin + symlinks
FILES:${PN}-mkl-sycl = "/opt/intel/oneapi/mkl/[0-9]*/lib/libmkl_sycl*"
FILES:${PN}-mkl-staticdev = "/opt/intel/oneapi/mkl/[0-9]*/lib/*.a"
FILES:${PN}-mkl-dev = " \
    /opt/intel/oneapi/mkl/[0-9]*/include \
    /opt/intel/oneapi/mkl/[0-9]*/lib/cmake \
    /opt/intel/oneapi/mkl/[0-9]*/lib/pkgconfig \
    /opt/intel/oneapi/mkl/[0-9]*/share \
"
FILES:${PN}-mkl = "/opt/intel/oneapi/mkl"
FILES:${PN}-debugger = "/opt/intel/oneapi/debugger"
# IPP is split three ways. ippcp (Cryptography Primitives) is bundled
# with ipp in this distribution and shares the same split policy.
#
#   -ipp-staticdev  Static archives, lib/intel64/*.a (~560 MB)
#   -ipp-dev        Headers, cmake/pkgconfig modules, share/ examples
#   -ipp            Runtime shared libs + env/etc/bin + symlinks
FILES:${PN}-ipp-staticdev = " \
    /opt/intel/oneapi/ipp/[0-9]*/lib/*.a \
    /opt/intel/oneapi/ippcp/[0-9]*/lib/*.a \
"
FILES:${PN}-ipp-dev = " \
    /opt/intel/oneapi/ipp/[0-9]*/include \
    /opt/intel/oneapi/ipp/[0-9]*/lib/cmake \
    /opt/intel/oneapi/ipp/[0-9]*/lib/pkgconfig \
    /opt/intel/oneapi/ipp/[0-9]*/share \
    /opt/intel/oneapi/ippcp/[0-9]*/include \
    /opt/intel/oneapi/ippcp/[0-9]*/lib/cmake \
    /opt/intel/oneapi/ippcp/[0-9]*/lib/pkgconfig \
    /opt/intel/oneapi/ippcp/[0-9]*/share \
"
FILES:${PN}-ipp = " \
    /opt/intel/oneapi/ipp \
    /opt/intel/oneapi/ippcp \
"
FILES:${PN}-ccl = "/opt/intel/oneapi/ccl"
FILES:${PN}-vtune = "/opt/intel/oneapi/vtune"
FILES:${PN}-compiler = " \
    /opt/intel/oneapi/compiler \
    /opt/intel/oneapi/dev-utilities \
    /opt/intel/oneapi/tcm \
    /opt/intel/oneapi/umf \
    /opt/intel/oneapi/mpi \
"
FILES:${PN}-runtime = " \
    ${sysconfdir}/OpenCL/vendors/intel-cpu.icd \
    ${libdir}/libxml2.so.2 \
"

# Inter-package dependencies.
#
# Hard RDEPENDS are intentionally minimal -- only the link/load-time
# closure the dynamic linker will demand at runtime. This lets a
# consumer install a single subpackage (e.g. intel-oneapi-toolkit-mkl
# for PyTorch CPU BLAS) without dragging in the full bundle.
#
#   * -common    : setvars.sh / modulefiles / version-umbrella dirs.
#                  Required only when the consumer sources env/vars.sh;
#                  apps that link the .so files directly do not need it.
#   * -runtime   : OpenCL/Level Zero ICD shim + libxml2 SONAME compat.
#                  Required only for SYCL/GPU offload paths. Pure CPU
#                  MKL/IPP/oneDNN do not need it.
#   * -licensing : EULA text (~1 MB). Kept on every binary subpackage.
#   * -tbb       : oneTBB shared library. Required by every component
#                  whose libraries link -ltbb (mkl, compiler, onednn).
RDEPENDS:${PN}-licensing += ""
RDEPENDS:${PN}-common    += "${PN}-licensing"
RDEPENDS:${PN}-runtime   += "virtual-opencl-icd zlib level-zero-loader bash libxml2 ${PN}-licensing"
RDEPENDS:${PN}-tbb       += "${PN}-licensing"
RDEPENDS:${PN}-dpl       += "${PN}-licensing"
RDEPENDS:${PN}-debugger  += "${PN}-licensing"
RDEPENDS:${PN}-ipp           += "${PN}-licensing"
RDEPENDS:${PN}-ipp-dev       += "${PN}-ipp"
RDEPENDS:${PN}-ipp-staticdev += "${PN}-ipp-dev"
RDEPENDS:${PN}-vtune     += "${PN}-licensing"
RDEPENDS:${PN}-mkl           += "${PN}-tbb ${PN}-licensing"
RDEPENDS:${PN}-mkl-sycl      += "${PN}-mkl ${PN}-runtime"
RDEPENDS:${PN}-mkl-dev       += "${PN}-mkl"
RDEPENDS:${PN}-mkl-staticdev += "${PN}-mkl-dev"
RDEPENDS:${PN}-onednn    += "${PN}-tbb ${PN}-licensing"
RDEPENDS:${PN}-compiler  += "${PN}-runtime ${PN}-common ${PN}-tbb ${PN}-licensing"
RDEPENDS:${PN}-ccl       += "${PN}-runtime ${PN}-licensing"

# Top-level meta package pulls in the full bundle, including the
# heavyweight optional pieces (SYCL/GPU offload libs, static archives
# and development headers) so that "IMAGE_INSTALL += intel-oneapi-toolkit"
# behaves like the legacy single-package install.
RDEPENDS:${PN} = " \
    ${PN}-compiler \
    ${PN}-runtime \
    ${PN}-mkl \
    ${PN}-mkl-sycl \
    ${PN}-mkl-staticdev \
    ${PN}-mkl-dev \
    ${PN}-tbb \
    ${PN}-dpl \
    ${PN}-debugger \
    ${PN}-ipp \
    ${PN}-ipp-staticdev \
    ${PN}-ipp-dev \
    ${PN}-ccl \
    ${PN}-vtune \
    ${PN}-onednn \
    ${PN}-common \
    ${PN}-licensing \
"

# This bundle is amd64-only.
COMPATIBLE_HOST = "x86_64.*-linux"
