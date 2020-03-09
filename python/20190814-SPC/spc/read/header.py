from read import func

class Header:

    def __init__(self, binary_string):

        start = 0

        self.file_type = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.file_type_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.file_type_endian = func.get_string_value(binary_string[start:start+80])
        start += 80

        self.file_version = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.file_version_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.file_version_time = func.get_string_value(binary_string[start:start+80])
        start += 80

        self.file_version2 = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.file_version2_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.file_version2_time = func.get_string_value(binary_string[start:start+80])
        start += 80

        self.target_name = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.target_name_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.target_name_string = func.get_string_value(binary_string[start:start+self.target_name_length])
        start += self.target_name_length

        self.projectile_name = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.projectile_name_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.projectile_name_string = func.get_string_value(binary_string[start:start+self.projectile_name_length])
        start += self.projectile_name_length

        self.initial_beam_energy = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.initial_beam_energy_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.initial_beam_energy_value = func.get_double_value(binary_string[start:start+8])
        start += 8

        self.bragg_peak_position = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.bragg_peak_position_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.bragg_peak_position_value = func.get_double_value(binary_string[start:start+8])
        start += 8

        self.number_of_particles = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_particles_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_particles_value = func.get_double_value(binary_string[start:start+8])
        start += 8

        self.number_of_depth_positions = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_depth_positions_length = func.get_ul_value(binary_string[start:start+4])
        start += 4
        self.number_of_depth_positions_value = func.get_ull_value(binary_string[start:start+8])
        start += 8

        self.length = start

    def get_number_of_depth_positions_value(self):
        return self.number_of_depth_positions_value

    def get_header_info(self):
        header_info = []
        header_info.append(self.file_type_endian)
        header_info.append(self.file_version_time)
        header_info.append(self.file_version2_time)
        header_info.append(self.target_name_string)
        header_info.append(self.projectile_name_string)
        header_info.append(str(self.initial_beam_energy_value) + " MeV/u")
        header_info.append(str(self.bragg_peak_position_value) + " cm in water")
        header_info.append(self.number_of_particles_value)
        header_info.append(self.number_of_depth_positions_value)
        
        return header_info

