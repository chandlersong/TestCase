L1 = [2,3,4]
L2 = L1[:] # Make a copy of L1 (or list(L1), copy.copy(L1), etc.)
L1[0] = 23
print(L1)
print(L2)

import copy
L2 = copy.copy(L1)
print(L2)
L2 = copy.deepcopy(L1)
print(L2)


L = [1, 2, 3]
M = [1, 2, 3]
print(L == M)
print(L is M)

import sys
print(sys.getrefcount(1))