from pandas import DataFrame, Series
import numpy as np
import pandas as pd
import statsmodels.api as sm

tips = pd.read_csv('tips.csv')
tips['tip_pct'] = tips['tip'] / tips['total_bill']

print(tips.pivot_table(index=['sex', 'smoker']))
print(tips.pivot_table(['tip_pct', 'size'], index=['sex', 'day'], columns='smoker'))
print(tips.pivot_table(['tip_pct', 'size'], index=['sex', 'day'], columns='smoker', margins=True))
print(tips.pivot_table('tip_pct', index=['sex', 'smoker'], columns='day', margins=True))
print(tips.pivot_table('tip_pct', index=['sex', 'smoker'], columns='day', aggfunc=len, margins=True))
print(tips.pivot_table('size', index=['time', 'sex', 'smoker'], columns='day', aggfunc='sum'))
print(tips.pivot_table('size', index=['time', 'sex', 'smoker'], columns='day', aggfunc='sum', fill_value=0))

data = DataFrame({
    'Sample': np.arange(1, 11),
    'Gender': ['Female', 'Male', 'Female', 'Male', 'Male', 'Male', 'Female', 'Female', 'Male', 'Female'],
    'Handedness': ['Right-handed', 'Left-handed', 'Right-handed', 'Right-handed', 'Left-handed', 'Right-handed',
                   'Right-handed', 'Left-handed', 'Right-handed', 'Right-handed']
})
print(data)
print(pd.crosstab(data.Gender, data.Handedness, margins=True))
print(pd.crosstab([tips.time, tips.day], tips.smoker, margins=True))
