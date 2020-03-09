import string

def findics(str):
    index = str.find('ics')
    if index == -1:
        return False
    else:
        return index >= 2

print(findics('ffff2icsand'))
print(findics('ficsand'))
print(findics('fficsand'))
print(findics('ficdsand'))