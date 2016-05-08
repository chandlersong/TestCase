from pandas import DataFrame, Series
import numpy as np
import pandas as pd

df = DataFrame({
    'key1': ['a', 'a', 'b', 'b', 'a'],
    'key2': ['one', 'two', 'one', 'two', 'one'],
    'data1': np.random.randn(5),
    'data2': np.random.randn(5)
})
print(df)
grouped = df.groupby('key1')
print(grouped['data1'].quantile(0.9))


def peak_to_peak(arr):
    return arr.max() - arr.min()


print(grouped.agg(peak_to_peak))
print(grouped.describe())

tips = pd.read_csv('tips.csv')
tips['tip_pct'] = tips['tip'] / tips['total_bill']
print(tips[:6])

grouped = tips.groupby(['sex', 'smoker'])
grouped_pct = grouped['tip_pct']
print(grouped_pct.agg('mean'))
print(grouped_pct.agg(['mean', 'std', peak_to_peak]))
print(grouped_pct.agg([('foo', 'mean'), ('bar', np.std)]))

functions = ['count', 'mean', 'max']
result = grouped['tip_pct', 'total_bill'].agg(functions)
print(result)
print(result['tip_pct'])

ftuples = [
    ('Durchschnitt', 'mean'),
    ('Abweichung', np.var)
]
print(grouped['tip_pct', 'total_bill'].agg(ftuples))
print(grouped.agg({'tip': np.max, 'size': 'sum'}))
print(grouped.agg({
    'tip_pct': ['min', 'max', 'mean', 'std'],
    'size': sum
}))

print(tips.groupby(['sex','smoker'],as_index=False).mean())