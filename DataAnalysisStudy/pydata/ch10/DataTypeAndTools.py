from datetime import datetime
from datetime import timedelta
from dateutil.parser import parse
import pandas as pd

now = datetime.now()
print(now)
print((now.year, now.month, now.day))

delta = datetime(2011, 1, 7) - datetime(2008, 6, 24, 8, 15)
print(delta)
print(delta.days)
print(delta.seconds)

start = datetime(2011, 1, 7)
print(start + timedelta(12))  # add day
print(start - 2 * timedelta(12))

stamp = datetime(2011, 1, 3)
print(str(stamp))
print(stamp.strftime('%Y-%m-%d'))

value = '2011-01-03'
print(datetime.strptime(value, '%Y-%m-%d'))

datestrs = ['7/6/2011', '8/6/2011']
print([datetime.strptime(x, '%m/%d/%Y') for x in datestrs])

print(parse('2011-01-03'))
print(parse('Jan 31, 1997 10:45 PM'))
print(parse('6/12/2011', dayfirst=True))
print(pd.to_datetime(datestrs))

idx = pd.to_datetime(datestrs + [None])
print(idx)
print(idx[2])
print(pd.isnull(idx))