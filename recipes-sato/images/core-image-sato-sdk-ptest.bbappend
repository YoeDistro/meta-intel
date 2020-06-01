# zeus builds live hddimg by default which has a hard limit of 4GB.
# Exclude intel-media-driver and intel-mediasdk from default configuration
# to avoid breakages when using default poky configuration.
# These don't enable ptests anyway and are not needed by ones that do.
MACHINE_HWCODECS = " intel-vaapi-driver gstreamer1.0-vaapi"
