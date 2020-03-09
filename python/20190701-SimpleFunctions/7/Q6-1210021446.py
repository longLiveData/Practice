import numpy as np

array6 = np.random.randn(20, 5)
print(array6)

array7 = array6.copy()
array7 = np.multiply(array7, 100)
print(array7)