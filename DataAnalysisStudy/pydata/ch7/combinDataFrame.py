from pandas import DataFrame, Series
import pandas as pd
import numpy as np

'''
Because data merge work just like database, don't read into detail
'''
df1 = DataFrame({
    'key': ['b', 'b', 'a', 'c', 'a', 'a', 'b'],
    'data1': range(7)
})

df2 = DataFrame({
    'key': ['a', 'b', 'd'],
    'data2': range(3)
})

print(df1)
print(df2)
print(pd.merge(df1, df2))
print(pd.merge(df1, df2, on='key'))

df3 = DataFrame({
    'lkey': ['b', 'b', 'a', 'c', 'a', 'a', 'b'],
    'data1': range(7)
})

df4 = DataFrame({
    'rkey': ['a', 'b', 'd'],
    'data2': range(3)
})

print(pd.merge(df3, df4, left_on='lkey', right_on='rkey'))

left1 = DataFrame({'key': ['a', 'b', 'a', 'a', 'b', 'c'], 'value': range(6)})
right1 = DataFrame({'group_val': [3.5, 7]}, index=['a', 'b'])

print(left1)
print(right1)
print(pd.merge(left1, right1, left_on='key', right_index=True))
print(pd.merge(left1, right1, left_on='key', right_index=True, how='outer'))

lefth = DataFrame({'key1': ['Ohio', 'Ohio', 'Ohio', 'Nevada', 'Nevada'],
                   'key2': [2000, 2001, 2002, 2001, 2002],
                   'data': np.arange(5.)})
righth = DataFrame(np.arange(12).reshape(6, 2),
                   index=[['Nevada', 'Nevada', 'Ohio', 'Ohio', 'Ohio', 'Ohio'],
                          [2001, 2000, 2000, 2000, 2001, 2002]],
                   columns=['event1', 'event2'])
print(lefth)
print(righth)
print(pd.merge(lefth, righth, left_on=['key1', 'key2'], right_index=True))

left2 = DataFrame([[1, 2], [3, 4], [5, 6]], index=['a', 'c', 'e'], columns=['Ohio', 'Nevada'])
right2 = DataFrame([[7, 8], [9, 10], [11, 12], [13, 14]], index=['b', 'c', 'd', 'e'], columns=['Missouri', 'Alabama'])
print(left2)
print(right2)
print(pd.merge(left2, right2, how='outer', left_index=True, right_index=True))
print(left2.join(right2, how='outer'))
print(left1.join(right1, on='key'))

another = DataFrame([[7, 8], [9, 10], [11, 12], [16, 17]],
                    index=['a', 'c', 'e', 'f'],
                    columns=['New York', 'Oregon'])
print(another)
print(left2.join([right2, another]))
print(left2.join([right2, another],how='outer'))
