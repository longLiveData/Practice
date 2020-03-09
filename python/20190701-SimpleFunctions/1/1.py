# explain: 
# if a number is less than 0, print it out;
# if a number is bigger than 0 or number is 0, 
# it converts the number to binary and print it one by one
# how it works:
# if number is less than 0 just print it out and end
# if number is not less than 0, 
# print this number divided by the remainder of 2,
# and update it by divide 2
# until it is 0 or 1, now just print it is ok

# whoknows(2) = 
# 1
# 0
# whoknows(15) = 
# 1
# 1
# 1
# 1
# whoknows(-3) =
# -3

def digitize(number, order):
    # make a list to save each digit in number
    numbers = []
    while number > 0:
        # save last digit of number into the list
        numbers.append(number % 10)
        # update number by divide 10
        number = int(number/10)
    # judge order
    if order == True:
        numbers.reverse()
    # print each number in numbers
    for n in numbers:
        print(n)

# test case
# digitize(1758, True)
# digitize(1758, False)

def reverse(number):
    # transfer int to a string
    temp = str(number)
    # make the string a string list
    temp = list(temp)
    # reverse the list
    temp = temp[::-1]
    # remake the list to string
    temp = "".join(temp)
    # transfer string to int
    result = int(temp)
    print(result)

# test case
# reverse(1758)