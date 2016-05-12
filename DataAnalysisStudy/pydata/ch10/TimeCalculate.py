from pandas import DataFrame, Series
import numpy as np
import pandas as pd

p = pd.Period(2007, freq='A-DEC')
print(p)

print(p + 5)
print(p - 2)
print(pd.Period('2014', freq='A-DEC') - p)
rng = pd.period_range('1/1/2000', '6/30/2000', freq='M')
print(rng)
print(Series(np.random.randn(6), index=rng))

values = ['2001Q3', '2002Q2', '2003Q1']
index = pd.PeriodIndex(values, freq='Q-DEC')
print(index)

p = pd.Period('2007', freq='A-DEC')
print(p.asfreq('M', how='start'))
print(p.asfreq('M', how='end'))

p = pd.Period('2007', freq='A-JUN')
print(p.asfreq('M', how='start'))
print(p.asfreq('M', how='end'))

p = pd.Period('2007-08', 'M')
print(p.asfreq('A-JUN'))
rng = pd.period_range('2006', '2009', freq='A-DEC')
ts = Series(np.random.randn(len(rng)), index=rng)
print(ts)
print(ts.asfreq('M', how='start'))
print(ts.asfreq('M', how='end'))

p = pd.Period('2014Q4', freq='Q-JAN')
print(p)
print(p.asfreq('D', 'start'))
print(p.asfreq('D', 'end'))

p4pm = (p.asfreq('B', 'e') - 1).asfreq('T', 's') + 16 * 60
print(p4pm)
print(p4pm.to_timestamp())

rng = pd.period_range('2011Q3', '2012Q4', freq='Q-JAN')
ts = Series(np.arange(len(rng)), index=rng)
new_rng = (rng.asfreq('B', 'e') - 1).asfreq('T', 's') + 16 * 60
ts_index = new_rng.to_timestamp()
