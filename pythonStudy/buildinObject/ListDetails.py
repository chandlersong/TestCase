print(len([1,2,3]))
print([1,2,3]+[4,5,6])
print([1]*4)

res = [c * 4 for c in 'SPAM']
print(res)

res = []
for c in 'SPAM':
    res.append(c*4)
print(res)

L = [1,2,3]
#  first delete from offset 1, up to but not including offset 2 and insert into the [8.9]
L[1:2] = [8,9]  # insert at position 1 and offset is 2. delete form positon 1 and range is 2
print(L)
L = [1,2,3]
L[1:3] = [8,9]
print(L)
L = [1,2,3]
L[1:5] = [8,9]
print(L)
L = [1,2,3]
L[1:1] = [8,9]
print(L)
L = [1,2,3]
L[:0] = [2, 3, 4]
print(L)

L = ['abc', 'ABD', 'aBe']
L.sort()
print(L)
L.sort(key=str.lower) # lower is a mehotd. each item in list will be called by the method and then sort
print(L)
L.sort(key=str.lower, reverse=True)
print(L)
print(str.lower)
print(['spam', 'toast', 'ham'].pop(1))

L = ['spam', 'eggs', 'ham', 'toast']
del L[0]
print(L)

D = {'spam': 2, 'ham': 1, 'eggs': 3}
print(D['spam'])
print('ham' in D)
print(1 in D)
keyList = list(D.keys())
print(keyList)
print(D.keys())
print(keyList == D.keys())
print(keyList is D.keys())

D[2] = 'test'        # add new entry
print(D)
D[2] = 'changed'
print(D)
del  D[2]
print(D)
print(D.items())
print(D.values())
print(D.get('spam'))
print(D.get('notExist',88))  # 88 is default value

D2 = {'toast':4, 'muffin':5} # Lots of delicious scrambled order here
D.update(D2)
print(D)
D3 ={ 'eggs': 6}
D.update(D3)
print(D)
print(D.pop('toast'))       # pop will delete
print(D)
print([key for (key, value) in D.items() if value == 2])    # get key by value
print([key for key in D.keys() if D[key] == 2])             # get key by value

Matrix = {}
try:
    print(Matrix[(2,3,6)])                        # Get is mumore save
except KeyError:
    print(0)

D = dict.fromkeys(['a', 'b', 'c'], 0)
print(D)
