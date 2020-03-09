import random

def process(fave_number):
    return ((fave_number + fave_number) * 2 ) ** 5 + 123456

def div_by_5(num):
    return num % 5 == 0

def add_world(word):
    return word + 'world' + 'Bob' * random.randint(0, 3)

fave_number = 1
# doing some calculations on this number
new_fave = process(fave_number)
print(new_fave)

# check if new_fave is divisible by 5
print("Is new fave number divisible by 5?", div_by_5(new_fave))
    
fave_number_2 = 42
# doing the same calculations as above on this number
new_fave_2 = process(fave_number_2)
print(new_fave_2)

# check if new_fave_2 is divisible by 5
print("Is new fave number divisible by 5?", div_by_5(new_fave_2))
    
fave_word = 'hello'
# add 'world' and a random number (between 0-3) of 'Bob's to this word
print(add_world(fave_word))

fave_word_2 = 'csc108'
# add 'world' and a random number (between 0-3) of 'Bob's to this word
print(add_world(fave_word_2))

fave_word_3 = 'catz'
# add 'world' and a random number (between 0-3) of 'Bob's to this word
print(add_world(fave_word_3))