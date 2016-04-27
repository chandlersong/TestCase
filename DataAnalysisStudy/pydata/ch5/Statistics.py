from pandas import Series, DataFrame
import pandas as pd
import pandas.io.data as web
import numpy as np

df = DataFrame([[1.4, np.nan], [7.1, -4.5], [np.nan, np.nan], [0.75, -1.3]],
               index=['a', 'b', 'c', 'd'],
               columns=['one', 'two'])

print(df)
print(df.sum())
print(df.sum(axis=1))
print(df.idxmax())
print(df.cumsum())
print(df.describe())

obj = Series(['a', 'a', 'b', 'c'] * 4)
print(obj.describe())

all_data = {}
for ticker in ['AAPL', 'IBM', 'MSFT', 'GOOG']:
    print("get data:" + ticker)
    all_data[ticker] = web.get_data_yahoo(ticker, '1/1/2010', '1/30/2010')
price = DataFrame({tic: data['Adj Close'] for tic, data in all_data.items()})
volume = DataFrame({tic: data['Volume'] for tic, data in all_data.items()})

returns = price.pct_change()
print(returns.tail())
print(returns.MSFT.corr(returns.IBM))
print(returns.MSFT.cov(returns.IBM))
print(returns.corr())
print(returns.cov())
print(returns.corrwith(returns.IBM))
print(returns.corrwith(volume))

obj = Series(['c', 'a', 'd', 'a', 'a', 'b', 'b', 'c', 'c'])
print(obj.unique())
print(obj.value_counts())
print(pd.value_counts(obj.values, sort=False))
mask = obj.isin(['b', 'c'])
print(mask)
print(obj[mask])

data = DataFrame({
    'QU1': [1, 3, 4, 3, 4],
    'QU2': [2, 3, 1, 2, 3],
    'QU3': [1, 5, 2, 4, 4]
})
print(data.apply(pd.value_counts).fillna(0))
