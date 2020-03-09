from math import sqrt
from math import pi

def basel(accuracy):
    x = 0 # 初始化x x用来表示每次迭代pi的当前值
    n = 0 # 初始化n n表示迭代项数 每迭代一次就加一项
    temp = 0 # temp表示每次迭代后等式右边的值 sqrt(temp*6)得到pi的值
    while(abs(pi - x) > accuracy):  # 每次迭代判断pi的值和当前pi的值误差是否满足条件 
                                    # 若是返回迭代次数和当前值 否则继续迭代
        n += 1 # 每次迭代计数+1
        temp += 1.0/(n*n) # 更新等式右边的值 加一个新项1/(n*n)
        x = sqrt(temp * 6) #计算此时pi的粗略值x
    return x, n # 返回pi的粗略值x和迭代次数n

def taylor(accuracy):
    x = 0 # 初始化x x用来表示每次迭代pi的当前值
    n = 0 # 初始化n n表示迭代项数 每迭代一次就加一项
    temp = 0 # temp表示每次迭代后等式右边的值 sqrt(temp*6)得到pi的值
    while(abs(pi - x) > accuracy):  # 每次迭代判断pi的值和当前pi的值误差是否满足条件 
                                    # 若是返回迭代次数和当前值 否则继续迭代
        n += 1 # 每次迭代计数+1
        temp += pow(-1, n+1) / (2 * n - 1)  # 更新等式右边的值 加一个新项pow(-1, n+1) / (2 * n - 1)
        x = temp * 4 #计算此时pi的粗略值x
    return x, n  # 返回pi的粗略值x和迭代次数n

def wallis(accuracy):
    x = 0 # 初始化x x用来表示每次迭代pi的当前值
    n = 0 # 初始化n n表示迭代项数 每迭代一次就加一项
    temp = 1 # temp表示每次迭代后等式右边的值
    while(abs(pi - x) > accuracy):  # 每次迭代判断pi的值和当前pi的值误差是否满足条件 
                                    # 若是返回迭代次数和当前值 否则继续迭代
        n += 1 # 每次迭代计数+1
        k = 2 * n # 使用中间变量k表示2*n
        temp *= k * k / (k-1) / (k + 1) # 根据公式更新等式右边的值
        x = temp * 2 #计算此时pi的粗略值x
    return x, n # 返回pi的粗略值x和迭代次数n

def spigot(accuracy):
    x = 1 # 初始化x x用来表示每次迭代pi的当前值
    n = 1 # 初始化n n表示迭代项数 每迭代一次就加一项
    temp = 1 # temp表示每次迭代后等式右边的值
    temp1 = 1 # temp表示每次迭代后等式右边新加项的值
    while(abs(pi - x) > accuracy):  # 每次迭代判断pi的值和当前pi的值误差是否满足条件 
                                    # 若是返回迭代次数和当前值 否则继续迭代
        n += 1 # 每次迭代计数+1
        temp1 = temp1*(n-1)/(2*n-1) # temp表示每次迭代后等式右边新加项的值
        temp += temp1  # 根据公式更新等式右边的值（加上刚才计算出来的最后一项temp1）
        x = temp * 2 #计算此时pi的粗略值x
    return x, n # 返回pi的粗略值x和迭代次数n

def race(precision, algorithms):
    res = []
    # process each algorithm in algorithms list
    for i in range(len(algorithms)):
        x, n =  algorithms[i](precision)
        res.append((i + 1, n))
    # sort by second number of pair
    res.sort(key = lambda res:res[1])
    return res

# print
def print_results(res):
    for r in res:
        print("Algorithm", r[0], "finished in", r[1], "steps")