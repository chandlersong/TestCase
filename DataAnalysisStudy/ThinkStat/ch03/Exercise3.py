import ThinkStat.utils.nsfg as nsfg
import pandas as pd

data = nsfg.ReadFemPreg('../ch01/2002FemPreg.dct', '../ch01/2002FemPreg.dat.gz')

lives = data[data.outcome == 1]

pregMap = nsfg.MakePregMap(lives)

firsts = lives[lives.birthord == 1]
others = lives[lives.birthord != 1]

others_mean = others['prglngth'].groupby(others['caseid']).mean()
print(others_mean)
firsts = firsts[['prglngth']].set_index(firsts['caseid'])
total = pd.concat([firsts, others_mean], axis=1,join='inner',keys=['first','other'])

total['diff'] = total['first']-total['other']

print(total['diff'].mean())
print(total['diff'].var())


