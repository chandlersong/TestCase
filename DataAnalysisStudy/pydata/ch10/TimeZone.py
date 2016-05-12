import pytz
from pandas import DataFrame, Series
import numpy as np
import pandas as pd
from pandas.tseries.offsets import Hour

print(pytz.common_timezones[-5:])
tz = pytz.timezone('US/Eastern')
print(tz)

rng = pd.date_range('3/9/2012 9:30', periods=6, freq='D')
ts = Series(np.random.randn(len(rng)), index=rng)

print(ts.index.tz)

print(pd.date_range('3/9/2012 9:30', periods=10, freq='D', tz='UTC'))

ts_utc = ts.tz_localize('UTC')
print(ts_utc)
print(ts_utc.index)
print(ts_utc.tz_convert('US/Eastern'))

ts_eastern = ts.tz_localize('US/Eastern')
print(ts_eastern.tz_convert('UTC'))
print(ts_eastern.tz_convert('Europe/Berlin'))
print(ts.index.tz_localize('Asia/Shanghai'))

stamp = pd.Timestamp('2011-03-12 04:00')
stamp_utc = stamp.tz_localize('utc')
print(stamp_utc.tz_convert('US/Eastern'))
stamp_moscow = pd.Timestamp('2011-03-12 04:00', tz='Europe/Moscow')
print(stamp_moscow)
print(stamp_utc.value)
print(stamp_utc.tz_convert('US/Eastern').value)

stamp = pd.Timestamp('2012-03-12 01:30', tz='US/Eastern')
print(stamp)
print(stamp + Hour())

stamp = pd.Timestamp('2012-11-04 00:30', tz='US/Eastern')
print(stamp)
print(stamp + 2 * Hour())

rng = pd.date_range('3/7/2012 9:30', periods=10, freq='B')
ts = Series(np.random.randn(len(rng)), index=rng)
print(ts)

ts1 = ts[:7].tz_localize('Europe/London')
ts2 = ts[2:].tz_localize('Europe/Moscow')
result = ts1 + ts2
print(result.index)
