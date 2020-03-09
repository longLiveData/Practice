class myfirstclass:

    def __init__(self, num):
        self._num = num
    
    def methoda(self):
        return self._num ** 2

    def methodb(self):
        return self._num ** 3

myobj = myfirstclass(4)
print(myobj.methoda())
print(myobj.methodb())