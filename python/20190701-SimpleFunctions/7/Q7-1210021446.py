import pandas as pd

obj7 = pd.Series([i for i in range(0, 600, 100)],
                index = ['Column 0', 'Column 1', 'Column 2', 'Column 3', 'Column 4', 'Column 5'])
print(obj7)