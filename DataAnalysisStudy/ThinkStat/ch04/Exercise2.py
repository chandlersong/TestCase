import random
from pandas import Series
import matplotlib.pyplot as plt
array =Series( [random.random() for _ in range(1000)])

# print(array)

pmf = array.value_counts()
print(pmf)

pmf.plot(kind='bar',linewidth=0.1)
plt.show()

cdf = array.sort_values()
cdf.index = range(1,len(cdf) + 1)
# print(cdf)
cdf.plot()
plt.show()
