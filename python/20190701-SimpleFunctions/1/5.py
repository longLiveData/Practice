def read_numbers():
    # make a list to save numbers
    numbers = []
    for i in range(10):
        # get number form input 
        num = input()
        # save number into list
        numbers.append(int(num))
    # return the list
    return numbers

# test case
# print(read_numbers())

def search(n):
    # get list of 10 numbers
    numbers = read_numbers()
    for i in range(len(numbers)):
        # search each number in list, if it is, print position of it
        if numbers[i] == n:
            print("in position", i+1)

# test case
# search(3)