# meta-intel

OpenEmbedded/Yocto BSP layer for Intel platforms.

## Dependencies

This layer primarily depends on OpenEmbedded-Core (OE-Core). However, certain
recipes may require additional layers to support optional features or
programming languages not supported by OE-Core. Such recipes are located within
the `dynamic-layers` directory.

Base dependencies:
- [Bitbake](https://git.openembedded.org/bitbake)
- [OE-Core](https://git.openembedded.org/openembedded-core)

Dynamic additional dependencies:

- [meta-openembedded](https://git.openembedded.org/meta-openembedded/tree/meta-oe)
- [meta-python](https://git.openembedded.org/meta-openembedded/tree/meta-python)
- [meta-clang](https://github.com/kraj/meta-clang.git)


## Contents

- [Building and booting meta-intel BSP layers](documentation/building_and_booting.md)
- [Intel oneAPI DPC++/C++ Compiler](documentation/dpcpp-compiler.md)
- [Build Image with OpenVINO™ toolkit](documentation/openvino.md)
- [Tested Hardware](documentation/tested_hardware.md)
- [Guidelines for submitting patches](documentation/submitting_patches.md)
- [Reporting bugs](documentation/reporting_bugs.md)
- [Reporting security bugs](SECURITY.md)

## Maintainers

- Yogesh Tyagi <yogesh.tyagi@intel.com>
