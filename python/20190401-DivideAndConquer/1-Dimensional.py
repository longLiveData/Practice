

def read_sequence(i):
    """This helper function reads i lines as i beds or people
    They are returned as a list of tuples like [("p1", 2.4, 1.4), ("p2", 5.34, 3.4), ...]
    (You may replace this representation with something else if you want.)
    """
    sequence = []
    for _ in range(i):
        name, x = input().split(" ")
        sequence.append((name, float(x)))
    return sequence

n = int(input())
people = read_sequence(n)
beds = read_sequence(n)

#################################
# implement your algorithm here #

peoplenum = []
for p in people:
	peoplenum.append(p[1])
peoplenum.sort()

for i in range(len(beds)):
	left = 0
	right = len(peoplenum)
	while(right - left > 1):
		mid = int((left+right)/2)
		if(peoplenum[mid] <= beds[i][1]):
			left = mid
		else:
			right = mid
	if(beds[i][1] < peoplenum[0]):
		right = 0

#################################

# output
	
	print(beds[i][0], right)