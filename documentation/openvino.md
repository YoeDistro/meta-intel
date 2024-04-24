Build a Yocto Image with OpenVINO™ toolkit
==========================================

Follow the [Yocto Project official documentation](https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html#compatible-linux-distribution) to set up and configure your host machine to be compatible with BitBake.

## Step 1: Set Up Environment

1. Clone the repositories.

```
      git clone https://git.yoctoproject.org/git/poky
      git clone https://github.com/openembedded/meta-openembedded
      git clone https://git.yoctoproject.org/git/meta-intel
```


2. Set up the OpenEmbedded build environment.

```
      source poky/oe-init-build-env

```



3. Add BitBake layers.


```
      bitbake-layers add-layer ../meta-openembedded/meta-oe
      bitbake-layers add-layer ../meta-openembedded/meta-python
      bitbake-layers add-layer ../meta-intel

```


4. Set up BitBake configurations.
   Include extra configuration in the `conf/local.conf` file in your build directory as required.


```
      MACHINE = "intel-skylake-64"

      # Enable building OpenVINO Python API.
      # This requires meta-python layer to be included in bblayers.conf.
      PACKAGECONFIG:append:pn-openvino-inference-engine = " python3"

      # This adds OpenVINO related libraries in the target image.
      CORE_IMAGE_EXTRA_INSTALL:append = " openvino-inference-engine"

      # This adds OpenVINO samples in the target image.
      CORE_IMAGE_EXTRA_INSTALL:append = " openvino-inference-engine-samples"

      # Include OpenVINO Python API package in the target image.
      CORE_IMAGE_EXTRA_INSTALL:append = " openvino-inference-engine-python3"

      # Include model conversion API in the target image.
      CORE_IMAGE_EXTRA_INSTALL:append = " openvino-model-optimizer"

```

## Step 2: Build a Yocto Image with OpenVINO Packages

Run BitBake to build your image with OpenVINO packages. For example, to build the minimal image, run the following command:


```
   bitbake core-image-minimal

```

## Step 3: Verify the Yocto Image

Verify that OpenVINO packages were built successfully. Run the following command:

```
   oe-pkgdata-util list-pkgs | grep openvino

```


If the image build is successful, it will return the list of packages as below:

```
   openvino-inference-engine
   openvino-inference-engine-dbg
   openvino-inference-engine-dev
   openvino-inference-engine-python3
   openvino-inference-engine-samples
   openvino-inference-engine-src
   openvino-model-optimizer
   openvino-model-optimizer-dbg
   openvino-model-optimizer-dev

```
