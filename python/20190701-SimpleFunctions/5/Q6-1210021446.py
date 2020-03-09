Fahrenheit = 125
Celsius = 22

def conv_Fah_to_Cel(f):
    result = (f - 32) * (5/9)
    return round(result, 1)

def conv_Cel_to_Fah(c):
    result = c * (9/5) + 32
    return round(result, 1)

print(conv_Fah_to_Cel(Fahrenheit))
print(conv_Cel_to_Fah(Celsius))