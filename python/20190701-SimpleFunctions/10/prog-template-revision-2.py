import math
import random
import shlex

def check_argument(args):
    if (len(args)>0) and (args[0].isdigit() == True) and (int(args[0]) > 0):
        return int(args[0])
    else:
        return -1

### place your code below this line ###

str_menu = '''
Console Application Template v2

1) Example OptionA, i.e., OptionA
2) Example OptionB X, where X is an integer greater than zero; i.e., OptionB 10  
3) Example OptionC, i.e., OptionC
4) Example Quit menu, i.e., Quit

Enter command (OptionA, OptionB X, Quit) below:'''

print(str_menu)

### place your code above this line ###

while True:
    try:
        cmd, *args = shlex.split(input('> '))
        if len(args) == 0:
            args = []
    except ValueError:
        cmd = -1
        
    console_argument = check_argument(args)

    ### place your code below this line ###
    
    if cmd.lower() == 'optiona'.lower():
        print('You have selected MenuA')
            
    elif cmd.lower() == 'optionb'.lower():
        if (console_argument > 0):
            print('You have selected OptionB', console_argument)
        else:
            print('Error: You must input an integer greater than zero; i.e. OptionB 5')
 
    elif cmd.lower() == 'optionc'.lower():
        print('You have selected MenuC')
    
    elif cmd.lower() == 'quit'.lower():
        print('Thank you for using the Console Application Template v2')
        break

    else:
        print('Error: You entered an unknown command:', cmd) 

    ### place your code above this line ###


