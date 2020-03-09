from read.header import Header
from read.depth import Depth

class FileReader:

    def __init__(self, file_path):
        
        # open file
        file = open(file_path, 'rb')
        file_content = file.read()

        start = 0
        # get header
        self.header = Header(file_content[start:-1])
        depth_number = self.header.get_number_of_depth_positions_value()
        start += self.header.length

        # get depths
        self.depths = []
        for index in range(depth_number):
            depth = Depth(file_content[start:-1])
            self.depths.append(depth)
            start += depth.length

        file.close()

    # 获取depth值字典
    def get_depth_position_value(self):
        depth_position_value = dict()
        for depth in self.depths:
            depth_position_value[depth.get_depth_position_value()] = depth.get_depth_value()

        return depth_position_value