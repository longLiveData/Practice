class Node:
    """
    if count = -1, it's a pNode
    """
    def __init__(self, name, xsize, ysize, count = -1):
        self.name = name
        self.xsize = xsize
        self.ysize = ysize
        self.count = count
        
def merge_sortX(L):
    if len(L) <= 1:
        return L

    mid = len(L)//2
    sorted_left = merge_sortX(L[:mid])
    sorted_right = merge_sortX(L[mid:])

    return merge_x(sorted_left, sorted_right)

def merge_x(L, R):
    S = []
    i, j, counter = 0, 0, 0

    while(i < len(L) and j < len(R)):
        if L[i].xsize <= R[j].xsize:
            S.append(L[i])
            i += 1
        else:
            S.append(R[j])
            j += 1

    S.extend(L[i:])
    S.extend(R[j:])

    return S

def merge_sortAll(L):
    if len(L) <= 1:
        return L

    mid = len(L)//2
    sorted_left = merge_sortAll(L[:mid])
    sorted_right = merge_sortAll(L[mid:])

    return merge_all(sorted_left, sorted_right)

def merge_all(L, R):
    Spc = []
    Sqb = []
    S = []
    counter = 0
    P, B, Q, C = [], [], [], []

    for node in L:
        if node.count == -1:
            P.append(node)
        else:
            B.append(node)
    for node in R:
        if node.count == -1:
            Q.append(node)
        else:
            C.append(node)
    
    while (P and C):
        if P[0].ysize <= C[0].ysize:
            temp = P.pop(0)
            Spc.append(temp)
        else:
            temp = C.pop(0)
            Spc.append(temp)
    Spc += P
    Spc += C
    
    for node in Spc:
        if node.count == -1:
            counter += 1
        else:
            node.count += counter

    while (Q and B):
        if Q[0].ysize <= B[0].ysize:
            temp = Q.pop(0)
            Sqb.append(temp)
        else:
            temp = B.pop(0)
            Sqb.append(temp)
    Sqb += Q
    Sqb += B

    while (Spc and Sqb):
        if Spc[0].ysize <= Sqb[0].ysize:
            temp = Spc.pop(0)
            S.append(temp)
        else:
            temp = Sqb.pop(0)
            S.append(temp)
    S += Sqb
    S += Spc

    
    return S


n = int(input())
list = []
beds = []

for _ in range(n):
    line = input().split()
    list.append(Node(line[0], float(line[1]), float(line[2])))
for _ in range(n):
    line = input().split()
    list.append(Node(line[0], float(line[1]), float(line[2]), 0))
    beds.append(Node(line[0], float(line[1]), float(line[2]), 0))

x_sortedList = merge_sortX(list)
sorted_list = merge_sortAll(x_sortedList)

for bed in sorted_list:
    if bed.count != -1:
        print("%s %d"%(bed.name, bed.count))