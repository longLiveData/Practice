'''
answers:
(1) 
First I divide beds into parts that A part only contains one bed,
The part also include people which people[i][1]<=beds[j][1]
Then I Merge them using the method of the 2-MergeStep.py
(2)
First I divide beds into parts until A part only contains one bed,
The part also include people which people[i][1]<=beds[j][1]
Then I Merge them using the method of the 2-MergeStep.py:
For B, the answer only includes P, so it's B[i][3]
For C, the answer includes P AND Q, C[i][3] only contains Q, so we need to add from P
We make a list pl that cantains P[i][2]
Case P and C is ordered, so we only need to find number of numbers in pl which no bigger than C[i][2]
The number is answer in P, finally we add it and C[i][3],  then we return that
(3) 
Like 1-Dimensional
Divide is O(nlongn)
Merge is O(nlongn)
So the upper bound on the time complexity of the algorithm is O(nlogn)
'''

'''
copy this as inputs:

5
p1 12.4 1.0
p2 1.0 2.0
p3 11.3 11.0
p4 5.3 1.0
p5 3.2 3.1
b1 15.2 23.5
b2 2.1 12.5
b3 1.3 24.3
b4 2.6 2.0
b5 16.4 2.0
'''

def read_sequence(filePath):
    """This helper function reads i lines as i beds or people
    They are returned as a list of tuples like [("p1", 2.4, 1.4), ("p2", 5.34, 3.4), ...]
    (You may replace this representation with something else if you want.)
    """
    sequence = []
    file = open(filePath)
    lines = file.readlines()
    for line in lines:
        line = line.split("- [")[1].split("]")[0].split(",")
        name = line[0].split("'")[1]

        sequence.append([name, float(line[1]), float(line[2])])
    return sequence

n = 256

people = read_sequence("p.txt")
beds = read_sequence("b.txt")

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

        if(beds[0][0] == 'b167'):
            print(beds)
            print(people)

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
    
    P.extend(Q)
    P.sort(key = lambda p:p[1])
    B.extend(C)
    B.sort(key = lambda b:b[1])

    return P, B

rP, rB = MergeSort(people, beds)

############################

# output
rB.sort(key = lambda b:b[0])
for b in rB:
    print(b[0], b[3])