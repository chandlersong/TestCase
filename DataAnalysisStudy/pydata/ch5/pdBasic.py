from pandas import Series, DataFrame
import pandas as pd
import numpy as np

obj = Series([9, 8, 7, 6, 5])
print(obj)
print(obj.index)

obj2 = Series([9, 8, 7, -6, -5], index=['e', 'd', 'c', 'b', 'a'])
print(obj2)
print(obj2['a'])
print(obj2.index)
obj['e'] = 11
print(obj2[['e', 'd']])
print(obj2 > 0)
print(obj2 * 2)
print(np.exp(obj2))
print(('e' in obj2))
print(('f' in obj2))
print((0.002479 in obj2))

sdata = {'Ohio': 35000,
         'Texas': 71000,
         'Oregon': 16000,
         'Utah': 5000}
print(Series(sdata))
states =['California','Ohio','Oregon','Texas']
stateInfo = Series(sdata,index = states)
print(stateInfo)
print(pd.isnull(stateInfo))
print(pd.notnull(stateInfo))
print(stateInfo.isnull())