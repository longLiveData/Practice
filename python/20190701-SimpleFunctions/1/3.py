def printZeros(num):
    # a string save result
    res = ""
    # add '0' to the string
    for i in range(num):
        res += '0'
    # print result
    print(res)
# test case
# printZeros(5)

def printZPattern(num):
    # recursive
    while(num > 0):
        # print and num decrement
        printZeros(num)
        num -= 1
# test case
# printZPattern(4)

# explain: 
# first putout number from num to 1,
# then putout number from 2 to num
# code as belows

def printZPattern2(num):
    start = num + 1
    end = 0
    while(num > end):
        # print and num decrement
        printZeros(num)
        num -= 1
    num += 2
    while(num <start):
        # print and num decrement
        printZeros(num)
        num += 1
# test case
# printZPattern2(3)