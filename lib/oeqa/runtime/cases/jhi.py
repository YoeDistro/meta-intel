import os
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends

class JhiTest(OERuntimeTestCase):

    @classmethod
    def tearDownClass(cls):
        _, output = cls.tc.target.run('pidof jhid')
        cls.tc.target.run('kill %s' % output)

    @OEHasPackage(['openssh-sshd'])
    @OEHasPackage(['jhi'])
    def test_jhi_mei_driver(self):
        command = 'ls /dev/mei*'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)

    @OETestDepends(['jhi.JhiTest.test_jhi_mei_driver'])
    def test_jhi_daemon_version(self):
        command = 'jhid -v'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)

    @OETestDepends(['jhi.JhiTest.test_jhi_mei_driver'])
    def test_jhi_daemon_can_initialized(self):
        command = 'jhid -d'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)

    @OEHasPackage(['jhi-test'])
    @OETestDepends(['jhi.JhiTest.test_jhi_daemon_can_initialized'])
    def test_jhi_bist(self):
        (status, output) = self.target.run('uname -m')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
        if 'x86_64' not in output:
            self.skipTest("Skipped jhi bist test if not x86_64 machine (current machine: %s)." % output)
        command = 'bist'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)
