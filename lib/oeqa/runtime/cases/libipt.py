from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.core.decorator.depends import OETestDepends

class LibiptTest(OERuntimeTestCase):
    libipt_bin_dir = '/usr/bin/libipt/'

    @classmethod
    def tearDownClass(cls):
        cls.tc.target.run('rm /tmp/loop-tnt*')

    @OEHasPackage(['libipt', 'libipt2'])
    @OEHasPackage(['libipt-test'])
    @OEHasPackage(['yasm'])
    def test_libipt_can_generate_trace_packet(self):
        (status, output) = self.target.run('cd /tmp; %spttc %s/tests/loop-tnt.ptt' % 
                                           (self.libipt_bin_dir, self.libipt_bin_dir))
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OETestDepends(['libipt.LibiptTest.test_libipt_can_generate_trace_packet'])
    def test_libipt_can_perform_trace_packet_dump(self):
        (status, output) = self.target.run('cd /tmp; %sptdump loop-tnt.pt' % self.libipt_bin_dir)
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
