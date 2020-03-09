import numpy as np
import pandas as pd

arr = np.random.randn(100000)
df = pd.DataFrame(arr)

df.to_csv('result4.csv')