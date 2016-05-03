from pandas import DataFrame, Series
import pandas as pd
import numpy as np
import datetime

data = DataFrame(np.arange(6).reshape((2, 3)),
                 index=pd.Index(['Ohio', 'Colorado'], name='state'),
                 columns=pd.Index(['one', 'two', 'three'], name='number'))
print(data)

result = data.stack()
print(result)
print(result.unstack())
print(result.unstack(0))
print(result.unstack('state'))

s1 = Series([0, 1, 2, 3], index=['a', 'b', 'c', 'd'])
s2 = Series([4, 5, 6], index=['c', 'd', 'e'])

data2 = pd.concat([s1, s2], keys=['one', 'two'])

print(data2)
print(data2.unstack())
print(data2.unstack().stack())
print(data2.unstack().stack(dropna=False))

df = DataFrame({'left': result, 'right': result + 5},
               columns=pd.Index(['left', 'right'], name='side'))
print(df)
print(df.unstack('state'))
print(df.unstack('state').stack('side'))

quarterConst = {
    '1.0': [3, 31],
    '2.0': [6, 30],
    '3.0': [9, 30],
    '4.0': [12, 31]
}


def dataPicker(year, quarter):
    quarterEntiy = quarterConst.get(quarter)
    return datetime.datetime(int(year[:4]), quarterEntiy[0], quarterEntiy[1], 0, 0, 0, 0)


ldata = pd.read_csv('macrodata.csv',
                    usecols=['year', 'quarter', 'realgdp', 'infl', 'unemp'],
                    parse_dates={'date': ['year', 'quarter']}, date_parser=dataPicker, index_col='date')
print(ldata)
print(ldata.stack())
ldata = DataFrame(ldata.stack()).swaplevel(0, 1)
ldata.reset_index(level=0, inplace=True)
ldata.reset_index(level=0, inplace=True)
ldata.columns = pd.Index(['date', 'item', 'value'])
print(ldata)

print(ldata[:10])

pivoted = ldata.pivot('date', 'item', 'value')
print(pivoted)
print(pivoted.head())

ldata['value2'] = np.random.randn(len(ldata))
print(ldata[:10])
pivoted = ldata.pivot('date', 'item')
print(pivoted[:5])

unstacked = ldata.set_index(['date', 'item']).unstack('item')
print(ldata.set_index(['date', 'item']))
print(unstacked[:7])
