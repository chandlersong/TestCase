import numpy as np
from pandas import Series
from collections import defaultdict
array =np.array([abs(int(x)) for x in np.random.randn(100)*30])
print(array)

def allMod(numberArray):
    dict =defaultdict(int)
    for number in numberArray:
        dict[number] += 1
    return dict


allMod = allMod(array)
print(allMod)
array = Series(allMod)
print(array)
print(array.sort_values(ascending=False))
print(array.sort_values(ascending=False).index[0])

