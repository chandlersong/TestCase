from pandas import DataFrame, Series
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt



close_px_all = pd.read_csv('../ch09/stock_px.csv', parse_dates=True, index_col=0)

close_px = close_px_all[['AAPL', 'MSFT', 'XOM']]

close_px = close_px.resample('B').ffill()
print(close_px)

close_px['AAPL'].plot()
plt.show()
print('block')
