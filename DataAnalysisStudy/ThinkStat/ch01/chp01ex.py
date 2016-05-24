import ThinkStat.utils.nsfg as nsfg
from collections import defaultdict
df = nsfg.ReadFemPreg()
pregnum = df['pregnum']
print(pregnum)

def GetPregNumMap(df):
    """Make a map from caseid to list of preg indices.

    df: DataFrame

    returns: dict that maps from caseid to list of indices into preg df
    """
    d = defaultdict(list)
    for index, caseid in df.caseid.iteritems():
            d[caseid].append(df.pregnum[index])
    return d


pregNumMap = GetPregNumMap(df)
caseid = 10229
indices = pregNumMap[caseid]
print(df.pregnum[indices].values)

# answer
def ValidatePregnum(resp):
    """Validate pregnum in the respondent file.

    resp: respondent DataFrame
    """
    # read the pregnancy frame
    preg = nsfg.ReadFemPreg()

    # make the map from caseid to list of pregnancy indices
    preg_map = nsfg.MakePregMap(preg)

    # iterate through the respondent pregnum series
    for index, pregnum in resp.pregnum.iteritems():
        caseid = resp.caseid[index]
        indices = preg_map[caseid]

        # check that pregnum from the respondent file equals
        # the number of records in the pregnancy file
        if len(indices) != pregnum:
            print(caseid, len(indices), pregnum)
            return False

    return True