from pandas import DataFrame, Series
import pandas as pd
import numpy as np
from datetime import time
from pandas_datareader import data as web

price = pd.read_csv('stock_px.csv',
                    usecols=[0, 2, 5, 8, 9],
                    parse_dates=True,
                    index_col=0,
                    skiprows=range(1, 5444), nrows=7)

volume = pd.read_csv('volume.csv',
                     usecols=[0, 2, 5, 9],
                     parse_dates=True,
                     index_col=0,
                     skiprows=range(1, 5444), nrows=5)

print(price)
print(volume)

print(price * volume)

vwap = (price * volume).sum() / volume.sum()

print(vwap)
print(vwap.dropna())
print(price.align(volume, join='inner'))

s1 = Series(range(3), index=['a', 'b', 'c'])
s2 = Series(range(4), index=['d', 'b', 'c', 'e'])
s3 = Series(range(3), index=['f', 'a', 'c'])

print(DataFrame({
    'One': s1, 'Two': s2, 'three': s3
}))

print(DataFrame({
    'One': s1, 'Two': s2, 'three': s3
}, index=list('face')))

ts1 = Series(np.random.randn(3),
             index=pd.date_range('2012-6-13', periods=3, freq='W-WED'))
print(ts1)
print(ts1.resample('B'))
print(ts1.resample('B').ffill())

dates = pd.DatetimeIndex(['2012-6-12', '2012-6-17', '2012-6-18',
                          '2012-6-21', '2012-6-22', '2012-6-29'])
ts2 = Series(np.random.randn(6), index=dates)
print(ts2)

print(ts1.reindex(ts2.index, method='ffill'))
print(ts2 + ts1.reindex(ts2.index, method='ffill'))

gdp = Series([1.78, 1.94, 2.08, 2.01, 2.15, 2.31, 2.46],
             index=pd.period_range('1984Q2', periods=7, freq='Q-SEP'))
inf1 = Series([0.025, 0.045, 0.037, 0.04],
              index=pd.period_range('1982', periods=4, freq='A-DEC'))

inf1_q = inf1.asfreq('Q-SEP', how='end')
print(inf1_q)
print(inf1_q.reindex(gdp.index, method='ffill'))

rng = pd.date_range('2012-06-01 09:30', '2012-06-01 15:59', freq='T')
rng = rng.append([rng + pd.offsets.BDay(i) for i in range(1, 4)])

ts = Series(np.arange(len(rng), dtype=float), index=rng)
print(ts)
print(ts[time(10, 0)])
print(ts.at_time(time(10, 0)))
print(ts.between_time(time(10, 0), time(10, 1)))

indexer = np.sort(np.random.permutation(len(ts))[700:])
irr_ts = ts.copy()
irr_ts[indexer] = np.nan
print(irr_ts['2012-06-01 09:50':'2012-06-01 10:00'])

selection = pd.date_range('2012-06-01 10:00', periods=4, freq='B')
print(irr_ts.asof(selection))

data1 = DataFrame(np.ones((6, 3), dtype=float),
                  columns=['a', 'b', 'c'],
                  index=pd.date_range('6/12/2012', periods=6))
data2 = DataFrame(np.ones((6, 3), dtype=float) * 2,
                  columns=['a', 'b', 'c'],
                  index=pd.date_range('6/13/2012', periods=6))

print(data1)
print(data2)
spliced = pd.concat([data1.ix[:'2012-06-14'], data2.ix['2012-06-15':]])
print(spliced)

data2 = DataFrame(np.ones((6, 4), dtype=float) * 2,
                  columns=['a', 'b', 'c', 'd'],
                  index=pd.date_range('6/13/2012', periods=6))
spliced = pd.concat([data1.ix[:'2012-06-14'], data2.ix['2012-06-15':]])
print(spliced)

spliced_filled = spliced.combine_first(data2)
print(spliced_filled)
spliced.update(data2, overwrite=False)

cp_spliced = spliced.copy()
cp_spliced[['a', 'c']] = data1[['a', 'c']]
print(cp_spliced)

price = web.get_data_yahoo('AAPL', '2011-01-01')['Adj Close']
print(price[-5:])

print(price['2011-10-03'] / price['2011-3-01'] - 1)

returns = price.pct_change()
ret_index = (1 + returns).cumprod()
ret_index[0] = 1
print(ret_index)

m_return = ret_index.resample('BM').last().pct_change()
print(m_return['2012'])
m_rets = (1 + returns).resample('M', kind='period').prod() - 1
print(m_rets)
