import random

def hangman():
    res_arr = ['dog', 'cat', 'mom', 'dad', 'lizard']
    index = random.randint(0, 4)
    res = list(res_arr[index])
    # print(res)
    print("Welcome to Hangman!")
    s_count = 0
    while(len(res) != 0):
        print()
        ch = input("Guess a letter:")
        if ch in res:
            print('"' + ch + '" is in the word.')
            res.remove(ch)
        else :
            print('"' + ch + '" is not in the word.')
            s_count += 1
        print("Strike Count = " + str(s_count))
    print()
    print("Congratulations, you have won the game! The word is " + res_arr[index])

hangman()