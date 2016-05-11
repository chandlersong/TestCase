from pandas import DataFrame, Series
import pandas as pd
import numpy as np

data = DataFrame({
    'k1': ['one'] * 3 + ['two'] * 4,
    'k2': [1, 1, 2, 3, 3, 4, 4]
})

print(data)
print(data.duplicated())
print(data.drop_duplicates())

data['v1'] = range(7)
print(data.drop_duplicates(['k1']))
print(data.drop_duplicates(['k1', 'k2'], keep='last'))

data = DataFrame({
    'food': ['bacon', 'pulled pork', 'bacon', 'Pastrami', 'corned beef', 'Bacon', 'Pastrami', 'honey ham', 'nova lox'],
    'ounces': [4, 3, 12, 6, 7.5, 8, 3, 5, 6]
})

print(data)
meat_to_animal = {
    'bacon': 'pig',
    'pulled pork': 'pig',
    'pastrami': 'cow',
    'corned beef': 'cow',
    'honey ham': 'pig',
    'nova lox': 'salmon'
}

data['animal'] = data['food'].map(str.lower).map(meat_to_animal)
print(data)
print(data['food'].map(lambda x: meat_to_animal[x.lower()]))

data = Series([1., -999., 2., -999., -1000., 3.])
print(data)
print(data.replace(-999, np.nan))
print(data.replace([-999, -1000], np.nan))
print(data.replace([-999, -1000], [np.nan, 0]))
print(data.replace({-999: np.nan, -1000: 0}))

data = DataFrame(np.arange(12).reshape((3, 4)),
                 index=['ohio', 'Colorado', 'New York'],
                 columns=['one', 'two', 'three', 'four'])
print(data.index.map(str.upper))

data.index = data.index.map(str.upper)
print(data)
print(data.rename(index=str.title, columns=str.upper))
print(data.rename(index={'OHIO': 'INDIANA'},
                  columns={'three': 'peekaboo'}))
_ = data.rename(index={'OHIO': 'INDIANA'}, inplace=True)
print(data)

ages = [20, 22, 25, 27, 21, 23, 37, 31, 61, 45, 41, 32]
bins = [18, 25, 35, 60, 100]
cats = pd.cut(ages, bins)
print(cats)
print(cats.codes)
print(cats.categories)
print(pd.value_counts(cats))

print(pd.cut(ages, [18, 26, 36, 61, 100], right=False))
group_names = ['Youth', 'YoungAdult', 'MiddleAged', 'Senior']
print(pd.cut(ages, bins, labels=group_names))

data = np.random.randn(20)
print(pd.cut(data, 4, precision=2))
print(pd.value_counts(pd.cut(data, 4, precision=2)))

data = np.random.randn(1000)
cats = pd.qcut(data, 4)
print(cats)
print(pd.value_counts(cats))
cats = pd.qcut(data, [0, 0.1, 0.5, 0.9, 1])
print(cats)
print(pd.value_counts(cats))

np.random.seed(12345)
data = DataFrame(np.random.randn(1000, 4))
print(data.describe())

col = data[3]
print(col[np.abs(col) > 3])

print(data[(np.abs(data) > 3).any(1)])

data[np.abs(data) > 3] = np.sign(data) * 3
print(np.sign(data) * 3)
print(data[np.abs(data) > 3])
print(np.abs(data))
print(data.describe())

df = DataFrame(np.arange(5 * 4).reshape((5, 4)))
sampler = np.random.permutation(5)
print(sampler)
print(df)
print(df.take(sampler))
print(df.take(np.random.permutation(len(df))[:3]))

bag = np.array([5, 7, -1, 6, 4])
sampler = np.random.randint(0, len(bag), size=10)
print(sampler)
draws = bag.take(sampler)
print(draws)

df = DataFrame({
    'key': ['b', 'b', 'a', 'c', 'a', 'b'],
    'data1': range(6)
})
print(df)
print(pd.get_dummies(df['key']))

dummies = pd.get_dummies(df['key'], prefix='key')
print(dummies)
df_with_dummy = df[['data1']].join(dummies)

mnames = ['movie_id', 'title', 'genres']
movies = pd.read_table('movies.dat', sep='::', header=None, names=mnames)

print(movies[:10])

genre_iter = (set(x.split('|')) for x in movies.genres)
genres = sorted(set.union(*genre_iter))
print(genres)

dummies = DataFrame(np.zeros((len(movies), len(genres))), columns=genres)
print(dummies)

for i, gen in enumerate(movies.genres):
    dummies.ix[i, gen.split('|')] = 1
movies_windic = movies.join(dummies.add_prefix('Genre_'))
print(movies_windic)
print(movies_windic.ix[0])

values = np.random.rand(10)
print(values)
bins = [0,0.2,0.4,0.6,0.8,1]
print(pd.get_dummies(pd.cut(values,bins)))