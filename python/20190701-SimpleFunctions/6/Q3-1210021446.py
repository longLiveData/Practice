def break_modules(x, y):
    try:
        res = x/y
    except ZeroDivisionError:
        return 'All numbers must be greater than zero'
    except TypeError:
        return 'Unknown error, please check your numbers'
    else:
        return  res

print(break_modules(5, 2))
print(break_modules(7, 0))
print(break_modules(7, 'ten'))