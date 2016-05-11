from pandas import DataFrame, Series
import numpy as np
import pandas as pd

df = DataFrame({
    'key1': ['a', 'a', 'b', 'b', 'a'],
    'key2': ['one', 'two', 'one', 'two', 'one'],
    'data1': np.random.randn(5),
    'data2': np.random.randn(5)
})

print(df)

grouped = df['data1'].groupby(df['key1'])
print(grouped)
print(grouped.mean())
mean = df['data1'].groupby([df['key1'], df['key2']]).mean()
print(mean)
print(mean.unstack())

states = np.array(['Ohio', 'California', 'California', 'Ohio', 'Ohio'])
years = np.array([2005, 2005, 2006, 2005, 2006])

print(df['data1'].groupby([states, years]).mean())
print(df.groupby('key1').mean())
print(df.groupby(['key1', 'key2']).mean())
print(df.groupby(['key1', 'key2']).size())

for name, group in df.groupby('key1'):
    print(name)
    print(group)

for (k1, k2), group in df.groupby(['key1', 'key2']):
    print(k1)
    print(k2)
    print(group)

pieces = dict(list(df.groupby('key1')))
print(pieces['b'])

print(df.dtypes)
grouped = df.groupby(df.dtypes, axis=1)
print(dict(list(grouped)))

print(df.groupby('key1')['data1'])  # equal to df['data1'].groupby(df['data1'])
print(df.groupby('key1')[['data2']])  # eqaul to df[['data2']].groupby(df['key1'])

print(df.groupby(['key1', 'key2'])[['data2']].mean())  # DataFrame
s_grouped = df.groupby(['key1', 'key2'])['data2']  # Series
print(s_grouped.mean())

people = DataFrame(np.random.randn(5, 5),
                   columns=['a', 'b', 'c', 'd', 'e'],
                   index=['Joe', 'Steve', 'Wes', 'Jim', 'Travis'])
people.ix[2:3, ['b', 'c']] = np.nan
print(people)

mapping = {
    'a': 'red',
    'b': 'red',
    'c': 'blue',
    'd': 'blue',
    'e': 'red',
    'f': 'orange'
}

by_column = people.groupby(mapping, axis=1)
print(by_column.sum())

map_series = Series(mapping)
print(map_series)
print(people.groupby(map_series, axis=1).count())

print(people.groupby(len).sum())
key_list = ['one', 'one', 'one', 'two', 'two']
print(people.groupby([len, key_list]).min())

columns = pd.MultiIndex.from_arrays([
    ['US', 'US', 'US', 'JP', 'JP'],
    [1, 3, 5, 1, 3]],
    names=['city', 'tenor'])

hier_df = DataFrame(np.random.randn(4, 5), columns=columns)
print(hier_df)
print(hier_df.groupby(level='city',axis=1).count())