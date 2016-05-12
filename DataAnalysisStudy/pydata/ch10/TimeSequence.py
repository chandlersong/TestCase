from pandas import DataFrame, Series
import numpy as np
import pandas as pd
from datetime import datetime

dates = [datetime(2011, 1, 2),
         datetime(2011, 1, 5),
         datetime(2011, 1, 7),
         datetime(2011, 1, 8),
         datetime(2011, 1, 10),
         datetime(2011, 1, 12)]

ts = Series(np.random.randn(6), index=dates)
print(ts)
print(type(ts))
print(ts.index)
print(ts + ts[::2])

print(ts.index.dtype)
stamp = ts.index[0]
print(stamp)

stamp = ts.index[2]
print(ts[stamp])
print(ts['1/10/2011'])
print(ts['20110110'])

longer_ts = Series(np.random.randn(1000),
                   index=pd.date_range('1/1/2000', periods=1000))
print(longer_ts)
print(longer_ts['2001'])
print(longer_ts['2001-05'])
print(ts[datetime(2011, 1, 7):])
print(ts)
print(ts['1/6/2011':'1/9/2011'])
print(ts.truncate(after='1/9/2011'))

dates = pd.date_range('1/1/2000', periods=100, freq='W-WED')
print(dates)
long_df = DataFrame(np.random.randn(100, 4), index=dates, columns=['Colorado', 'Texas', 'New York', 'Ohio'])
print(long_df.ix['5-2001'])

dates = pd.DatetimeIndex(['1/1/2000', '1/2/2000', '1/2/2000', '1/2/2000', '1/3/2000'])
dup_ts = Series(np.arange(5), index=dates)
print(dup_ts)
print(dup_ts.index.is_unique)
print(dup_ts['1/3/2000'])
print(dup_ts['1/2/2000'])

grouped = dup_ts.groupby(level=0)
print(grouped.mean())
print(grouped.count())
