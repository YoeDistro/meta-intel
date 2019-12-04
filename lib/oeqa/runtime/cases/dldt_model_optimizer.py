from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.runtime.miutils.targets.oeqatarget import OEQATarget
from oeqa.runtime.miutils.tests.squeezenet_model_download_test import SqueezenetModelDownloadTest
from oeqa.runtime.miutils.tests.dldt_model_optimizer_test import DldtModelOptimizerTest
from oeqa.runtime.miutils.dldtutils import get_testdata_config

class DldtModelOptimizer(OERuntimeTestCase):

    @classmethod
    def setUpClass(cls):
        cls.sqn_download = SqueezenetModelDownloadTest(OEQATarget(cls.tc.target), '/tmp/mo/md')
        cls.sqn_download.setup()
        cls.dldt_mo = DldtModelOptimizerTest(OEQATarget(cls.tc.target), '/tmp/mo/ir')
        cls.dldt_mo.setup()

    @classmethod
    def tearDownClass(cls):
        cls.dldt_mo.tear_down()
        cls.sqn_download.tear_down()

    @OEHasPackage(['dldt-model-optimizer'])
    @OEHasPackage(['wget'])
    def test_dldt_mo_can_create_ir(self):
        proxy_port = get_testdata_config(self.tc.td, 'DLDT_PIP_PROXY')
        if not proxy_port:
            self.skipTest('Need to configure bitbake configuration (DLDT_PIP_PROXY="proxy.server:port").')
        (status, output) = self.sqn_download.test_can_download_squeezenet_model(proxy_port)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
        (status, output) = self.sqn_download.test_can_download_squeezenet_prototxt(proxy_port)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

        mo_exe_dir = get_testdata_config(self.tc.td, 'DLDT_MO_EXE_DIR')
        if not mo_exe_dir:
            self.skipTest('Need to configure bitbake configuration (DLDT_MO_EXE_DIR="directory_to_mo.py").')
        mo_files_dir = self.sqn_download.work_dir
        (status, output) = self.dldt_mo.test_dldt_mo_can_create_ir(mo_exe_dir, mo_files_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
