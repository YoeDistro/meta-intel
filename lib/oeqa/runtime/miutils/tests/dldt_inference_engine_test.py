import os
script_path = os.path.dirname(os.path.realpath(__file__))
files_path = os.path.join(script_path, '../../files/')

class DldtInferenceEngineTest(object):
    ie_input_files = {'ie_python_sample': 'classification_sample.py',
                      'input': 'chicky_512.png',
                      'input_download': 'https://raw.githubusercontent.com/opencv/opencv/master/samples/data/chicky_512.png',
                      'model': 'squeezenet_v1.1.xml'}

    def __init__(self, target, work_dir):
        self.target = target
        self.work_dir = work_dir

    def setup(self):
        self.target.run('mkdir -p %s' % self.work_dir)
        self.target.copy_to(os.path.join(files_path, 'dldt-inference-engine', self.ie_input_files['ie_python_sample']),
                            self.work_dir)
        python_cmd = 'from openvino.inference_engine import IENetwork, IECore; ie = IECore(); print(ie.available_devices)'
        __, output = self.target.run('python3 -c "%s"' % python_cmd)
        self.available_devices = output

    def tear_down(self):
        self.target.run('rm -rf %s' % self.work_dir)

    def test_check_if_openvino_device_available(self, device):
        if device not in self.available_devices:
            return False, self.available_devices
        return True, self.available_devices

    def test_can_download_input_file(self, proxy_port):
        return self.target.run('cd %s; wget %s -e https_proxy=%s' %
                               (self.work_dir,
                                self.ie_input_files['input_download'],
                                proxy_port))

    def test_dldt_ie_classification_with_device(self, device, ir_files_dir):
        return self.target.run('classification_sample_async -d %s -i %s -m %s' %
                               (device,
                                os.path.join(self.work_dir, self.ie_input_files['input']),
                                os.path.join(ir_files_dir, self.ie_input_files['model'])))

    def test_dldt_ie_classification_python_api_with_device(self, device, ir_files_dir, extension=''):
        if extension:
            return self.target.run('python3 %s -d %s -i %s -m %s -l %s' %
                                   (os.path.join(self.work_dir, self.ie_input_files['ie_python_sample']),
                                    device,
                                    os.path.join(self.work_dir, self.ie_input_files['input']),
                                    os.path.join(ir_files_dir, self.ie_input_files['model']),
                                    extension))
        else:
            return self.target.run('python3 %s -d %s -i %s -m %s' %
                                   (os.path.join(self.work_dir, self.ie_input_files['ie_python_sample']),
                                    device,
                                    os.path.join(self.work_dir, self.ie_input_files['input']),
                                    os.path.join(ir_files_dir, self.ie_input_files['model'])))
