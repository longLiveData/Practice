import xlwt
from datetime import datetime

wb = xlrd.open_workbook('record.xls')
ws = wb.add_sheet('Test')

# 先写表头
ws.write(0, 0, '性别' )
ws.write(0, 1, '政治面貌')
index = 2
for i in range(40):
    ws.write(0, index, '词'+str(i+1))
    index += 1
    ws.write(0, index, '正误')
    index += 1
    ws.write(0, index, '时长')
    index += 1

ws.write(1, 0, sex)
ws.write(1, 1, poli)
index = 2
for i in range(40):
    ws.write(1, index, words[i])
    index += 1
    ws.write(1, index, corr[i])
    index += 1
    ws.write(1, index, time[i])
    index += 1
 
wb.save('record.xls')