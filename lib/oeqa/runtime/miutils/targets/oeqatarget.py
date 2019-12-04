
class OEQATarget(object):

    def __init__(self, target):
        self.target = target

    def run(self, cmd):
        return self.target.run(cmd)

    def copy_to(self, source, destination_dir):
        self.target.copyTo(source, destination_dir)
