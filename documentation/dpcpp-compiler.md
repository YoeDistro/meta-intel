Intel(R) oneAPI DPC++/C++ Compiler (ICX) toolchain
==========================================================================

Get Started with the Intel oneAPI DPC++/C++ Compiler:

https://www.intel.com/content/www/us/en/developer/tools/oneapi/dpc-compiler.html#


Getting Started
===============

Clone the required layers and include them in bblayers.conf:

```
git clone https://git.openembedded.org/openembedded-core
git clone https://git.openembedded.org/bitbake
git clone https://git.openembedded.org/meta-openembedded
git clone https://github.com/kraj/meta-clang.git
git clone https://git.yoctoproject.org/meta-intel

$ source openembedded-core/oe-init-build-env

$ bitbake-layers add-layer ../meta-openembedded/meta-oe/
$ bitbake-layers add-layer ../meta-intel
$ bitbake-layers add-layer ../meta-clang
```

Distro
======

Note that oneAPI DPC++/C++ compiler currently only works when the vendor string is "oe".

```
DISTRO ?= "nodistro"
```

MACHINE configuration
=====================

```
MACHINE ?= "intel-skylake-64"
```

Package installation
====================

```
# To include OpenCL driver that might be needed when compiling SYCL programs, include:
IMAGE_INSTALL:append = " intel-compute-runtime intel-graphics-compiler"

# To install only runtime libraries, include:
IMAGE_INSTALL:append = " intel-oneapi-dpcpp-cpp-runtime intel-oneapi-dpcpp-cpp-runtime-dev"

# To install the toolchain, include:
IMAGE_INSTALL:append = " intel-oneapi-dpcpp-cpp intel-oneapi-dpcpp-cpp-dev"
```
in local.conf.

Build an image
==============

```
$ bitbake core-image-minimal
```

Including oneAPI C++/DPC++ compiler in generated SDK toolchain
==============================================================

The compiler is not included in the generated SDK by default. If it is expected to be part of SDK, add ICXSDK = "1" in local.conf:

```
ICXSDK = "1"
```

Generate SDK:
```
bitbake core-image-minimal -c populate_sdk
```


To setup PATH variables on target
=================================

Once image is booted successfully, some variables would need to be exported to make sure compiler can be used:

```
$ source /opt/intel/oneapi/compiler/2022.1.0/env/vars.sh

$ mkdir -p /lib64

$ ln -sf /lib/ld-linux-x86-64.so.2 /lib64/ld-linux-x86-64.so.2
```

Build application and run
=========================

To compile a sycl application, for example:

```
$ icpx --target=x86_64-oe-linux -fsycl simple-sycl-app.c -o simple-sycl-app
```

To run:

```
$ ./simple-sycl-app
```
