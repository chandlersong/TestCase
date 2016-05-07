from pandas import DataFrame, Series
import pandas as pd
import json

db = json.load(open('foods-2011-10-03.json'))
print(len(db))
print(db[0].keys())
print(db[0]['nutrients'][0])

nutrients = DataFrame(db[0]['nutrients'])
print(nutrients[:7])

info_keys = ['description', 'group', 'id', 'manufacturer']
info = DataFrame(db, columns=info_keys)
print(pd.value_counts(info.group)[:10])

nutrients = []
for rec in db:
    fnuts = DataFrame(rec['nutrients'])
    fnuts['id'] = rec['id']
    nutrients.append(fnuts)
nutrients = pd.concat(nutrients, ignore_index=True)
print(nutrients[:10])
print(nutrients.duplicated().sum())

nutrients = nutrients.drop_duplicates()

col_mapping = {'description': 'food',
               'group': 'fgroup'}
info = info.rename(columns=col_mapping, copy=False)
print(info[:10])

col_mapping = {
    'description': 'nutrient',
    'group': 'nutgroup'
}
nutrients = nutrients.rename(columns=col_mapping, copy=False)
print(nutrients[:10])

ndata = pd.merge(nutrients, info, on='id', how='outer')
print(ndata[:10])
print(ndata.ix[30000])

result = ndata.groupby(['nutrient', 'fgroup'])['value'].quantile(0.5)
result['Zinc, Zn'].sort_values().plot(kind='barh')
