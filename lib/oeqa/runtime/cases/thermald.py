from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends
from oeqa.runtime.decorator.package import OEHasPackage
import threading
import time
import re

class ThermaldTest(OERuntimeTestCase):
    def get_thermal_zone_with_target_type(self, target_type):
        i = 0
        while True:
            status, output = self.target.run('cat /sys/class/thermal/thermal_zone%s/type' % i)
            if status:
                return -1
            if output == target_type:
                return i
            i = i + 1

    def run_thermald_emulation_to_exceed_setpoint_then_end_thermald_process(self, run_args):
        time.sleep(2)
        self.target.run('echo 106000 > /sys/class/thermal/thermal_zone%s/emul_temp' % run_args)
        time.sleep(2)
        __, output = self.target.run('pidof thermald')
        self.target.run('kill -9 %s' % output)

    def test_thermald_emulation_mode(self):
        # Thermald test depend on thermal emulation, where CONFIG_THERMAL_EMULATION=y was required
        # To enable thermal emulation, refer to https://github.com/intel/thermal_daemon/blob/master/test/readme_test.txt
        (status, output) = self.target.run('gzip -dc /proc/config.gz | grep CONFIG_THERMAL_EMULATION=y')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

    @OEHasPackage(['thermald'])
    @OETestDepends(['thermald.ThermaldTest.test_thermald_emulation_mode'])
    def test_thermald_can_track_thermal_exceed_setpoint(self):
        x86_thermal_zone_index = self.get_thermal_zone_with_target_type('x86_pkg_temp')
        if x86_thermal_zone_index < 0:
            self.skipTest('Could not get the thermal zone index for target type (%s)' % 'x86_pkg_temp')
        td_thread = threading.Thread(target=self.run_thermald_emulation_to_exceed_setpoint_then_end_thermald_process,
                                     args=(x86_thermal_zone_index,))
        td_thread.start()
        status, output = self.target.run('thermald --no-daemon --loglevel=info')
        regex_search = ".*thd_cdev_set_state.*106000"
        regex_comp = re.compile(regex_search)
        m = regex_comp.search(output)
        self.assertTrue(m, msg='status and output: %s and %s' % (status, output))
