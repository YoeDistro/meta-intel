import os

class DldtModelOptimizerTest(object):
    mo_input_files = {'model': 'squeezenet_v1.1.caffemodel',
                      'prototxt': 'deploy.prototxt'}
    mo_exe = 'mo.py'

    def __init__(self, target, work_dir):
        self.target = target
        self.work_dir = work_dir

    def setup(self):
        self.target.run('mkdir -p %s' % self.work_dir)

    def tear_down(self):
        self.target.run('rm -rf %s' % self.work_dir)

    def test_dldt_mo_can_create_ir(self, mo_exe_dir, mo_files_dir):
        return self.target.run('python3 %s --input_model %s --input_proto %s --output_dir %s --data_type FP16' %
                               (os.path.join(mo_exe_dir, self.mo_exe),
                                os.path.join(mo_files_dir, self.mo_input_files['model']),
                                os.path.join(mo_files_dir, self.mo_input_files['prototxt']),
                                self.work_dir))
