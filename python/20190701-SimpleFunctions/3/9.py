def pasteCalc(mushroom, tomato, oil, pasta):
    mushroom = int(mushroom / 1)
    tomato = int(tomato / 2)
    oil = int(oil / 2)
    pasta = int(pasta / 5)
    
    return min(mushroom, tomato, oil, pasta)

print(pasteCalc(10, 8, 8, 25))
print(pasteCalc(5, 2, 6, 22))