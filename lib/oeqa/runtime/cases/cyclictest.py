from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends
from oeqa.runtime.decorator.package import OEHasPackage
import os
import subprocess
import datetime

class CyclicTest(OERuntimeTestCase):

    @OEHasPackage(['rt-tests'])
    @OETestDepends(['ssh.SSHTest.test_ssh'])
    def test_cyclic(self):
        # Cyclictest command and argument based on public setup for Intel(R) Core(TM) i7-6700
        # https://www.osadl.org/Latency-plot-of-system-in-rack-9-slot.qa-latencyplot-r9s5.0.html?shadow=1
        # Command line: cyclictest -l100000000 -m -Sp99 -i200 -h400 -q
        status, output = self.target.run('cyclictest -l100000000 -m -Sp99 -i200 -h400')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))
        test_log_dir = self.td.get('TEST_LOG_DIR', '')

        if not test_log_dir:
            test_log_dir = os.path.join(self.td.get('WORKDIR', ''), 'testimage')

        cyclic_log_dir = os.path.join(test_log_dir, '%s.%s' % ('cyclic_test', datetime.datetime.now().strftime('%Y%m%d%H%M%S')))
        os.makedirs(cyclic_log_dir)
        log_path = os.path.join(cyclic_log_dir, 'cyclic_log')
        with open(log_path, 'w') as f:
            f.write(output)

        max_latency = subprocess.check_output(('grep "Max Latencies" %s | tr " " "\n" | sort -n | tail -1 | sed s/^0*//') % log_path, shell=True).strip()
        max_latency = int(max_latency)
        
        # Default target latency based on max latency (24us) captured at public execution multiple by 1.2 (20% buffer) 
        # https://www.osadl.org/Latency-plot-of-system-in-rack-9-slot.qa-latencyplot-r9s5.0.html?shadow=1
        target_latency = 1.2*24
        user_defined_target_latency = self.tc.td.get("RTKERNEL_TARGET_LATENCY")
        if user_defined_target_latency:
            target_latency = int(user_defined_target_latency)
        self.assertTrue(max_latency < target_latency,
                        msg="Max latency (%sus) is greater than target (%sus)." % (max_latency, target_latency))
