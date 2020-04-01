from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.core.decorator.depends import OETestDepends

class LibxcamTest(OERuntimeTestCase):
    yuv_file = 'vtest.yuv'
    soft_test_app_file = 'test-soft-image'
    libxcam_test_app_dir = '/usr/bin/libxcam/'
    libxcam_file_dir = '/tmp/'

    @classmethod
    def tearDownClass(cls):
        cls.tc.target.run("rm %s%s" % (cls.libxcam_file_dir, cls.yuv_file))

    @OEHasPackage(['gstreamer1.0-plugins-base'])
    @OEHasPackage(['gstreamer1.0-plugins-good'])
    @OEHasPackage(['gstreamer1.0-vaapi'])
    @OEHasPackage(['intel-vaapi-driver'])
    def test_libxcam_can_generate_yuv_file_with_gstreamer(self):
        (status, output) = self.target.run('gst-inspect-1.0 vaapi')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

        (status, output) = self.target.run('gst-launch-1.0 -ev videotestsrc num-buffers=60 ! '
                                           'timeoverlay ! filesink location=%s%s' %
                                           (self.libxcam_file_dir, self.yuv_file))
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OEHasPackage(['libxcam'])
    @OEHasPackage(['libxcam-test'])
    @OETestDepends(['libxcam.LibxcamTest.test_libxcam_can_generate_yuv_file_with_gstreamer'])
    def test_libxcam_can_execute_soft_image_sample_app(self):
        (status, output) = self.target.run('%s%s --type remap --input0 %s%s --output soft_out.nv12 --save false' %
                                            (self.libxcam_test_app_dir,
                                            self.soft_test_app_file,
                                            self.libxcam_file_dir,
                                            self.yuv_file))
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
