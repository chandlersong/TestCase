import ThinkStat.utils.thinkstats2 as thinkstats2
import numpy as np
import math
from pandas import Series

array = np.array([abs(int(x)) for x in np.random.randn(100) * 30])

pmf = thinkstats2.Pmf(array)
print(pmf)
print(pmf.Mean())
print(pmf.Var())
array = Series(array);
series_pmf = array.value_counts() / 100


# print(series_pmf)


# print(series_pmf.sum())

def PmfMean(array):
    return Series({index: index * value for index, value in array.iteritems()}).sum()


def PmfVar(array):
    mean = PmfMean(array)
    temp = [((index - mean) ** 2) * value for index, value in array.iteritems()]
    return Series(temp).sum()


print("calculate mean:" + str(PmfMean(series_pmf)))
print("calculate Var:" + str(PmfVar(series_pmf)))
