def listmult(arr):
    arr = sorted(arr)
    median = arr[int(len(arr)/2)]
    for i in range(len(arr)):
        arr[i] += median
    return arr

print(listmult([2, 3, 5]))
print(listmult([2, 3, 4, 5]))
print(listmult([2, 3, 4, 5, 6]))