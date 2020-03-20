from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.core.decorator.depends import OETestDepends

class MsdkTest(OERuntimeTestCase):

    @classmethod
    def tearDownClass(cls):
        cls.tc.target.run("rm /tmp/mtest_h264.mp4")

    @OEHasPackage(['gstreamer1.0-plugins-base'])
    @OEHasPackage(['gstreamer1.0-plugins-good'])
    @OEHasPackage(['gstreamer1.0-plugins-bad'])
    @OEHasPackage(['intel-mediasdk'])
    @OEHasPackage(['intel-media-driver', 'libigfxcmrt7'])
    def test_gstreamer_can_encode_with_msdk_and_intel_media_driver(self):
        (status, output) = self.target.run('gst-inspect-1.0 msdk')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

        (status, output) = self.target.run('export LIBVA_DRIVER_NAME=iHD; '
                                           'gst-launch-1.0 -ev videotestsrc num-buffers=120 ! timeoverlay ! '
                                           'msdkh264enc ! video/x-h264,profile=main ! h264parse ! '
                                           'filesink location=/tmp/mtest_h264.mp4')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['intel_mediasdk.MsdkTest.test_gstreamer_can_encode_with_msdk_and_intel_media_driver'])
    def test_gstreamer_can_decode_with_msdk_and_intel_media_driver(self):
        (status, output) = self.target.run('export LIBVA_DRIVER_NAME=iHD; '
                                           'gst-launch-1.0 filesrc location=/tmp/mtest_h264.mp4 ! '
                                           'h264parse ! msdkh264dec ! '
                                           'msdkh265enc rate-control=cbr bitrate=5000 gop-size=30 b-frames=2 ! '
                                           'video/x-h265,profile=main ! h265parse ! fakesink')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

