import math
import random
import shlex

def check_argument(args):
    if (len(args)>0) and (args[0].isdigit() == True) and (int(args[0]) > 0):
        return int(args[0])
    else:
        return -1

### place your code below this line ###

number = 0
times = 0
simulation_result = []
total = 0

str_menu = '''
Dice Simulation Commands

1) Dice number from (2 to 5) fot simulation, i.e., Dice 5
2) Confirm number of dice used in simulation, i.e., Confirm 
3) Roll simulation dice a number of times, i.e., Roll 10
4) Report the simulation results, i.e., Report
5) Help menu, i.e., Help
6) Quit menu, i.e., Quit

Enter command (Dice N, Confirm, Roll N, Report, Quit) below:'''

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
    
    if cmd.lower() == 'Dice'.lower():
        if (console_argument > 1 and console_argument < 6):
            number = console_argument
        else:
            print('Error: You must input an integer 2-5; i.e. Dice 5')
        
    elif cmd.lower() == 'Confirm'.lower():
        print('Numbers of dice:', number)
 
    elif cmd.lower() == 'roll'.lower():
        if (console_argument > 0):
            times = console_argument

            # do roll
            for n in range(number):
                arr = []
                for t in range(times):
                    r = roll()
                    arr.append(r)
                    total += r
                simulation_result.append(arr)

        else:
            print('Error: You must input an integer greater than 0; i.e. Roll 5')
    
    elif cmd.lower() == 'report'.lower():
        print('simulation index')
        print([i for i in range(times)])
        print('Rolled Numbers:')
        for s in simulation_result:
            print(s)
        print('Sum of rolled numbers:', total)

    elif cmd.lower() == 'help'.lower():
        print(str_menu)

    elif cmd.lower() == 'quit'.lower() or cmd.lower() == 'exit'.lower():
        print('Thank you for using the Dice simulation')
        break

    else:
        print('Error: You entered an unknown command:', cmd) 

    def roll():
        return random.randint(1, 6)

    ### place your code above this line ###