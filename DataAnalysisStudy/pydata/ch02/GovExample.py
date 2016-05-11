import json
from collections import defaultdict
from collections import Counter
from pandas import DataFrame, Series
import pandas as pd
import numpy as np

path = "pydata_usagov_bitly_data2012-03-16-1331923249.txt"
records = [json.loads(line) for line in open(path)]
print(records[0])
print(records[0]['tz'])

time_zones = [rec['tz'] for rec in records if 'tz' in rec]
print(len(time_zones))


def get_counts(sequence):
    x = int(3)
    seq_counts = defaultdict(int)  # all the value will be initialize to 0
    for x in sequence:
        seq_counts[x] += 1
    return seq_counts


def top_counts(count_dict, n=10):
    value_key_pairs = [(count, tz) for tz, count in count_dict.items()]
    value_key_pairs.sort()
    return value_key_pairs[-n:]


counts = get_counts(time_zones)
print(counts['America/New_York'])
print(top_counts(counts))

counts = Counter(time_zones)
print(counts.most_common(10))
print(counts)

frame = DataFrame(records)
# print(frame)
print(frame['tz'][:10])
tz_counts = frame['tz'].value_counts()
print(tz_counts[:10])

clean_tz = frame['tz'].fillna('Missing')
clean_tz[clean_tz == '']='Unknow'
tz_counts = clean_tz.value_counts()
print(tz_counts)

tz_counts[:10].plot(kind='barh',rot=0)

results = Series([x.split()[0] for x in frame.a.dropna()])
print(results[:5])
print(results.value_counts()[:8])

cframe = frame[frame.a.notnull()]
operating_system = np.where(cframe['a'].str.contains('Windows'),'windows','Not windows')
print(operating_system[:5])
by_tz_os = cframe.groupby(['tz',operating_system])
agg_counts = by_tz_os.size().unstack().fillna(0)
print(agg_counts[:10])
indexer = agg_counts.sum(1).argsort()
print(indexer[:10])

count_subset = agg_counts.take(indexer)[-10:]
print(count_subset)
count_subset.plot(kind='barh',stacked = True)