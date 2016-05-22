from pandas_datareader import data as web
from pandas import DataFrame, Series
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from collections import defaultdict

plt.interactive(True)
names = ['AAPL', 'GOOG', 'MSFT', 'DELL', 'GS', 'MS', 'BAC', 'C']


def get_px(stock, start, end):
    print('Get ' + stock)
    return web.get_data_yahoo(stock, start, end)['Adj Close']


px = DataFrame({n: get_px(n, '1/1/2009', '6/1/2012') for n in names})

px = px.asfreq('B').fillna(method='pad')
rets = px.pct_change()
((1 + rets).cumprod() - 1).plot()
print('block')


def calc_mom(price, lookback, lag):
    mon_ret = price.shift(lag).pct_change(lookback)
    ranks = mon_ret.rank(axis=1, ascending=False)
    demeaned = ranks - ranks.mean(axis=1)
    return demeaned / demeaned.std(axis=1)


compound = lambda x: (1 + x).prod() - 1
daily_sr = lambda x: x.mean() / x.std()


def strat_sr(prices, lb, hold):
    freq = '%dB' % hold
    port = calc_mom(prices, lb, lag=1)

    daily_rets = prices.pct_change()

    port = port.shift(1).resample(freq).first()
    returns = daily_rets.resample(freq).apply(compound)
    port_rets = (port * returns).sum(axis=1)

    return daily_sr(port_rets) * np.sqrt(252 / hold)


print(strat_sr(px, 70, 30))

lookbacks = range(20, 90, 5)
holdings = range(20, 90, 5)

dd = defaultdict(dict)

for lb in lookbacks:
    for hold in holdings:
        dd[lb][hold] = strat_sr(px, lb, hold)
ddf = DataFrame(dd)


def heatmap(df, cmap=plt.cm.gray_r):
    fig = plt.figure()
    ax = fig.add_subplot(111)
    axim = ax.imshow(df.values, cmap=cmap, interpolation='nearest')
    ax.set_xlabel(df.columns.name)
    ax.set_xticks(np.arange(len(df.columns)))
    ax.set_xticklabels(list(df.columns))
    ax.set_ylabel(df.index.name)
    ax.set_yticks(np.arange(len(df.index)))
    ax.set_yticklabels(list(df.index))
    plt.colorbar(axim)


heatmap(ddf)
print('block')
