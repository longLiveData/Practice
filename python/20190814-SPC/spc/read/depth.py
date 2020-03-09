from read import func
from read.isotope import Isotope

class Depth:

    def __init__(self, binary_string):

        isotope_name = {1:"Proton", 2:"Helium", 3:"Lithium", 4:"Berylium", 5:"Boron", 6:"Carbon"}
        self.depth_value = dict()
        self.depth_value["read"] = []
        self.depth_value["calc"] = []

        start = 0

        self.depth_position = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.depth_position_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.depth_position_value = func.get_double_value(binary_string[start:start+8])
        start += 8

        self.number_of_particles = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_particles_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_particles_value = func.get_double_value(binary_string[start:start+8])
        start += 8

        self.number_of_nuclide_species = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_nuclide_species_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_nuclide_species_value = func.get_ull_value(binary_string[start:start+8])
        start += 8

        self.isotope_list = []

        for index in range(1, 1+self.number_of_nuclide_species_value):
            isotope = Isotope(isotope_name[index])
            isotope.get_data(binary_string[start:-1])

            start += isotope.length
            self.isotope_list.append(isotope)
            self.depth_value[index] = [isotope.get_data_x(), isotope.get_data_y()]

            # 添加两个新读取的值:fragments值
            self.depth_value["read"].append(isotope.get_cumulated_species())
            self.depth_value["calc"].append(isotope.get_running_sum_of_histogram())

        self.length = start

    # 返回depth_position_value值
    def get_depth_position_value(self):
        # 保留小数点后两位
        return '%.2f' % self.depth_position_value

    # 返回depth_value值
    def get_depth_value(self):
        cur_value = self.depth_value
        for i in range(2, 6):
            if len(cur_value[i][0]) == 0:
                cur_value[i][0] = cur_value[1][0]
        return cur_value