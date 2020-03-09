my_list = ['lorem', 'ipsum', 'dolor', 'sit', 'ad', 'dolore', 'consectetur', 'adipiscing', 'elit', 'sed', 'aliqua']
dictF = {}

for l in my_list:
    if l[0] in dictF:
        dictF[l[0]].append(l)
    else:
        dictF[l[0]] = [l]

print('dictF:', dictF)