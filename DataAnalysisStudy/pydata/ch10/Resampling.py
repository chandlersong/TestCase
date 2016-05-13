from pandas import DataFrame, Series
import numpy as np
import pandas as pd

rng = pd.date_range('1/1/2000', periods=100, freq='D')

ts = Series(np.random.randn(len(rng)), index=rng)

print(ts[:10])
print(ts.resample('M').mean())
print(ts.resample('M', kind='period').mean())

rng = pd.date_range('1/1/2000', periods=12, freq='T')
ts = Series(np.arange(12), index=rng)
print(ts.resample('5min').sum())
print(ts.resample('5min', closed='left', label='left').sum())
print(ts.resample('5min', closed='right', label='right').sum())

print(ts.resample('5min').ohlc())

rng = pd.date_range('1/1/2000', periods=100, freq='D')
ts = Series(np.arange(100), index=rng)

print(ts.groupby(lambda x: x.month).mean())
print(ts.groupby(lambda x: x.weekday).mean())

frame = DataFrame(np.random.randn(2, 4),
                  index=pd.date_range('1/1/2000', periods=2, freq='W-WED'),
                  columns=['Colorado', 'Texas', 'New York', 'Ohio'])

print(frame[:5])

df_daily = frame.resample('D')
print(df_daily)
print(frame.resample('D').ffill())
print(frame.resample('D').ffill(limit=2))
print(frame.resample('W-THU').ffill())

frame = DataFrame(np.random.randn(24, 4),
                  index=pd.period_range('1-2000', '12-2001', freq='M'),
                  columns=['Colorado', 'Texas', 'New York', 'Ohio'])

print(frame[:5])

annual_frame = frame.resample('A-DEC').mean()
print(annual_frame)
print(annual_frame.resample('Q-DEC').ffill())
print(annual_frame.resample('Q-DEC',convention='start').ffill())
print(annual_frame.resample('Q-MAR').ffill())