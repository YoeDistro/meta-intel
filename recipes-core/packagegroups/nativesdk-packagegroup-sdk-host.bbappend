RDEPENDS:${PN} += "${@bb.utils.contains('ICXSDK', '1', ' intel-oneapi-dpcpp-cpp intel-oneapi-dpcpp-cpp-dev intel-oneapi-dpcpp-cpp-runtime intel-oneapi-dpcpp-cpp-runtime-dev ', '', d)}"
