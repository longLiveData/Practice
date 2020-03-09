import math

def Fcutoff(grades):
    grades = sorted(grades)
    index = math.ceil(len(grades) * 0.3)
    return grades[index]

grades = [1,2,3,4,5,6,7,8,9,10,11,12,13,14]
print(Fcutoff(grades))