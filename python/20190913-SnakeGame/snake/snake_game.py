#  
# File: fileName.py  
# Author: your name 
# Id: your id 
# Description: Assignment 2 – place assignment description here…  
# This is my own work as defined by EIBT  
# Academic Misconduct policy.  
#
 
import game
import random

def create_snake(window):
    '''Creates and returns a list containing 3 Rectangles to represent a snake.
    
    The Rectangle at index 0 will represent the head.
    The Rectangle at index 2 will represent the tail.
    Each Rectangle should be given the colour 'green'.
    Each Rectangle should have a width and height of 50.
    The head should be at position 400, 400.
    Each Rectangle should be placed 50 pixels apart to the right.
    You can construct the head using the line:
        rect = game.Rectangle(400, 400, 50, 50, window.get_canvas(), 'green')
    '''
    snake=[]
    head= game.Rectangle(400, 400, 50, 50, window.get_canvas(), 'green')
    middle= game.Rectangle(350, 400, 50, 50, window.get_canvas(), 'green')
    tail= game.Rectangle(300, 400, 50, 50, window.get_canvas(), 'green')
    snake.append(head)
    snake.append(middle)
    snake.append(tail)
    return snake
   

def create_apple(window):
    '''Constructs and returns a Rectangle at a random position in intervals of 50 pixels.

    The Rectangle should be given the colour 'red'.
    The Rectangle should have the width and height 50.
    Assuming the canvas is 800x800 pixels, there should be 16x16 positions
    where a 50x50 Rectangle can be placed. You can generate a random
    position using:
        random.randint(0,15)*50, random.randint(0,15)*50
    '''
    apple = game.Rectangle(random.randint(0,15)*50,random.randint(0,15)*50, 50, 50, win.get_canvas(), 'red')
    return apple


def move_snake(snake, direction):
    '''Moves the snake 50 pixels in the given direction.
    
    Assume the direction is one of the strings 'Up', 'Down', 'Left',
    or 'Right'. The head Rectangle of the snake should move 50 pixels in
    the given direction and the other Rectangles should follow.
    An algorithm that does this may loop from the tail to the Rectangle
    before the head, setting the position of that Rectangle to equal next
    Rectangle's position. It moves the head only after all other Rectangles
    have been moved. You may move the head 'Up' by using:
        snake[0].move_by(0, -50)
        
    '''

    if direction == 'Up':
        tx, ty = snake[0].get_x(), snake[0].get_y()
        snake[0].move_by(0, -50)
        for i in range(1, len(snake)):
            nx, ny = snake[i].get_x(), snake[i].get_y()
            snake[i].move_by(tx-nx, ty-ny)
            tx, ty = nx, ny

    if direction == 'Down':
        tx, ty = snake[0].get_x(), snake[0].get_y()
        snake[0].move_by(0, 50)
        for i in range(1, len(snake)):
            nx, ny = snake[i].get_x(), snake[i].get_y()
            snake[i].move_by(tx-nx, ty-ny)
            tx, ty = nx, ny

    if direction == 'Left':
        tx, ty = snake[0].get_x(), snake[0].get_y()
        snake[0].move_by(-50, 0)
        for i in range(1, len(snake)):
            nx, ny = snake[i].get_x(), snake[i].get_y()
            snake[i].move_by(tx-nx, ty-ny)
            tx, ty = nx, ny
            

    if direction == 'Right':
        tx, ty = snake[0].get_x(), snake[0].get_y()
        snake[0].move_by(50, 0)
        for i in range(1, len(snake)):
            nx, ny = snake[i].get_x(), snake[i].get_y()
            snake[i].move_by(tx-nx, ty-ny)
            tx, ty = nx, ny

def extend_snake(snake, window):
    '''Appends a new Rectangle to the tail of the snake increasing the length by 1.

    The Rectangle should be given the colour 'green'.
    The Rectangle should have a width and height of 50.
    The Rectangle should be given the same coordinates as the tail.
    The Rectangle should be appended to the snake.
    You can use the get_x() amd get_y() methods to access the tail's position.
    '''
    # TODO Add your code and comments here
    print('In function extend_snake')

    snake1 = snake[-1]
    snake2 = snake[-2]
    new_x = snake1.get_x() * 2 - snake2.get_x()
    new_y = snake1.get_y() * 2 - snake2.get_y()
    new_snake = game.Rectangle(new_x, new_y, 50, 50, window.get_canvas(), 'green')
    snake.append(new_snake)

    return snake

    
def is_on_apple(snake, apple):
    '''Returns True if the snake's head is at the same position as the apple.

    If the snake's head and the apple have the same x and y coordinates
    return True; otherwise return False.
    '''
    # TODO Add your code and comments here
    print('In function on_apple')
    apple_x, apple_y = apple.get_x(), apple.get_y()
    snake_x, snake_y = snake[0].get_x(), snake[0].get_y()
    return apple_x == snake_x and apple_y == snake_y
    
def collision(snake, window):
    '''Returns True if the snake's head hits a part of its body or leaves the Window.

    If the the snake's head is at the same position as any Rectangle in
    its body this should return True.
    If the snake's head's position is less than 0 along the x or y, or
    greater than or equal to the Window's canvas width or height, this
    should return True.
    In all other cases, this return False.
    You can access the window's canvas dimensions using:
    win.get_canvas_width()
    win.get_canvas_height()
    '''
    # TODO Add your code and comments here
    print('In function collision')
    head_x, head_y = snake[0].get_x(), snake[0].get_y()
    if head_x < 0 or head_x > win.get_canvas_width():
        return True
    if head_y < 0 or head_y > win.get_canvas_height():
        return True
    for i in range(1, len(snake)):
        if head_x == snake[i].get_x() and head_y == snake[i].get_y():
            return True
    return False


def add_to_high_scores(score, filename):
    '''Opens the file with the given name and adds the score in a sorted position.

    The file should contain one score on each line.
    The file scores should be sorted from highest to lowest.
    You may need to put the contents of the file and the score into a list,
    and then sort the list, before writing it back to the file.
    '''
    # TODO Add your code and comments here
    print('In function add_to_high_scores')

    file = open(filename)
    ls = []
    for line in file.readlines():
        ls.append(int(line.strip()))
    file.close()

    ls.append(score)
    ls.sort()
    ls.reverse()

    file = open(filename, "w")
    for i in ls:
        file.writelines(str(i)+"\n")
    file.close()

def on_key_press(event):
    '''Detects key press events and performs an action when important keys are pressed.
    
    When the user presses Up, Down, Left, or Right, this should set the
    global direction to that String, if the snake can move in that direction.
    A snake moving up cannot move down. A snake moving left cannot move right.
    When the user presses Return, this should toggle the playing variable.
    If it was True, this should set it to False; If it was False this
    should set it to True.
    You can access the name of the key that was pressed by using event.keysym.
    Check if Up was pressed by using:
    if event.keysym == 'Up':
    '''
    
    # event.keysym returns the name of the key that was pressed

    global direction
    global playing

    if event.keysym == 'Up':
        if direction != "Down":
            direction = "Up"
    elif event.keysym == 'Down':
        if direction != "Up":
            direction = 'Down'
    elif event.keysym == 'Left':
        if direction != "Right":
            direction = 'Left'
    elif event.keysym == 'Right':
        if direction != "Left":
            direction = 'Right'
    elif event.keysym == 'Return':
        if playing == True:
            playing = False
        else:
            playing = True


'''Allow the user to play a game of Snake.

Read the stages described in the assignment specification to help you
write this code.
'''

direction = 'Right'
playing = True
score = 0

win = game.Window('Snake Game')
apple = create_apple(win)
snake = create_snake(win)
win.bind_keys_to(on_key_press)
win.set_text('Welcome to Snake - Press Return to Continue...')

timer = 0
turn_time = 1
win.get_time_elapsed()
win.bind_keys_to(on_key_press)
while win.is_open():
    win.update()
    timer += win.get_time_elapsed()
    if timer > turn_time:
        timer = 0
        if playing:
            score += 10
            move_snake(snake, direction)
            if collision(snake, win):
                for s in snake:
                    s.destroy()
                apple.destroy()

                apple = create_apple(win)
                snake = create_snake(win)
                direction = 'Right'
                add_to_high_scores(score, "scores.txt")
                win.set_text('Score: X - Game Over - Press Return to continue…')
                playing = False
                score = 0
                turn_time = 1

            if is_on_apple(snake, apple):
                turn_time = max(turn_time-0.1, 0.1)
                score += 100
                extend_snake(snake, win)
                apple.destroy()
                apple = create_apple(win)

            win.set_text('Score: ' + str(score))

    