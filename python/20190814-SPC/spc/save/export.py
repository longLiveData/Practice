import xlwt

def save(file_name, data):

    # data = {depth:[[x],[y1],[y2],[y3],[y4],[y5],[y6]]}

    # z值列表
    depths_list = [d for d in data.keys()]

    # 创建excel对象
    book = xlwt.Workbook()
                
    for depth in depths_list:
        # 每个z值新建一个表  
        sheet = book.add_sheet('z=' + str(depth), cell_overwrite_ok=True)
        # 写入标题第一行 合并单元格
        sheet.write_merge(0, 0, 1, 6, "No of Particles")
        # 写入标题第二行
        topic = ["Energy(MeV/u)", "Proton", "Energy(MeV/u)", "Helium", "Energy(MeV/u)", 
            "Lithium", "Energy(MeV/u)", "Berylium", "Energy(MeV/u)", "Boron", "Energy(MeV/u)", "Carbon"]
        for index in range(len(topic)):
            sheet.write(1, index, topic[index])
        # 写入数据
        row = 0
        for isotope in data[depth]:
            col = 2
            for d in isotope:
                sheet.write(col, row, d)
                col += 1
            row += 1

    # 保存excel
    book.save(file_name) 

def save_fragments(file_name, data, data2):

    # data = {depth:[[x],[y1],[y2],[y3],[y4],[y5],[y6]]}

    # 创建excel对象
    book = xlwt.Workbook()

    # 新建一个表  
    sheet = book.add_sheet("sheet1", cell_overwrite_ok=True)
    # 写入标题第一行 合并单元格
    sheet.write_merge(0, 0, 1, 6, "No of Particles")
    # 写入标题第二行
    topic = ["z", "Proton", "Helium", "Lithium", "Berylium", "Boron", "Carbon"]
    for index in range(len(topic)):
        sheet.write(1, index, topic[index])
    # 写入数据
    z_list = data[1][0]
    i = 2
    for z in z_list:
        sheet.write(i, 0, z)
        i += 1
    row = 1
    for index in range(1, 7):
        data_array = data[index][1]
        i = 2
        for d in data_array:
            sheet.write(i, row, d)
            i += 1
        row += 1

    # 新数据
    data = data2

    # 新建一个表  
    sheet = book.add_sheet("sheet2", cell_overwrite_ok=True)
    # 写入标题第一行 合并单元格
    sheet.write_merge(0, 0, 1, 6, "No of Particles")
    # 写入标题第二行
    for index in range(len(topic)):
        sheet.write(1, index, topic[index])
    # 写入数据
    z_list = data[1][0]
    i = 2
    for z in z_list:
        sheet.write(i, 0, z)
        i += 1
    row = 1
    for index in range(1, 7):
        data_array = data[index][1]
        i = 2
        for d in data_array:
            sheet.write(i, row, d)
            i += 1
        row += 1

    # 保存excel
    book.save(file_name) 