from struct import *

# 内置转换函数
# 二进制 → unsigned short
def get_ul_value(binary_string):
    return unpack('H', binary_string[0:2])[0]

# 二进制 → char
def get_char_value(binary_string):
    return unpack('c', binary_string[0:1])[0]

# 二进制 → string
def get_string_value(binary_string):
    return binary_string.decode('ascii').split("\x00")[0]

# 二进制 → double
def get_double_value(binary_string):
    return unpack('d', binary_string)[0]

# 二进制 → ull
def get_ull_value(binary_string):
    return unpack('Q', binary_string)[0]

# 二进制 → long
def get_long_value(binary_string):
    return unpack('l', binary_string)[0]