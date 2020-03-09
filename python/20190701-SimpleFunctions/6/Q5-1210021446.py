class parentclass:

    def __init__(self, num):
        self._num = num
    
    def methoda(self):
        print (self._num ** 2)

class childclass(parentclass):
    
    def __init__(self, num):
        self._num = num
    
    def methodb(self):
        print (self._num ** 3)

    def methodc(self):
        print (self._num ** 4)

myobj = childclass(10)
myobj.methoda()
myobj.methodb()
myobj.methodc()