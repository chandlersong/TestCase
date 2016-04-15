import json
from collections import defaultdict

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


counts = get_counts(time_zones)
print(counts['America/New_York'])

x=int(3)
print(x)

