import os
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends

class IsalTest(OERuntimeTestCase):

    @OEHasPackage(['isa-l'])
    def test_isal_igzip_version(self):
        command = 'igzip -V'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)

    @OETestDepends(['isal.IsalTest.test_isal_igzip_version'])
    def test_isal_igzip_can_compress(self):
        command = 'echo "hello" > /tmp/igzip_sample'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)
        command = 'igzip -z /tmp/igzip_sample'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)
        command = 'rm /tmp/igzip_sample*'
        (status, output) = self.target.run(command)
        self.assertEqual(status, 0, msg="Error messages: %s" % output)
