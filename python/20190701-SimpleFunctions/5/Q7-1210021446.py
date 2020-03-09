def conv_Cel_to_Fah(c):
    result = c * (9/5) + 32
    return round(result, 1)

a = {}
for n in range(10, 41):
    a[n] = conv_Cel_to_Fah(n)

print(a)
