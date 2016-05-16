import random;
import string
from pandas import DataFrame, Series
import pandas as pd
import numpy as np
from pandas_datareader import data as web
import matplotlib.pyplot as plt
import datetime

plt.interactive(True)
random.seed(0)


def rands(n):
    choices = string.ascii_uppercase
    return ''.join([random.choice(choices) for _ in range(n)])


N = 1000
tickers = np.array([rands(5) for _ in range(N)])

M = 500
df = DataFrame(
    {'Momentum': np.random.randn(M) / 200 + 0.03,
     'Value': np.random.randn(M) / 200 + 0.08,
     'ShortInterest': np.random.rand(M) / 200 - 0.02},
    index=tickers[:M]
)

ind_names = np.array(['FINANCIAL', 'TECH'])
sampler = np.random.randint(0, len(ind_names), N)
industries = Series(ind_names[sampler], index=tickers, name='industry')

by_industry = df.groupby(industries)
print(by_industry.mean())
print(by_industry.describe())


def zscore(group):
    return (group - group.mean()) / group.std()


df_stand = by_industry.apply(zscore)
print(df_stand.groupby(industries).agg(['mean', 'std']))
ind_rank = by_industry.rank(ascending=False)
print(ind_rank.groupby(industries).agg(['min', 'max']))
print(by_industry.apply(lambda x: zscore(x.rank())))

fac1, fac2, fac3 = np.random.rand(3, 1000)
ticker_subset = tickers.take(np.random.permutation(N)[:1000])
port = Series(0.7 * fac1 + 1.2 * fac2 + 0.3 * fac3 + np.random.rand(1000), index=ticker_subset)

factors = DataFrame({'f1': fac1, 'f2': fac2, 'f3': fac3}, index=ticker_subset)

print(factors.corrwith(port))
print(pd.ols(y=port, x=factors).beta)


def beta_exposure(chunk, factors=None):
    return pd.ols(y=chunk, x=factors).beta


by_ind = port.groupby(industries)
exposures = by_ind.apply(beta_exposure, factors=factors)
print(exposures.unstack())

data = web.get_data_yahoo('SPY', '2006-01-01')
print(data)

px = data['Adj Close']
returns = px.pct_change()


def to_index(rets):
    index = (1 + rets).cumprod()
    first_loc = max(index.notnull().index.get_loc(index.notnull().argmax()) - 1, 0)
    index.values[first_loc] = 1
    return index


def trend_signal(rets, lookback, lag):
    signal = Series.rolling(rets, lookback, min_periods=lookback - 5).sum()
    return signal.shift(lag)


signal = trend_signal(returns, 100, 3)
print(signal)
trade_friday = signal.resample('W-FRI')
trade_friday = trade_friday.resample('B').ffill()
print(trade_friday)
trade_rets = trade_friday.shift(1) * returns

to_index(trade_rets).plot()

print('block')

vol = pd.rolling_std(returns, 250, min_periods=200) * np.sqrt(250)


def shape(rets, ann=250):
    return rets.mean() / rets.std() * np.sqrt(ann)

print(trade_rets)
print(pd.qcut(vol, 4))


# print(trade_rets.groupby(pd.qcut(vol, 4)).agg(shape))
