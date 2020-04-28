from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.core.decorator.depends import OETestDepends
from oeqa.runtime.miutils.targets.oeqatarget import OEQATarget
from oeqa.runtime.miutils.tests.mkl_dnn_test import MkldnnTest

class MklDnn(OERuntimeTestCase):

    @classmethod
    def setUpClass(cls):
        cls.mkldnntest = MkldnnTest(OEQATarget(cls.tc.target))
        
    @classmethod
    def tearDownClass(cls):
        cls.mkldnntest.tear_down()
        
    @OEHasPackage(['onednn', 'libdnnl1'])
    @OEHasPackage(['onednn-src', 'libdnnl-src'])
    @OEHasPackage(['onednn-dev', 'libdnnl-dev'])
    @OEHasPackage(['gcc'])
    @OEHasPackage(['gcc-symlinks'])
    @OEHasPackage(['libstdc++-dev'])
    @OEHasPackage(['binutils'])
    def test_mkldnn_can_compile_and_execute(self):
        (status, output) = self.mkldnntest.test_mkldnn_can_compile_and_execute()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
    
    @OEHasPackage(['onednn', 'libdnnl1'])
    @OEHasPackage(['onednn-test', 'libdnnl-test'])
    def test_mkldnn_benchdnn_package_available(self):
        (status, output) = self.mkldnntest.test_mkldnn_benchdnn_package_available()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['mkl_dnn.MklDnn.test_mkldnn_benchdnn_package_available'])
    def test_mkldnn_conv_api(self):
        (status, output) = self.mkldnntest.test_mkldnn_conv_api()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['mkl_dnn.MklDnn.test_mkldnn_benchdnn_package_available'])
    def test_mkldnn_bnorm_api(self):
        (status, output) = self.mkldnntest.test_mkldnn_bnorm_api()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['mkl_dnn.MklDnn.test_mkldnn_benchdnn_package_available'])
    def test_mkldnn_deconv_api(self):
        (status, output) = self.mkldnntest.test_mkldnn_deconv_api()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['mkl_dnn.MklDnn.test_mkldnn_benchdnn_package_available'])
    def test_mkldnn_ip_api(self):
        (status, output) = self.mkldnntest.test_mkldnn_ip_api()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['mkl_dnn.MklDnn.test_mkldnn_benchdnn_package_available'])
    def test_mkldnn_reorder_api(self):
        (status, output) = self.mkldnntest.test_mkldnn_reorder_api()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['mkl_dnn.MklDnn.test_mkldnn_benchdnn_package_available'])
    def test_mkldnn_rnn_api(self):
        (status, output) = self.mkldnntest.test_mkldnn_rnn_api()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['mkl_dnn.MklDnn.test_mkldnn_benchdnn_package_available'])
    def test_mkldnn_shuffle_api(self):
        (status, output) = self.mkldnntest.test_mkldnn_shuffle_api()
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
