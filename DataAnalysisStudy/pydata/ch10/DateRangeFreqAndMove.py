from pandas import DataFrame, Series
import numpy as np
import pandas as pd
from datetime import datetime
from pandas.tseries.offsets import Hour, Minute, Day, MonthEnd

dates = [datetime(2011, 1, 2),
         datetime(2011, 1, 5),
         datetime(2011, 1, 7),
         datetime(2011, 1, 8),
         datetime(2011, 1, 10),
         datetime(2011, 1, 12)]

ts = Series(np.random.randn(6), index=dates)

print(ts)
print(ts.resample('D'))

# generate date range
index = pd.date_range('4/1/2012', '6/1/2012')
print(index)
print(pd.date_range(start='4/1/2012', periods=20))
print(pd.date_range('1/1/2000', '12/1/2000', freq='BM'))
print(pd.date_range('5/2/2012 12:56:31', periods=5, normalize=True))

hour = Hour()
print(hour)

four_hour = Hour(4)
print(four_hour)

print(pd.date_range('1/1/2000', '1/3/2000 23:59', freq='4h'))

print(Hour(2) + Minute(30))

print(pd.date_range('1/1/2000', periods=10, freq='1h30min'))

print(pd.date_range('1/1/2012', '9/1/2012', freq='WOM-3FRI'))

ts = Series(np.random.randn(4),
            index=pd.date_range('1/1/2000', periods=4, freq='M'))
print(ts)
print(ts.shift(2))
print(ts / ts.shift(1) - 1)
print(ts.shift(2, freq='M'))
print(ts.shift(3, freq='D'))
print(ts.shift(1, freq='3D'))

now = datetime(2011, 11, 17)
print(now + 3 * Day())
print(now + MonthEnd())
print(now + MonthEnd(2))

offset = MonthEnd()
print(offset.rollforward(now))
print(offset.rollback(now))

ts = Series(np.random.randn(20),
            index=pd.date_range('1/15/2000', periods=20, freq='4d'))
print(ts)
print(ts.groupby(offset.rollforward).mean())
print(ts.resample('M').mean())

rng = pd.date_range('1/1/2000', periods=3, freq='M')
ts = Series(np.random.randn(3), index=rng)
pts = ts.to_period()
print(ts)
print(pts)

rng = pd.date_range('1/29/2000', periods=6, freq='D')
ts2 = Series(np.random.randn(6), index=rng)
print(ts2.to_period('M'))
pts = ts.to_period()
print(pts)
print(pts.to_timestamp(how='end'))

data = pd.read_csv('macrodata.csv')
print(data.year)
print(data.quarter)

index = pd.PeriodIndex(year=data.year, quarter=data.quarter, freq='Q-DEC')
print(index)
data.index = index;
print(data.infl)
