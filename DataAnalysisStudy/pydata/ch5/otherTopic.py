from pandas import Series, DataFrame
import pandas as pd
import numpy as np
import pandas_datareader.data as web

ser = Series(np.arange(3.))
print(ser)

ser2 = Series(np.arange(3.), index=['a', 'b', 'c'])
print(ser2[-1])

print(ser.ix[:1])

ser3 = Series(range(3), index=[-5, 1, 3])
print(ser3.iloc[2])
print(ser3.iloc[-1])

frame = DataFrame(np.arange(6).reshape(3, 2), index=[2, 0, 1])
print(frame.iloc[0])

pdata = pd.Panel(
    dict((stk, web.get_data_yahoo(stk, '1/1/2010', '1/30/2010')) for stk in ['AAPL', 'IBM', 'MSFT', 'GOOG']))
print(pdata)
print(pdata.ix[:, '1/5/2010', :])
frame = pdata.ix[:, '1/5/2010':, :].to_frame()
print(frame)
print(frame.to_panel())
print('finsih')
