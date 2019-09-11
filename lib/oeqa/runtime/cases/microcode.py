from oeqa.runtime.case import OERuntimeTestCase
from oeqa.runtime.decorator.package import OEHasPackage
import re

class MicrocodeTest(OERuntimeTestCase):

    def get_revision_from_microcode_string_list(self, microcode_string_list, re_pattern):
        re_compile = re.compile(re_pattern)
        rev_list = []
        for s in microcode_string_list:
            matched_revs = re_compile.findall(s)
            if matched_revs:
                for mr in matched_revs:
                    rev_list.append(int(mr, 16))
        return rev_list

    @OEHasPackage(["iucode-tool"])
    def test_microcode_update(self):
        (status, output) = self.target.run('iucode_tool /lib/firmware/intel-ucode/ -tb -lS | grep rev')
        if status:
            self.skipTest("The iucode_tool detected no microcode for update.")

        selected_microcodes = output.splitlines()
        selected_rev_list = self.get_revision_from_microcode_string_list(selected_microcodes, "rev (\w*)")
        self.assertTrue(selected_rev_list, msg="Could not find any rev from iucode_tool selected microcode.")

        (status, output) = self.target.run('dmesg | grep microcode')
        self.assertEqual(status, 0, msg='status and output: %s and %s' % (status, output))

        updated_microcodes = output.splitlines()
        updated_rev_list = self.get_revision_from_microcode_string_list(updated_microcodes, "revision=(\w*)")
        self.assertTrue(updated_rev_list, msg="Could not find any updated revision from microcode dmesg.")

        for ul in updated_rev_list:
            self.assertTrue(ul in selected_rev_list, msg="Updated revision, %s, not in selected revision list (%s)" %
                                                         (ul, selected_rev_list))
