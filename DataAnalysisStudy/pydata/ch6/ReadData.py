from pandas import DataFrame,Series
import pandas as pd

df = pd.read_csv('ex1.csv')
print(df)

print(pd.read_table('ex1.csv', sep=','))

print(pd.read_csv('ex2.csv', header=None))
print(pd.read_csv('ex2.csv', names=['a', 'b', 'c', 'd', 'message']))

names = ['a', 'b', 'c', 'd', 'message']
print(pd.read_csv('ex2.csv', names=names, index_col='message'))

parsed = pd.read_csv('csv_mindex.csv', index_col=['key1', 'key2'])
print(parsed)

print(pd.read_table('ex3.txt', sep='\s+'))
print(pd.read_table('ex4.csv', skiprows=[0, 2, 3]))

result = pd.read_csv('ex5.csv')
print(result)
print(pd.isnull(result))
print(pd.read_csv('ex5.csv', na_values=['NULL']))

sentinel = {'message': ['foo', 'NA'], 'something': 'two'}
print(pd.read_csv('ex5.csv', na_values=sentinel))

result = pd.read_csv('ex6.csv')
print(result)
print(pd.read_csv('ex6.csv', nrows=5))

chunker = pd.read_csv('ex6.csv',chunksize=1000)
print(chunker)

tot = Series([])
for piece in chunker:
    tot = tot.add(piece['key'].value_counts(),fill_value=0)
tot = tot.sort_values(ascending=False)
print(tot)