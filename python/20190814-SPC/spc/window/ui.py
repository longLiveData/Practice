import tkinter as tk
import tkinter.ttk
from tkinter import filedialog
from read import readfile
from save import export
import tkinter.messagebox


class Window:

    def __init__(self):

        # 整体数据
        self.spc = None
        self.header_data = None
        self.depths = []
        
        # 具体数据
        self.radio_energy_array = []
        self.radio_energy_dict = dict()
        self.header_info_data = []
        self.depth_value = 0.00
        self.x_max_dict = dict()
        self.y_max_dict = dict()
        self.fragments_data_read = dict()
        self.fragments_data_read_m = []
        self.fragments_data_calc = dict()
        
        # 主体部分
        self.window = tk.Tk()
        self.window.title("Fragment spectrum")
        # 主体窗口尺寸
        self.window.geometry('1000x600')

        # 标签栏按钮-open
        self.open_file_button = tk.Button(self.window, text="Open", command=self.open_file)
        self.open_file_button.place(x=0, y=0, anchor="nw")
        # 标签栏按钮-export
        self.export_file_button = tk.Button(self.window, text="Export", command=self.export_data, state="disabled")
        self.export_file_button.place(x=45, y=0, anchor="nw")
        # 标签栏按钮-about
        self.about_button = tk.Button(self.window, text="About", command=self.about_click)
        self.about_button.place(x=95, y=0, anchor="nw")
        # 标签栏信息-路径标签
        self.file_label = tk.Label(self.window)
        self.file_label.place(x=215, y=3, anchor="nw")


        # 左上角Display部分
        # 两个可选项
        self.v1 = tk.IntVar()
        self.radio_frag = tk.Radiobutton(self.window, text='Fragments           ', 
            variable=self.v1, value=0, command=self.turn_fragment)
        self.radio_frag.place(x=50, y=50, anchor='nw')
        self.radio_energy = tk.Radiobutton(self.window, text='Energies at depth', 
            variable=self.v1, value=1, command=self.turn_energy)
        self.radio_energy.place(x=50, y=80, anchor='nw')
        # 下拉菜单框
        self.radio_energy_value = tk.ttk.Combobox(self.window, width=10)
        self.radio_energy_value.place(x=60, y=110, anchor='nw')
        self.radio_energy_value.bind("<<ComboboxSelected>>", self.change_depth)
        # cm标签
        self.radio_energy_text = tk.Label(self.window, text="cm")
        self.radio_energy_text.place(x=180, y=110, anchor='nw')

        # 左边选择6个粒子部分
        self.cb1 = tk.IntVar()
        self.c1 = tk.Checkbutton(self.window, text='Z=6 A=12 Q=6',bg="red", 
            variable=self.cb1, onvalue=1, offvalue=0, command=self.paint6)
        self.c1.place(x=50, y=190, anchor='nw')
        self.cb2 = tk.IntVar()
        self.c2 = tk.Checkbutton(self.window, text='Z=5 A=10 Q=5',bg="green", 
            variable=self.cb2, onvalue=1, offvalue=0, command=self.paint5)
        self.c2.place(x=50, y=220, anchor='nw')
        self.cb3 = tk.IntVar()
        self.c3 = tk.Checkbutton(self.window, text='Z=4 A=7  Q=4',bg="blue", 
            variable=self.cb3, onvalue=1, offvalue=0, command=self.paint4)
        self.c3.place(x=50, y=250, anchor='nw')
        self.cb4 = tk.IntVar()
        self.c4 = tk.Checkbutton(self.window, text='Z=3 A=6  Q=3',bg="orange", 
            variable=self.cb4, onvalue=1, offvalue=0, command=self.paint3)
        self.c4.place(x=50, y=280, anchor='nw')
        self.cb5 = tk.IntVar()
        self.c5 = tk.Checkbutton(self.window, text='Z=2 A=4  Q=2',bg="yellow", 
            variable=self.cb5, onvalue=1, offvalue=0, command=self.paint2)
        self.c5.place(x=50, y=310, anchor='nw')
        self.cb6 = tk.IntVar()
        self.c6 = tk.Checkbutton(self.window, text='Z=1 A=2  Q=1',bg="brown", 
            variable=self.cb6, onvalue=1, offvalue=0, command=self.paint1)
        self.c6.place(x=50, y=340, anchor='nw')
        self.clink_all = tk.Button(self.window, text='Select All', command = self.choose_all)
        self.clink_all.place(x=90, y=380, anchor='nw')

        # 右上Header信息显示部分
        self.header = tk.Text(self.window, height=9, width=107, bg="#CCCCCC")
        self.header.place(x=215, y=33, anchor='nw')

        # 绘图主体部分
        # 绘图区尺寸
        self.canvas_height = 380
        self.canvas_width = 750
        self.canvas = tk.Canvas(self.window, bg='#CCCCCC', height=self.canvas_height, width=self.canvas_width)
        self.canvas.place(x=215, y=180, anchor='nw') # 绘图区左上角
        # 两个坐标点值
        self.min_coordinate = tk.Label(self.window, text="(0,0)")
        self.min_coordinate.place(x=215, y=565, anchor='nw') # 坐标原点位置
        self.max_coordinate = tk.Label(self.window, text="")
        self.max_coordinate.place(x=800, y=155, anchor='nw') # 坐标最大点位置
        # 两个坐标名称
        self.x_coordinate_name = tk.Label(self.window, text="Number of particles")
        self.x_coordinate_name.place(x=215, y=155, anchor='nw') # 纵坐标名称位置
        self.y_coordinate_name = tk.Label(self.window, text="Depth z (cm)")
        self.y_coordinate_name.place(x=900, y=565, anchor='nw') # 横坐标名称位置

        # 窗口循环
        self.window.mainloop()


    # open 打开文件
    def open_file(self):
        # 打开选择文件界面
        file_name = filedialog.askopenfilename(filetypes=[("SPC",".spc")])
        # 界面显示文件名
        self.file_label['text'] = file_name 
        # 加载文件数据
        self.load_data(file_name)
        # 导出按钮可以点击
        self.export_file_button['state'] = 'normal'

    # 读取文件中的数据并加载到spc类中 循环显示
    def load_data(self, file_name):
        # 获取spc文件数据
        self.spc = readfile.FileReader(file_name)
        # 获取header
        self.header_data = self.spc.header
        # 获取depths数据
        self.depths = self.spc.depths

        # 读取数据
        # 读取header数据
        self.header_info_data = self.get_header_info()
        # 加载header值到界面
        self.set_header_info(self.header_info_data)
        # 读取radio_energy数据
        self.radio_energy_array = self.get_depth_position_value_list()
        # 加载数据到左上角
        self.set_radio_energy_value()
        # 读取 depths 数据
        self.radio_energy_dict = self.get_depth_position_value_dict()
        self.scale_radio_energy_dict = self.data_scale_transformation()
        # 获取fragments数据
        self.fragments_data_read = self.get_fragments_data_read()
        self.fragments_data_calc = self.get_fragments_data_calc()

    # 全选按钮触发函数
    def choose_all(self):
        self.c1.select()
        self.c2.select()
        self.c3.select()
        self.c4.select()
        self.c5.select()
        self.c6.select()

        self.paint1()
        self.paint2()
        self.paint3()
        self.paint4()
        self.paint5()
        self.paint6()

    # 获取header数据
    def get_header_info(self):
        header_info = self.header_data.get_header_info()
        res = ""
        res += "File type:" + header_info[0] + "\n"
        res += "File version:" + header_info[1] + "\n"
        res += "File date:" + header_info[2] + "\n" 
        res += "Target material:" + header_info[3] + "\n"
        res += "Projectile:" + header_info[4] + "\n"
        res += "Initial energy:" + header_info[5] + "\n"
        res += "Bragg peak position:" + header_info[6] + "\n"
        res += "No of incident particles:" + str(header_info[7]) + "\n"
        res += "No of depths:" + str(header_info[8]) + "\n"
        return res

    # 获取depth_energy列表值 用来左上角显示
    def get_depth_position_value_list(self):
        return [i for i in self.spc.get_depth_position_value().keys()]

    # 获取depth_energy字典值 用来绘图
    def get_depth_position_value_dict(self):
        return self.spc.get_depth_position_value()
    
    # 获取fragments值:第一种方式 read
    def get_fragments_data_read(self):
        depth_list = self.radio_energy_array
        depth_y = [[],[],[],[],[],[],[]]
        for depth in depth_list:
            isolate_data = self.radio_energy_dict[depth]
            read_data = isolate_data["read"]
            for index in range(1, 7):
                depth_y[index].append(read_data[index-1])
        res = dict()
        for i in range(1, 7):
            res[i] = [[float(i) for i in depth_list], depth_y[i]]


        # 找到最大值和最小值
        max_x, max_y = 0, 0
        for i in range(1, 7):
            max_array_x, max_array_y = float(max(res[i][0])), float(max(res[i][1]))
            max_x = max(max_x, max_array_x)
            max_y = max(max_y, max_array_y)

        self.fragments_data_read_m = [max_x, max_y]
        return res

    # 获取fragments值： 第二种方式,calc
    def get_fragments_data_calc(self):
        depth_list = self.radio_energy_array
        depth_y = [[],[],[],[],[],[],[]]
        for depth in depth_list:
            isolate_data = self.radio_energy_dict[depth]
            read_data = isolate_data["calc"]
            for index in range(1, 7):
                depth_y[index].append(read_data[index-1])
        res = dict()
        for i in range(1, 7):
            res[i] = [[float(i) for i in depth_list], depth_y[i]]
        
        return res

    # 设置左上角
    def set_radio_energy_value(self):
        # 设置下拉菜单里面的值
        self.radio_energy_value["values"] = self.radio_energy_array
        # 设置初始值0
        self.radio_energy_value.current(0)
        # 设为只读
        self.radio_energy_value["state"] = "readonly"

    # 设置header的值
    def set_header_info(self, info_string):
        self.header.config(state="normal")  # 设显示值设为可编辑
        self.header.delete('1.0','end')
        self.header.insert("insert", info_string) # 将值显示在界面
        self.header.config(state="disabled") # 设显示值为不可编辑


    # 点击哪个画哪个
    def paint6(self):
        # 选中需要画图
        if self.cb1.get() == 1:
            # 需要绘制energy图
            if self.v1.get() == 1:
                depth_value = self.radio_energy_value.get()
                self.paint_isotopic(depth_value, 6, 't6')
            # 需要绘制fragment图
            else:
                self.paint_fragment(self.fragments_data_read[6], 1, 't6')

        # 没有选中需要删图
        else:
            self.canvas.delete('t6')

    def paint5(self):
        # 选中的话就删除 否则就新建
        if self.cb2.get() == 1:
            # 需要绘制energy图
            if self.v1.get() == 1:
                depth_value = self.radio_energy_value.get()
                self.paint_isotopic(depth_value, 5, 't5')
            # 需要绘制fragment图
            else:
                self.paint_fragment(self.fragments_data_read[5], 2, 't5')
        else:
            self.canvas.delete('t5')

    def paint4(self):
        # 选中的话就删除 否则就新建
        if self.cb3.get() == 1:
            # 需要绘制energy图
            if self.v1.get() == 1:
                depth_value = self.radio_energy_value.get()
                self.paint_isotopic(depth_value, 4, 't4')
            # 需要绘制fragment图
            else:
                self.paint_fragment(self.fragments_data_read[4], 3, 't4')
        else:
            self.canvas.delete('t4')

    def paint3(self):
        # 选中的话就删除 否则就新建
        if self.cb4.get() == 1:
            # 需要绘制energy图
            if self.v1.get() == 1:
                depth_value = self.radio_energy_value.get()
                self.paint_isotopic(depth_value, 3, 't3')
            # 需要绘制fragment图
            else:
                self.paint_fragment(self.fragments_data_read[3], 4, 't3')
        else:
            self.canvas.delete('t3')

    def paint2(self):
        # 选中的话就删除 否则就新建
        if self.cb5.get() == 1:
            # 需要绘制energy图
            if self.v1.get() == 1:
                depth_value = self.radio_energy_value.get()
                self.paint_isotopic(depth_value, 2, 't2')
            # 需要绘制fragment图
            else:
                self.paint_fragment(self.fragments_data_read[2], 5, 't2')
        else:
            self.canvas.delete('t2')

    def paint1(self):
        # 选中的话就删除 否则就新建
        if self.cb6.get() == 1:
            # 需要绘制energy图
            if self.v1.get() == 1:
                depth_value = self.radio_energy_value.get()
                self.paint_isotopic(depth_value, 1, 't1')
            # 需要绘制fragment图
            else:
                self.paint_fragment(self.fragments_data_read[1], 6, 't1')
        else:
            self.canvas.delete('t1')

    # 绘制fragments图
    def paint_fragment(self, fragment_data, color, tag):
        data = self.scale_fragment_data(fragment_data)
        color_dict = {1:"red", 2:"green", 3:"blue", 4:"orange", 5:"yellow", 6:"brown"}
        for i in range(len(data)-1):
            self.canvas.create_line(data[i][0], data[i][1], data[i+1][0], data[i+1][1], 
                fill=color_dict[color], width=1, tag=(tag))

    # 绘制粒子图像
    def paint_isotopic(self, depth_value, isotopic_num, tag):
        data = self.scale_radio_energy_dict[depth_value][isotopic_num]
        color_dict = {1:"red", 2:"green", 3:"blue", 4:"orange", 5:"yellow", 6:"brown"}
        for i in range(len(data)-1):
            self.canvas.create_line(data[i][0], data[i][1], data[i+1][0], data[i+1][1], 
                fill=color_dict[isotopic_num], width=1, tag=(tag))

    # 转换全部的数据
    def data_scale_transformation(self):
        res = dict()
        depth_list = [i for i in self.radio_energy_dict.keys()]
        for depth in depth_list:
            res[depth], self.x_max_dict[depth], self.y_max_dict[depth] = self.scale_depth_data(self.radio_energy_dict[depth])
        return res

    # 转换某组数据
    def scale_depth_data(self, depth_data):
        isotope_list = [1, 2, 3, 4, 5, 6]

        # 获取该组depth最大值
        x_max, y_max = 0, 0
        for isotope in isotope_list:
            old_data = depth_data[isotope]
            x_max, y_max = max(x_max, max(old_data[0])), max(y_max, max(old_data[1]))

        # 获取值
        depth_value = dict()
        for isotope in isotope_list:
            data = depth_data[isotope]
            isotope_list = []
            # if x_max != 0 and y_max != 0:
            for d in zip(data[0], data[1]):
                x_new = d[0] / x_max * float(self.canvas_width)
                y_new = self.canvas_height - d[1] / y_max * float(self.canvas_height)
                isotope_list.append([x_new, y_new])
            depth_value[isotope] = isotope_list
        return depth_value, x_max, y_max

    def scale_fragment_data(self, data):
        x_max, y_max = self.fragments_data_read_m
        res = []
        for d in zip(data[0], data[1]):
            x_new = float(d[0]) / float(x_max) * float(self.canvas_width)
            y_new = self.canvas_height - d[1] / y_max * float(self.canvas_height)
            res.append([x_new, y_new])
        return res

    # combobox点击事件
    def change_depth(self, *args):
        # 清除画布 
        self.canvas.delete('t1')
        self.canvas.delete('t2')
        self.canvas.delete('t3')
        self.canvas.delete('t4')
        self.canvas.delete('t5')
        self.canvas.delete('t6')
        # 最大值标签
        depth_value = self.radio_energy_value.get()
        x_max = self.x_max_dict[depth_value]
        y_max = self.y_max_dict[depth_value]
        self.max_coordinate['text'] = "(" + str(x_max) + "," + str(y_max) + ")"
        # 重设radio
        self.c1.deselect()
        self.c2.deselect()
        self.c3.deselect()
        self.c4.deselect()
        self.c5.deselect()
        self.c6.deselect()

    def turn_fragment(self, *args):
        # 清除画布 
        self.canvas.delete('t1')
        self.canvas.delete('t2')
        self.canvas.delete('t3')
        self.canvas.delete('t4')
        self.canvas.delete('t5')
        self.canvas.delete('t6')
        # 重设radio
        self.c1.deselect()
        self.c2.deselect()
        self.c3.deselect()
        self.c4.deselect()
        self.c5.deselect()
        self.c6.deselect()
        # 重设标签
        depth_value = self.radio_energy_value.get()
        x_max = self.x_max_dict[depth_value]
        y_max = self.y_max_dict[depth_value]
        self.max_coordinate['text'] = "(" + str(self.fragments_data_read_m[0]) + "," + str(self.fragments_data_read_m[1]) + ")"
        self.x_coordinate_name['text'] = "Number of particles"
        self.y_coordinate_name['text'] = "Depth z"
        self.radio_energy_value.config(state="disabled")

    def turn_energy(self, *args):
        # 清除画布 
        self.canvas.delete('t1')
        self.canvas.delete('t2')
        self.canvas.delete('t3')
        self.canvas.delete('t4')
        self.canvas.delete('t5')
        self.canvas.delete('t6')
        # 重设radio
        self.c1.deselect()
        self.c2.deselect()
        self.c3.deselect()
        self.c4.deselect()
        self.c5.deselect()
        self.c6.deselect()
        # 重设标签
        depth_value = self.radio_energy_value.get()
        x_max = self.x_max_dict[depth_value]
        y_max = self.y_max_dict[depth_value]
        self.max_coordinate['text'] = ""
        self.x_coordinate_name['text'] = "Number of particles"
        self.y_coordinate_name['text'] = "Energy (MeV)"
        self.radio_energy_value.config(state="normal")
        # 最大值标签
        depth_value = self.radio_energy_value.get()
        x_max = self.x_max_dict[depth_value]
        y_max = self.y_max_dict[depth_value]
        self.max_coordinate['text'] = "(" + str(x_max) + "," + str(y_max) + ")"

    # 导出功能
    def export_data(self):
        # 获取保存文件路径
        save_file = filedialog.asksaveasfilename(filetypes=[("excel",".xls")])
        if save_file.split(".")[-1] != "xls":
            save_file += ".xls"
        
        # 数据转换
        data_to_export = dict()
        for depth in self.radio_energy_array:
            main_value = []
            main_value.append(self.radio_energy_dict[depth][1][0])
            main_value.append(self.radio_energy_dict[depth][1][1])
            main_value.append(self.radio_energy_dict[depth][2][0])
            main_value.append(self.radio_energy_dict[depth][2][1])
            main_value.append(self.radio_energy_dict[depth][3][0])
            main_value.append(self.radio_energy_dict[depth][3][1])
            main_value.append(self.radio_energy_dict[depth][4][0])
            main_value.append(self.radio_energy_dict[depth][4][1])
            main_value.append(self.radio_energy_dict[depth][5][0])
            main_value.append(self.radio_energy_dict[depth][5][1])
            main_value.append(self.radio_energy_dict[depth][6][0])
            main_value.append(self.radio_energy_dict[depth][6][1])
            data_to_export[depth] = main_value
        # 保存数据
        save_energydepth_file = save_file.split('.x')[0] + "_EnergyAtDepth.xls"
        export.save(save_energydepth_file, data_to_export)

        # 保存fragments数据
        save_fragments_file = save_file.split('.x')[0] + "_fragments.xls"
        export.save_fragments(save_fragments_file, self.fragments_data_read, self.fragments_data_calc)

    # about 按钮显示
    def about_click(self):
        tkinter.messagebox.showinfo('V1.0, Medical Physics Department, SPHIC', 'Bugs and feedback: \n\nEmail: yinxiangzi.sheng@sphic.org.cn; \n\nWechat: 18049876663')