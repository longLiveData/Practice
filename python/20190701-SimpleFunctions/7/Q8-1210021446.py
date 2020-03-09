import pandas as pd

data10 = {  'state':['Ohio', 'Ohio', 'Arizona', 'Arizona'],
            'population': [1.5, 1.7, 3.6, 4.1],
            'year': [2000, 2001, 2000, 2002]}

frame10 = pd.DataFrame(data10)
print(frame10)