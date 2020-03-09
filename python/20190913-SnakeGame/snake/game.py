'''A simple game development API for educational purposes.

the game module was developed for educational purposes to provide a
a simple game development API to students with no prior knowledge of
TkInter or TK/TCL. It includes wrapper for tkinter.Tk and tkinter.Canvas
to ease their use for the purposes of game development.
'''
__author__  = "Michael Ulpen"
__email__   = "michael.ulpen@unisa.edu.au"
__license__ = "GPL"
__date__    = "15 August 2019"

import tkinter as tk
import time

class Window(tk.Tk):
    '''Window provides a simple graphic user interface for game development.

    Window extends tkinter.TK by providing some helpful methods for
    accessing the window size and state.By default, it provides a simple
    layout containing a 1-line text area and a canvas for drawing graphics
    and shapes.
    '''

    def __init__(self, title="Game", width=800, height=824,
                 master=None, default_layout=True):
        '''Creates TK window.

        The default dimensions are 800x830 pixels.
        If default_layout is set to True, the window will be preconfigured
        to contain a 1-line text area and an 800x800 canvas
        '''
        super().__init__(master)
        
        self.title(title)
        self.geometry(str(width)+"x"+str(height)+"+0+0")
        if default_layout:
            self.__text_area = tk.Label(self, font="18", text="", fg="black")
            self.__text_area.pack(side = tk.TOP)
            self.__canvas = tk.Canvas(self, bg="light grey")
            self.__canvas.pack(side = tk.BOTTOM, expand="YES", fill='both')
        
        self.update()
        self.__open = True
        # self.__old_timer = time.time_ns()
        self.__old_timer = time.time()
        self.protocol("WM_DELETE_WINDOW", self.destroy)

    def destroy(self):
        '''Closes the window.

        After the Window is destroyed a call to is_open will return false.
        Subsequent calls to other methods may fail with a _tkinter.TclError.
        '''
        self.__open = False
        super().destroy()
    
    def is_open(self):
        '''Returns True if the Window has not been closed.
        
        Otherwise returns False.
        '''
        return self.__open
        
    def get_canvas(self):
        '''Returns the canvas of the Window.

        Raises an AttributeError if default_layout was set to False.
        '''
        return self.__canvas

    def get_canvas_width(self):
        '''Returns the canvas's width.

        Raises an AttributeError if default_layout was set to False.
        '''
        return self.__canvas.winfo_width()

    def get_canvas_height(self):
        '''Returns the canvas's height.

        Raises an AttributeError if default_layout was set to False.
        '''
        return self.__canvas.winfo_height()
    
    def set_text(self, new_text):
        '''Changes the text in the Window's text field to new_text.

        Raises an AttributeError if default_layout was set to False.
        '''
        if self.__text_area != None:
            self.__text_area.config(text=str(new_text))
            self.update()

    def get_time_elapsed(self):
        '''Returns seconds as a float since the last call to this method. 

        Seconds is returned as a float with nanosecond precision.
        '''
        # nanos = time.time_ns()
        nanos = time.time()
        diff = nanos - self.__old_timer
        self.__old_timer = nanos
        return diff 
    
    def bind_keys_to(self, function_arg):
        '''Binds the argument function to all <KeyPress> events.

        The function_arg is the name of a function (without parentheses).
        This function will be called whenever a key is pressed down on
        the keyboard.
        '''
        self.bind("<KeyPress>", function_arg)


class Rectangle:
    '''A wrapper class for Rectangles drawn on the Window's canvas.

    Rectangle contains width, height, x, y coordinates and colour. This is
    designed to be used with a Tk canvas object. You can get the canvas
    from Window.get_canvas()
    '''

    def __init__(self, x, y, w, h, canvas=None, colour='green'):
        '''Draws an width x height pixel Rectangle to the canvas at x, y.

        Rectangle will not be drawable if canvas is None.
        '''
        self.__x = x
        self.__y = y
        self.__w = w
        self.__h = h
        self.__colour = colour
        if canvas != None:
            self.__canvas = canvas
            self.__id = self.__canvas.create_rectangle(
                        self.__x,
                        self.__y,
                        self.__x+self.__w,
                        self.__y+self.__h,
                        fill=self.__colour, width=1)

    def get_id(self):
        '''Returns the id of this object in the canvas.
        
        If canvas was set to None in __init__, this will return None.
        '''
        try:
            return self.__id
        except AttributeError:
            return None
    
    def get_x(self):
        '''Returns the x coordinate of the Rectangle measured from top-left.

        '''
        return self.__x

    def get_y(self):
        '''Returns the y coordinate of the Rectangle measured from top-left.

        '''
        return self.__y

    def get_width(self):
        '''Returns the width of the Rectangle.

        '''
        return self.__w

    def get_height(self):
        '''Returns the height of the Rectangle.

        '''
        return self.__h
    
    def move_by(self, dx, dy):
        '''Adds the argument dx to the x coordinate and dy to the y coordinate.

        This can be used to move the Rectangle a given distance from its
        current position.
        '''
        self.__x += dx
        self.__y += dy
        if self.__canvas != None:
            self.__canvas.move(self.__id, dx, dy)

    def move_to(self, x, y):
        '''Sets the Rectangle's x and y coordinates to the argument x and y.

        '''
        self.move_by(-self.get_x()+x, -self.get_y()+y)

    def destroy(self):
        '''Deletes the Rectangle from its canvas and sets its id and canvas to None.

        The Rectangle will not be drawable after a call to this method.
        '''
        if self.__canvas != None:
            self.__canvas.delete(self.__id)
            self.__id = None
            self.__canvas = None
        
    def __str__(self):
        '''Returns a str representation of the Rectangle.

        Called automatically when the Rectangle is printed.
        '''
        return "Rectangle: " + str(self.get_x()) + "x, " + \
            str(self.get_y())+"y, " + str(self.get_width()) +"w, " + \
            str(self.get_height()) +"h"


