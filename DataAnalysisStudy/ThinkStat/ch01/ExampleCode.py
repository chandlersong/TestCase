import ThinkStat.utils.nsfg as nsfg

df = nsfg.ReadFemPreg()
print(df)
print(df.columns)

pregordr = df['pregordr']
print(type(pregordr))

print(df.outcome.value_counts().sort_index())

preg_map = nsfg.MakePregMap(df)
caseid = 10229
indices = preg_map[caseid]
print(indices)
print(df.outcome[indices].values)
