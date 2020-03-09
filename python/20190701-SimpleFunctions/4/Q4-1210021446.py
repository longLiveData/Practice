sum5 = 0
sum2 = 0

for i in range(0,101):
    if i % 5 == 0:
        sum5 += i
    if i % 2 == 0:
        sum2 += i

print('sum2', sum2, 'divided by sum5', sum5, 'is', sum2 / sum5)