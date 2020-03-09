

def read_people(i):
    """This helper function reads i lines as i people
    They are returned as a list of tuples like [("p1", 2.4, 1.4), ("p2", 5.34, 3.4), ...]
    (You may replace this representation with something else if you want.)
    """
    people = []
    for _ in range(i):
        name, x, y = input().split()
        people.append((name, float(x), float(y)))
    return people

def read_beds(i):
    """This helper function reads i lines as i beds
    They are returned as a list of tuples like [("b1", 2.4, 1.4, 2), ("b2", 5.34, 3.4, 5), ...]
    (You may replace this representation with something else if you want.)
    """
    beds = []
    for _ in range(i):
        name, x, y, c = input().split()
        beds.append((name, float(x), float(y), int(c)))
    return beds

# get the constants defining the number of people / beds
n = int(input())
l = int(input())
m = int(input())

# P is the first l people
P = read_people(l)
# B is the first m beds
B = read_beds(m)
# Q is the remaining n-l people
Q = read_people(n-l)
# C is the remaining n-m beds
C = read_beds(n-m)


#################################
# implement your algorithm here #
for b in B:
	print(b[0], b[3])
	
pl = []
for p in P:
	pl.append(p[2])
pl.append(float("inf"))

for c in C:
	num = c[2]
	pos = 0
	con = True
	while(con):
		if(pl[pos] <= num):
			pos += 1
		else:
			con = False
	print(c[0], c[3] + pos)
	
#################################

# output
