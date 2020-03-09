# import math
# import random

# # 3
# def func3():
#     list1 = input('Enter the first sequence of numbers separated by commas:')
#     list2 = input('Enter the second sequence of numbers separated by commas:')
#     arr1 = list(map(int, list1.split(',')))
#     arr2 = list(map(int, list2.split(',')))
#     res = []
#     for a in arr1:
#         for b in arr2:
#             res.append((a, b))
#     print(res)
    
# func3()
# # 5
# def func5():
#     # define a function to judge if a number prime
#     def prime(n):
#         for i in range(2, int(math.sqrt(n)+1)):
#             if n % i == 0:
#                 return False
#         return True
#     # define a function to judge if a number digits add up bigger than 5
#     def add_up(n):
#         digits = list(str(n))
#         count = 0
#         for d in digits:
#             count += int(d)
#         if count > 5:
#             return True
#         else:
#             return False 
#     res = []
#     # judge each number in 0-250 and print result
#     for n in range(0, 250):
#         if prime(n) and add_up(n):
#             res.append(n)
#     print(res)

# func5()

# # 6
# def func6():
#     res = random.randint(1, 100)
#     guess = 0
#     while(int(guess) != res):
#         guess = input('Enter a number: ')
#         if int(guess) > res:
#             print('The number is less than', guess)
#         if int(guess) < res:
#             print('The number is bigger than', guess)
#     print('Congratulations! The number is', guess, '!')

# func6()

# # 7
# def func7():
#     # a
#     count = 0
#     for i in range(11, 20, 2):
#         count += i
#     print(count)
#     # b
#     nums = [75, 83, 47, 29, 96]
#     middle = 0
#     for num in nums:
#         middle += num / len(nums)
#     temp = 0
#     for num in nums:
#         temp += (num - middle) ** 2
#     res = math.sqrt(temp)
#     print(res)
#     # c
#     print(66 ** 0.25)
#     # d
#     print(0.5 * 40 * 10 * 10)

# func7()

# # 8
# def func8():
#     test_scores = '2 5 6 9 3 8 2 6 5 9 5 6 6 2 9 0 9 8 8 7'
#     score = test_scores.split(' ')
#     score.sort()
#     score = score[::-1]
#     # 1
#     print(score[1])
#     # 2
#     print(score[13])
#     # 3
#     print(int(score[7]) + int(score[8]))
#     # 4
#     print(int(score[18]) - int(score[2]))

# func8()

# # 9
# def func9():
#     s = 'computerscience'
#     # a
#     print(s[-1] == 'b')
#     # b
#     print(s[0] == 'c')
#     # c
#     print(s.count('e') == 3)
#     # d
#     print(len(s) == 16)

# func9()

# # 10
# def func10():
#     # a
#     print(type(12 * 0.1) == type(12))
#     # b
#     s = ['a', 'b', 'c', 'd', 'e']
#     print(len(s*3) == 15)
#     # c
#     print((20%5) <= 2)
#     # d
#     def prime(n):
#         for i in range(2, int(math.sqrt(n)+1)):
#             if n % i == 0:
#                 return False
#         return True
#     print((prime(5) == True) and (4 % 2 == 1))
#     # e
#     print(type(62) == type(0.1))

# func10()

# # 11
# def func11():
#     s = 'abcdefghijklmnopqrstuvwxyz'
#     # a
#     a = s[7] + s[0] + s[17] + s[17] + s[8] + s[18]
#     print(a)
#     # b
#     b = s[11] + s[0] + s[1]
#     print(b)
#     # c
#     c = s[13] + s[4] + s[22]
#     print(c)
#     # d
#     d = s[12] + s[12] + s[12] + s[24] + s[22]
#     print(d)

# func11()

def get(n):
    # 把小于n的平方数都放到squares中
    squares = []
    i = 1
    while i ** 2 <= n:
        squares.append(i)
        i += 1
    
    level = 0
    queue = [n]
    visited = [False] * (n+1)
    while queue:
        level += 1
        temp = []
        for q in queue:
            for fa in squares:
                if q == fa:
                    return level
                if q < fa:
                    break
                if visited[q - fa]:
                    continue
                temp.append(q-fa)
                visited[q-fa] = True
            queue = temp
    return level

print(get(13))