from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.core.decorator.depends import OETestDepends
from oeqa.runtime.miutils.targets.oeqatarget import OEQATarget
from oeqa.runtime.miutils.tests.squeezenet_model_download_test import SqueezenetModelDownloadTest
from oeqa.runtime.miutils.tests.dldt_model_optimizer_test import DldtModelOptimizerTest
from oeqa.runtime.miutils.tests.dldt_inference_engine_test import DldtInferenceEngineTest
from oeqa.runtime.miutils.dldtutils import get_testdata_config

class DldtInferenceEngine(OERuntimeTestCase):

    @classmethod
    def setUpClass(cls):
        cls.sqn_download = SqueezenetModelDownloadTest(OEQATarget(cls.tc.target), '/tmp/ie/md')
        cls.sqn_download.setup()
        cls.dldt_mo = DldtModelOptimizerTest(OEQATarget(cls.tc.target), '/tmp/ie/ir')
        cls.dldt_mo.setup()
        cls.dldt_ie = DldtInferenceEngineTest(OEQATarget(cls.tc.target), '/tmp/ie/inputs')
        cls.dldt_ie.setup()
        cls.ir_files_dir = cls.dldt_mo.work_dir

    @classmethod
    def tearDownClass(cls):
        cls.dldt_ie.tear_down()
        cls.dldt_mo.tear_down()
        cls.sqn_download.tear_down()

    @OEHasPackage(['dldt-model-optimizer'])
    @OEHasPackage(['wget'])
    def test_dldt_ie_can_create_ir_and_download_input(self):
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

        (status, output) = self.dldt_ie.test_can_download_input_file(proxy_port)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['dldt_inference_engine.DldtInferenceEngine.test_dldt_ie_can_create_ir_and_download_input'])
    @OEHasPackage(['dldt-inference-engine'])
    @OEHasPackage(['dldt-inference-engine-samples'])
    def test_dldt_ie_classification_with_cpu(self):
        (status, output) = self.dldt_ie.test_dldt_ie_classification_with_device('CPU', self.ir_files_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['dldt_inference_engine.DldtInferenceEngine.test_dldt_ie_can_create_ir_and_download_input'])
    @OEHasPackage(['dldt-inference-engine'])
    @OEHasPackage(['dldt-inference-engine-samples'])
    @OEHasPackage(['intel-compute-runtime'])
    @OEHasPackage(['opencl-icd-loader'])
    def test_dldt_ie_classification_with_gpu(self):
        (status, output) = self.dldt_ie.test_dldt_ie_classification_with_device('GPU', self.ir_files_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['dldt_inference_engine.DldtInferenceEngine.test_dldt_ie_can_create_ir_and_download_input'])
    @OEHasPackage(['dldt-inference-engine'])
    @OEHasPackage(['dldt-inference-engine-samples'])
    @OEHasPackage(['dldt-inference-engine-vpu-firmware'])
    def test_dldt_ie_classification_with_myriad(self):
        device = 'MYRIAD'
        (status, output) = self.dldt_ie.test_check_if_openvino_device_available(device)
        if not status:
            self.skipTest('OpenVINO %s device not available on target machine(availalbe devices: %s)' % (device, output))
        (status, output) = self.dldt_ie.test_dldt_ie_classification_with_device(device, self.ir_files_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['dldt_inference_engine.DldtInferenceEngine.test_dldt_ie_can_create_ir_and_download_input'])
    @OEHasPackage(['dldt-inference-engine'])
    @OEHasPackage(['dldt-inference-engine-python3'])
    @OEHasPackage(['python3-opencv'])
    @OEHasPackage(['python3-numpy'])
    def test_dldt_ie_classification_python_api_with_cpu(self):
        (status, output) = self.dldt_ie.test_dldt_ie_classification_python_api_with_device('CPU', self.ir_files_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['dldt_inference_engine.DldtInferenceEngine.test_dldt_ie_can_create_ir_and_download_input'])
    @OEHasPackage(['dldt-inference-engine'])
    @OEHasPackage(['dldt-inference-engine-python3'])
    @OEHasPackage(['intel-compute-runtime'])
    @OEHasPackage(['opencl-icd-loader'])
    @OEHasPackage(['python3-opencv'])
    @OEHasPackage(['python3-numpy'])
    def test_dldt_ie_classification_python_api_with_gpu(self):
        (status, output) = self.dldt_ie.test_dldt_ie_classification_python_api_with_device('GPU', self.ir_files_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['dldt_inference_engine.DldtInferenceEngine.test_dldt_ie_can_create_ir_and_download_input'])
    @OEHasPackage(['dldt-inference-engine'])
    @OEHasPackage(['dldt-inference-engine-python3'])
    @OEHasPackage(['dldt-inference-engine-vpu-firmware'])
    @OEHasPackage(['python3-opencv'])
    @OEHasPackage(['python3-numpy'])
    def test_dldt_ie_classification_python_api_with_myriad(self):
        device = 'MYRIAD'
        (status, output) = self.dldt_ie.test_check_if_openvino_device_available(device)
        if not status:
            self.skipTest('OpenVINO %s device not available on target machine(availalbe devices: %s)' % (device, output))
        (status, output) = self.dldt_ie.test_dldt_ie_classification_python_api_with_device(device, self.ir_files_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
