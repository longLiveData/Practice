def printdivisors(num):
    # divisors of a number smaller than half of it
    end = int(num / 2) + 1
    res = []
    # search from 1 to end
    for i in range(1, end):
        if num % i == 0:
            # append i to res list
            res.append(i)
    print(res)
# test case
# printdivisors(36)

def sumdivisors(num):
    # define end of search
    end = int(num / 2) + 1
    sum = 0
    # search from 1 to end
    for i in range(1, end):
        if num % i == 0:
            # add i to sum
            sum += i
    return sum
# test case
# print(sumdivisors(6))

def allperfects(lower, higher):
    # process each number between lower and higher
    for num in range(lower, higher+1):
        # if it is a perfect number
        if num == sumdivisors(num):
            print(num)
# test case
# allperfects(3, 1000)


