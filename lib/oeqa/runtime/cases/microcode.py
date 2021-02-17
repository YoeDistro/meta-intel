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
        (status, output) = self.target.run('iucode_tool /lib/firmware/intel-ucode/ -tb -l --scan-system=2 | grep rev')

        selected_microcodes = output.splitlines()
        selected_rev_list = self.get_revision_from_microcode_string_list(selected_microcodes, "rev (\w*)")

        (status, output) = self.target.run("dmesg | grep 'microcode updated early'")

        updated_microcodes = output.splitlines()
        updated_rev_list = self.get_revision_from_microcode_string_list(updated_microcodes, "revision (\w*)")

        for ul in updated_rev_list:
            self.assertTrue(ul in selected_rev_list, msg="Updated revision, %s, not in selected revision list (%s)" %
                                                         (ul, selected_rev_list))
