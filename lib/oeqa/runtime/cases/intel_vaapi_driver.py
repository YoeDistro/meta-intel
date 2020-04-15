from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.core.decorator.depends import OETestDepends

class VaapiDriverTest(OERuntimeTestCase):

    @classmethod
    def tearDownClass(cls):
        cls.tc.target.run("rm /tmp/vtest_h264.mp4")

    @OEHasPackage(['gstreamer1.0-plugins-base'])
    @OEHasPackage(['gstreamer1.0-plugins-good'])
    @OEHasPackage(['gstreamer1.0-vaapi'])
    @OEHasPackage(['intel-vaapi-driver'])
    def test_gstreamer_can_encode_with_intel_vaapi_driver(self):
        (status, output) = self.target.run('gst-inspect-1.0 vaapi')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

        (status, output) = self.target.run('export LIBVA_DRIVER_NAME=i965; '
                                           'gst-launch-1.0 -ev videotestsrc num-buffers=60 ! '
                                           'timeoverlay ! vaapih264enc ! mp4mux ! filesink location=/tmp/vtest_h264.mp4')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['intel_vaapi_driver.VaapiDriverTest.test_gstreamer_can_encode_with_intel_vaapi_driver'])
    def test_gstreamer_can_decode_with_intel_vaapi_driver(self):
        (status, output) = self.target.run('export LIBVA_DRIVER_NAME=i965; '
                                           'gst-launch-1.0 filesrc location=/tmp/vtest_h264.mp4 ! '
                                           'qtdemux ! h264parse ! vaapih264dec ! vaapisink')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
