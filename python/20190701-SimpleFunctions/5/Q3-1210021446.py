lorem = 'lorem ipsum dolor sit ad dolore consectetur adipiscing elit sed aliqua'
lorem2 = lorem.split(sep = ' ')

dictD = {}
for l in sorted(lorem2)[::-1]:
    dictD[l] = len(l)

print(dictD)