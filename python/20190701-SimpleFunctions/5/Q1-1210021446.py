lsta = ['a', 'b', 'c']
lstb = [5, 12, 10]

dicta = dict()
for key, value in zip(lsta, lstb):
    dicta[key] = value

print(dicta)
