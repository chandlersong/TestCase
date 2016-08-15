from pandas import DataFrame, Series
import numpy as np
import pandas as pd
import statsmodels.api as sm

df = DataFrame({
    'key1': ['a', 'a', 'b', 'b', 'a'],
    'key2': ['one', 'two', 'one', 'two', 'one'],
    'data1': np.random.randn(5),
    'data2': np.random.randn(5)
})

print(df)
k1_means = df.groupby('key1').mean().add_prefix('mean_')
print(k1_means)

print(pd.merge(df, k1_means, left_on='key1', right_index=True))

key = ['one', 'two', 'one', 'two', 'one']
people = DataFrame(np.random.randn(5, 5),
                   columns=['a', 'b', 'c', 'd', 'e'],
                   index=['Joe', 'Steve', 'Wes', 'Jim', 'Travis'])
print(people)
print(people.groupby(key).mean())
print(people.groupby(key).transform(np.mean))


def demean(arr):
    return arr - arr.mean()


demeaned = people.groupby(key).transform(demean)
print(demeaned)
print(demeaned.groupby(key).mean())

tips = pd.read_csv('tips.csv')
tips['tip_pct'] = tips['tip'] / tips['total_bill']


def top(df, n=5, column='tip_pct'):
    return df.sort_values(by=column)[-n:]

print("unittest apply")



def printGroupBy(groupby):
    for name, group in groupby:
        print(name)
        print(group)

print("show smoker group by")
smoker_groupby = tips.groupby('smoker')
printGroupBy(smoker_groupby)
print(top(tips, n=6))

print(smoker_groupby.apply(top))
print("show smoker day group by")
smoker_day_groupby = tips.groupby(['smoker', 'day'])
printGroupBy(smoker_day_groupby)
print(smoker_day_groupby.apply(top, n=1, column='total_bill'))

result = tips.groupby('smoker')['tip_pct'].describe()
print(result)
print(result.unstack('smoker'))
print(tips.groupby('smoker', group_keys=False).apply(top))

frame = DataFrame(
    {'data1': np.random.randn(1000),
     'data2': np.random.randn(1000)}
)

factor = pd.cut(frame.data1, 4)
print(factor[:10])


def get_stats(group):
    return {
        'min': group.min(),
        'max': group.max(),
        'count': group.count(),
        'mean': group.mean()
    }


grouped = frame.data2.groupby(factor)
print(grouped)

print(grouped.apply(get_stats).unstack())

grouping = pd.qcut(frame.data1, 10, labels=False)
grouped = frame.data2.groupby(grouping)
print(grouped.apply(get_stats).unstack())

s = Series(np.random.randn(6))
s[::2] = np.nan
print(s)
print(s.fillna(s.mean()))

states = ['Ohio', 'New York', 'Vermont', 'Florida', 'Oregon', 'Nevada', 'California', 'Idaho']
grouped_key = ['East'] * 4 + ['West'] * 4
data = Series(np.random.randn(8), index=states)
data[['Vermont', 'Nevada', 'Idaho']] = np.nan
print(data)
print(data.groupby(grouped_key).mean())

fill_mean = lambda g: g.fillna(g.mean())

print(data.groupby(grouped_key).apply(fill_mean))

fill_values = {'East': 0.5, 'West': -1}
fill_func = lambda g: g.fillna(fill_values[g.name])
print(data.groupby(grouped_key).apply(fill_func))

suits = ['H', 'S', 'C', 'D']
print('aa')
print(np.arange(1, 11))
print([10] * 3)
card_val = (list(range(1, 11)) + [10] * 3) * 4
base_means = ['A'] + list(range(2, 11)) + ['J', 'K', 'Q']
cards = []
for suit in suits:
    cards.extend(str(num) + suit for num in base_means)
deck = Series(card_val, index=cards)

print(deck[:13])


def draw(deck, n=5):
    return deck.take(np.random.permutation(len(deck))[:n])


print(draw(deck))

get_suit = lambda card: card[-1]

print(deck.groupby(get_suit).apply(draw, n=2))
print(deck.groupby(get_suit, group_keys=False).apply(draw, n=2))

df = DataFrame({
    'category': ['a'] * 4 + ['b'] * 4,
    'data': np.random.randn(8),
    'weights': np.random.randn(8)
})

print(df)
grouped = df.groupby('category')
get_wavg = lambda g: np.average(g['data'], weights=g['weights'])
print(grouped.apply(get_wavg))

close_px = pd.read_csv('stock_px.csv', parse_dates=True, index_col=0)
print(close_px)
print(close_px[-4:])

rets = close_px.pct_change().dropna()
spx_corr = lambda x: x.corrwith(x['SPX'])
by_year = rets.groupby(lambda x: x.year)
print(by_year.apply(spx_corr))

print(by_year.apply(lambda g: g['AAPL'].corr(g['MSFT'])))


def regress(data, yvar, xvars):
    Y = data[yvar]
    X = data[xvars]
    X['intercept'] = 1
    result = sm.OLS(Y, X).fit()
    return result.params


print(by_year.apply(regress, 'AAPL', ['SPX']))
