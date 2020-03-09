import sys

# 这里的输入文件每行结尾可能是\n也可能是\r 都要考虑到
# 输出格式python3和python2不一样 注意 格式转换

def getPath(arr, path, spath, cx, cy):
    arr[cx][cy] = '1'

    # if get to target position, print
    if(cx == endx  and cy == endy):
        print (str(len(path)) + " " + str(len(spath)))
        res = ""
        for i in spath:
            res += i + " "
        print(res)
        return path, spath

    # else
    else:
        if(ifOverIndex(arr, cx-1, cy)):
            path.append([cx-1, cy])
            
            if(ifPass(arr, cx-1, cy)):
                spath.append("U")
                path, spath = getPath(arr, path, spath, cx-1, cy)
        
        if(ifOverIndex(arr, cx+1, cy)):
            path.append([cx+1, cy])
            
            if(ifPass(arr, cx+1, cy)):
                spath.append("D")
                path, spath = getPath(arr, path, spath, cx+1, cy)

        if(ifOverIndex(arr, cx, cy-1)):
            path.append([cx, cy-1])
            
            if(ifPass(arr, cx, cy-1)):
                spath.append("L")
                path, spath = getPath(arr, path, spath, cx, cy-1)

        if(ifOverIndex(arr, cx, cy+1)):
            path.append([cx, cy+1])
            
            if(ifPass(arr, cx, cy+1)):
                spath.append("R")
                path, spath = getPath(arr, path, spath, cx, cy+1)
        
        if(len(spath) > 0):
            spath.pop()

        return path, spath
    
# judge if over index
def ifOverIndex(arr, cx, cy):
    if(0 <= cx and cx < len(arr) and 0 <= cy and cy < len(arr[0])):
        return True

# if pass
def ifPass(arr, cx, cy):
    if(arr[cx][cy] == '0'):
        return True

# sys.argv : input args by command line
args = sys.argv
file = open(args[1])
lines = file.readlines()
arr = []
for i in range (1, len(lines)):

    a = lines[i].split("\n")[0].split("\r")[0].split(" ")
    arr.append(a)


startx = int(args[3])
starty = int(args[2])
endx = int(args[5])
endy = int(args[4])

if (arr[endx][endy] == '1' or arr[startx][starty] == '1'):
    print("0 0")
    print('X')
else:
    path = []
    spath = []
    path, spath = getPath(arr, path, spath, startx, starty)

    if [endx, endy] not in path:
        print(str(len(path))+ " " + str(0))
        print('X')