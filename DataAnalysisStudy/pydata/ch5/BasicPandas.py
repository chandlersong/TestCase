from pandas import Series, DataFrame
import pandas as pd
import numpy as np

'''
reindex
'''
obj = Series([4.5, 7.2, -5.3, 3.6], index=['d', 'b', 'a', 'c'])
print(obj)
obj2 = obj.reindex(['a', 'b', 'c', 'd', 'e'])
print(obj)
print(obj2)
obj2['a'] = 1
print(obj)
print(obj2)
print(obj.reindex(['a', 'b', 'c', 'd', 'e'], fill_value=0))

obj3 = Series(['blue', 'purple', 'yellow'], index=[0, 2, 4])
print(obj3.reindex(range(6), method='ffill'))
print(obj3.reindex(range(6), method='bfill'))

frame = DataFrame(np.arange(9).reshape((3, 3)),
                  index=['a', 'b', 'c'],
                  columns=['Ohio', 'Texas', 'California'])

print(frame)

frame2 = frame.reindex(['a', 'b', 'c', 'd'])
print(frame2)

states = ['Texas', 'Utah', 'California']
print(frame.reindex(columns=states))
print(frame.ix(['a', 'b', 'c', 'd'], states))

'''
drop item
'''
obj = Series(np.arange(5.), index=['a', 'b', 'c', 'd', 'e'])

new_obj = obj.drop('c')
print(obj)
print(new_obj)

print(obj.drop(['d', 'c']))

data = DataFrame(np.arange(16).reshape((4, 4)),
                 index=['Ohio', 'Colorado', 'Utah', 'New York'],
                 columns=['one', 'two', 'three', 'four'])
print(data)
print(data.drop(['Colorado', 'Ohio']))
print(data.drop('two', axis=1))
print(data.drop(['two', 'four'], axis=1))

data_drop = data.drop(['Colorado', 'Ohio'])
data_drop['One'] = 1
print(data)
print(data_drop)

'''
index, select and filter
'''
obj = Series(np.arange(4.), index=['a', 'b', 'c', 'd'])
print(obj['b'])
print(obj[1])
print(obj[2:4])
print(obj[['b', 'a', 'd']])
print(obj[[1, 3]])
print(obj[obj < 2])
print(obj['b':'c'])
obj['b':'c'] = 5
print(obj)

data = DataFrame(np.arange(16).reshape((4, 4)),
                 index=['Ohio', 'Colorado', 'Utah', 'New York'],
                 columns=['one', 'two', 'three', 'four'])

print(data)
print(data['two'])
print(data[['three', 'one']])
print(data[:2])
print(data[data['three'] > 5])
print(data < 5)
data[data < 5] = 0
print(data)

print(data.ix['Colorado', ['two', 'three']])
print(data.ix[['Colorado', 'Utah'], [3, 0, 1]])
print(data.ix[2])
print(data.ix[:'Utah', 'two'])
print(data.ix[data.three > 5, :3])

'''
Calculate and align
'''
s1 = Series([7.3, -2.5, 3.4, 1.5], index=['a', 'c', 'd', 'e'])
s2 = Series([-2.1, 3.6, -1.5, 4, 3.1], index=['a', 'c', 'e', 'f', 'g'])
print(s1 + s2)

df1 = DataFrame(np.arange(9.).reshape((3, 3)),
                columns=list('bcd'),
                index=['Ohio', 'Texas', 'Colorado'])
df2 = DataFrame(np.arange(12.).reshape(4, 3),
                columns=list('bde'),
                index=['Utah', 'Ohio', 'Texas', 'Oregon'])
print(df1 + df2)

df1 = DataFrame(np.arange(12.).reshape((3, 4)),
                columns=list('abcd'))
df2 = DataFrame(np.arange(20.).reshape(4, 5),
                columns=list('abcde'))
print(df1 + df2)
print(df1.add(df2, fill_value=0))
print(df1.reindex(columns=df2.columns, fill_value=0))

# broad casting
arr = np.arange(12.).reshape((3, 4))
print(arr - arr[0])

frame = DataFrame(np.arange(12.).reshape((4, 3)),
                  columns=list('bde'),
                  index=['Utah', 'Ohio', 'Texas', 'Oregon'])
print(frame)
series = frame.ix[0]
print(series)
print(frame - series)
series2 = Series(range(3), index=['b', 'e', 'f'])
print(frame + series2)
series3 = frame['d']
print(frame.sub(series3, axis=0))
