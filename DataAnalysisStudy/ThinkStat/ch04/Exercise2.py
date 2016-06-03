import random
from pandas import Series
import matplotlib.pyplot as plt
array =Series( [random.random() for _ in range(1000)])

# print(array)

pmf = array.value_counts()
# print(pmf)

# pmf.hist(bins=50)
# plt.show()u
temp = pmf.sort_index()
cdf = temp.cumsum() / temp.sum()
# print(cdf)
cdf.plot()
plt.show()


def calculateCdf(series_array):
    pmf = series_array.value_counts()
    temp = pmf.sort_index()
    return temp.cumsum() / temp.sum()


test = Series([1,2,2,3,5])
test_cdf = calculateCdf(test)
print(test_cdf)