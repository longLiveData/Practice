def conv_Fah_to_Cel(f):
    result = (f - 32) * (5/9)
    return round(result, 1)

def conv_Cel_to_Fah(c):
    result = c * (9/5) + 32
    return round(result, 1)

def convert_temp(degrees, cnv_type='ftoc'):
    if isinstance(degrees, int) or isinstance(degrees, float):
        if cnv_type == 'ftoc':
            return conv_Fah_to_Cel(degrees)
        elif cnv_type == 'ctof':
            return conv_Cel_to_Fah(degrees)
        else:
            return -1
    else:
        return -2

print(convert_temp(125))
print(convert_temp(92, 'ftoc'))
print(convert_temp('88', 'ftoc'))
print(convert_temp(28, 'ctof'))
print(convert_temp(11, 'bla'))