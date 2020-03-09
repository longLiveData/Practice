

def read_sequence(i):
    """This helper function reads i lines as i beds or people
    They are returned as a list of tuples like [("p1", 2.4, 1.4), ("p2", 5.34, 3.4), ...]
    (You may replace this representation with something else if you want.)
    """
    sequence = []
    for _ in range(i):
        name, x, y = input().split()
        sequence.append([name, float(x), float(y)])
    return sequence

n = int(input())
people = read_sequence(n)
beds = read_sequence(n)
	
############################
# write your solution here #
for b in beds:
    b.append(0)
people.sort(key = lambda p:p[1])
beds.sort(key = lambda b:b[1])

def MergeSort(people, beds):
    # divide end 
    if(len(beds) <= 1):
        num = 0
        for p in people:
            if p[1] <= beds[0][1] and p[2] <= beds[0][2]:
                num += 1
        beds[0][3] = num
        return people, beds 

    # divide
    mid = int(len(beds) / 2)
    x = beds[mid-1][1]
    pl = []
    pr = []
    for p in people:
        if(p[1] <= x):
            pl.append(p)
        else:
            pr.append(p)
    lp, lb = MergeSort(pl, beds[:mid])
    rp, rb = MergeSort(pr, beds[mid:])
    
    # merge
    return Merge(lp, lb, rp, rb)


def Merge(P, B, Q, C):
    pl = []
    for p in P:
        pl.append(p[2])
    pl.sort()
    pl.append(float("inf"))
	
    for c in C:
        num = c[2]
        pos = 0
        con = True
        while(con):
            if(pl[pos] > num):
                con = False
            else:
                pos += 1
        c[3] += pos

    return P+Q, B+C

rP, rB = MergeSort(people, beds)

############################

# output
rB.sort(key = lambda b:b[0])
for b in rB:
    print(b[0], b[3])