# Place ALL import statements at the top of your file
import random
import dice

# File: fileName.py <-- Replace with *your* file name
# Author: your name <-- Replace with *your* name
# Email Id: your email id <-- Replace with *your* email id
# Description: Assignment 1 - place assignment description here...
# This is my own work as defined by the University's
# Academic Misconduct policy.
#
def show_head():
    """ print head info """
    print("Petals Around The Rose")
    print("---------------------")
    print("The name of the game is 'Petals Around The Rose'. The name of the")
    print("game is important. The computer will roll five dice and ask you to")
    print("guess the score for the roll. The score will always be zero or an")
    print("even number. Your mission, should you choose to accept it, is to")
    print("work out how the computer calculates the score. If you succeed in")
    print("working out the secret and guess correctly four times in a row, you") 
    print("become a Potentate of the Rose.")
    print("")

def display():
    """ display dices and return user answer """
    answer = show()
    temp = input("Please enter your guess for the roll: ")
    if int(temp) == int(answer):
        print("Well done! You guessed it!\n")
        return 1
    elif int(temp) % 2 == 1:
        print("No sorry, it's "+ str(answer) +" not "+ temp +". The score is always even.\n")
    else:
        print("No sorry, it's "+ str(answer) +" not "+ temp +".\n")

def show():
    """ show dices and get read answer """
    # get dies
    die1 = random.randint(1, 6)
    die2 = random.randint(1, 6)
    die3 = random.randint(1, 6)
    die4 = random.randint(1, 6)
    die5 = random.randint(1, 6)
    # display
    dice.display_dice(die1, die2, die3, die4, die5)
    # calc answer
    answer = 0
    for die in [die1, die2, die3, die4, die5]:
        if die == 3:
            answer += 2
        elif die == 5:
            answer += 4
    # answer
    return answer    

def show_summary(count, right):
    """ show finally summary """
    print("\nGame Summary")
    print("============")
    print()
    print("You played "+ str(count)+" games:") 
    print("  |--> Number of correct guesses: " + str(right))
    print("  |--> Number of incorrect guesses: " + str(count-right))
    print()
    print("Thanks for playing!\n")

def display_details():
    """ main program """
    count = 0
    right = 0
    right_row = 0
    wrong_row = 0
    show_head()
    game_check = input("Would you like to play Petals Around The Rose [y|n]? ")
    if game_check == 'n':
        print("No worries... another time perhaps... :)")
    else:
        while game_check != 'n':
            if game_check != 'y':
                print("Please enter either 'y' or 'n'.\n")
            else:
                count += 1
                if display() == 1:
                    right += 1
                    right_row += 1
                    wrong_row = 0
                    if right_row >= 4:
                        print("Congratulations! You have worked out the secret! ")
                        print("Make sure you don’t tell anyone!")
                else:
                    wrong_row += 1
                    right_row = 0
                    if wrong_row >= 4:
                        print("Hint: The name of the game is important... Petals Around The Rose.")

            game_check = input("Roll dice again [y|n]? ")
        show_summary(count, right)

# Call display_details() to display your details
display_details()