from pandas import Series
import numpy as np

print('test prod')
prod_test = Series([1, 2, 3])
print(prod_test.prod())  # 1*2*3...*N
print(prod_test.cumprod())

print('test pct change')
pct_change_test = Series([1, 2, 5, 4, 3])
print(pct_change_test.pct_change())

