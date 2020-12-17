
class MkldnnTest(object):
    mkldnn_target_test_filename = 'mkl-dnn-c'

    def __init__(self, target):
        self.target = target

    def tear_down(self):
        self.target.run('rm /tmp/%s' % self.mkldnn_target_test_filename)
        
    def test_mkldnn_can_compile_and_execute(self):
        mkldnn_src_dir = '/usr/src/debug/onednn/'
        mkldnn_src_test_filename = 'api.c'
        mkldnn_src_test_file = ''
        
        (__, output) = self.target.run('cd %s; find -name %s' % (mkldnn_src_dir, mkldnn_src_test_filename))
        if 'No such file or directory' in output:
            return -1, output

        mkldnn_src_test_file = os.path.join(mkldnn_src_dir, output)
        (status, output) = self.target.run('gcc %s -o /tmp/%s -ldnnl' % (mkldnn_src_test_file, self.mkldnn_target_test_filename))
        if status:
            return status, output

        (status, output) = self.target.run('cd /tmp; ./%s' % self.mkldnn_target_test_filename)
        return status, output
   
    def test_mkldnn_benchdnn_package_available(self):
        (status, output) = self.target.run('ls /usr/bin/mkl-dnn/tests/benchdnn')
        return status, output
    
    def _run_mkldnn_benchdnn_test(self, cmd):
        (status, output) = self.target.run('cd /usr/bin/mkl-dnn/tests/benchdnn; %s' % cmd)
        return status, output

    def test_mkldnn_conv_api(self):
        return self._run_mkldnn_benchdnn_test('./benchdnn --conv --batch=inputs/conv/test_conv_3d')        

    def test_mkldnn_bnorm_api(self):
        return self._run_mkldnn_benchdnn_test('./benchdnn --bnorm --batch=inputs/bnorm/test_bnorm_regressions')
        
    def test_mkldnn_deconv_api(self):
        return self._run_mkldnn_benchdnn_test('./benchdnn --deconv --batch=inputs/deconv/test_deconv_bfloat16')
        
    def test_mkldnn_ip_api(self):
        return self._run_mkldnn_benchdnn_test('./benchdnn --ip --batch=inputs/ip/test_ip_bfloat16')
        
    def test_mkldnn_reorder_api(self):
        return self._run_mkldnn_benchdnn_test('./benchdnn --reorder --batch=inputs/reorder/test_reorder_bfloat16')
        
    def test_mkldnn_rnn_api(self):
        return self._run_mkldnn_benchdnn_test('./benchdnn --rnn --batch=inputs/rnn/test_rnn_all')
        
    def test_mkldnn_shuffle_api(self):
        return self._run_mkldnn_benchdnn_test('./benchdnn --shuffle --batch=inputs/shuffle/test_shuffle_bfloat16')