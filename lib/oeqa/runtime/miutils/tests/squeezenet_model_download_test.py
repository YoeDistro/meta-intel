class SqueezenetModelDownloadTest(object):
    download_files = {'squeezenet1.1.prototxt': 'https://raw.githubusercontent.com/DeepScale/SqueezeNet/a47b6f13d30985279789d08053d37013d67d131b/SqueezeNet_v1.1/deploy.prototxt',
                      'squeezenet1.1.caffemodel': 'https://github.com/DeepScale/SqueezeNet/raw/a47b6f13d30985279789d08053d37013d67d131b/SqueezeNet_v1.1/squeezenet_v1.1.caffemodel'}

    def __init__(self, target, work_dir):
        self.target = target
        self.work_dir = work_dir

    def setup(self):
        self.target.run('mkdir -p %s' % self.work_dir)

    def tear_down(self):
        self.target.run('rm -rf %s' % self.work_dir)

    def test_can_download_squeezenet_model(self, proxy_port):
        return self.target.run('cd %s; wget %s -e https_proxy=%s' %
                               (self.work_dir,
                                self.download_files['squeezenet1.1.caffemodel'],
                                proxy_port))

    def test_can_download_squeezenet_prototxt(self, proxy_port):
        return self.target.run('cd %s; wget %s -e https_proxy=%s' %
                               (self.work_dir,
                                self.download_files['squeezenet1.1.prototxt'],
                                proxy_port))
