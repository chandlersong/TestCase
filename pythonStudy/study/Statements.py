nudge = 1
wink  = 2
A, B = nudge, wink         # Tuple assignment
print(A, B)
[C, D] = [nudge, wink]   # List assignment
print(C, D)

(a, b, c) = "ABC" # Assign string of characters to tuple
print(a, c)

seq = [1,2,3,4,5]
a,*b = seq
print(a)
print(b)

print(2 or 3, 3 or 2)   # Return 2. because it stop at 2
print([] or 3)
print([] or {})  # Return {}. because it stop at {}

A = 't' if 'spam' else 'f'
print(A)
A = 't' if '' else 'f'
print(A)

print(['f', 't'][bool('')])
print(['f', 't'][bool('spam')])

default = "default"
X = A or default
print(X)
A = None
X = A or default
print(X)

T = [(1, 2), (3, 4), (5, 6)]
for (a, b) in T:
    print(a,b)
for both in T:
    a, b = both
    print(a, b)

D = {'a': 1, 'b': 2, 'c': 3}
for key in D:
    print(key, '=>', D[key])

for (key, value) in D.items():
    print(key, '=>', value)


L1 = [1,2,3,4]
L2 = [5,6,7,8]

for (x, y) in zip(L1, L2):
    print(x, y, '--', x+y)
print(list(zip(L1, L2)))

S = 'spam'
for (offset, item) in enumerate(S):
    print(item, 'appears at offset', offset)

E = enumerate(S)
print(next(E))