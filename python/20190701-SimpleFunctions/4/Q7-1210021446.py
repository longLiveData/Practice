historical = 0, 6, 2, 8, 6, 2, 0, 8, 9, 9
predictiona = 8, 6, 2, 8, 0, 3, 4, 8, 2, 5
predictionb = 3, 4, 2, 1, 1, 7, 0, 6, 7, 9

print('')
print('historical:', historical)
print('predictiona:', predictiona)
print('predictionb:', predictionb)
print('')

for i in range(len(historical)):
    print('historical:', historical[i], 'predictiona:', predictiona[i],'predictionb:', predictionb[i])