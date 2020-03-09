from read import func

class Isotope:

    def __init__(self, name):
        self.name = name

    def get_data(self, binary_string):

        start = 0

        self.nuclide_species = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.nuclide_species_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.nuclide_charge = func.get_double_value(binary_string[start:start+8])
        start += 8
        self.nuclide_mass = func.get_double_value(binary_string[start:start+8])
        start += 8
        self.protons_number = func.get_long_value(binary_string[start:start+4])
        start += 4
        self.nucleons_number = func.get_long_value(binary_string[start:start+4])
        start += 4

        self.cumulated_species = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.cumulated_species_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.cumulated_species = func.get_double_value(binary_string[start:start+8])
        start += 8

        # print("1", self.cumulated_species)

        self.number_of_lateral_coefficients = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_lateral_coefficients_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.reserved = func.get_ull_value(binary_string[start:start+8])
        start += 8

        self.number_of_energy_bins = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_energy_bins_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_energy_bins_value = func.get_ul_value(binary_string[start:start+8])
        start += 8

        # x轴数据
        self.energy_bin_borders = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.energy_bin_borders_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.data_x = []
        times = self.energy_bin_borders_length / 8 - 1
        
        for i in range(int(times)):
            self.data_x.append(func.get_double_value(binary_string[start:start+8]))
            start += 8
        start += 8

        # y轴数据
        self.spectrum_histogram = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.spectrum_histogram_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.data_y = []
        times = self.spectrum_histogram_length / 8
        for i in range(int(times)):
            self.data_y.append(func.get_double_value(binary_string[start:start+8]))
            start += 8

        self.running_sum_of_histogram = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.running_sum_of_histogram_length = func.get_ul_value(binary_string[start:start+4])
        start += 4

        self.running_sum_of_histogram_value = 0.0
        time = self.running_sum_of_histogram_length / 8 - 1
        for i in range(int(time)):
            self.running_sum_of_histogram_value += func.get_double_value(binary_string[start:start+8])
            start += 8
        # print("2", self.running_sum_of_histogram_value)

        start += 8

        self.length = start

    def get_data_x(self):
        data = self.data_x
        if len(data) != 0:
            energy_bin = data[1] - data[0]
            for i in range(len(data)):
                data[i] += energy_bin
        return data

    def get_data_y(self):
        return self.data_y

    # 直接读取的
    def get_cumulated_species(self):
        return self.cumulated_species

    # 最后一项累加的
    def get_running_sum_of_histogram(self):
        return self.running_sum_of_histogram_value