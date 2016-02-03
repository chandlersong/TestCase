L = [123,"example",2.3]
print(len(L))
print(L[0])
print(L[:2])  # from 0 to 3
L+['a','b']
print(L)
L * 2
print(L)
print(L * 2)


M=[[1,2,3],[4,5,6],[7,8,9]]
print(M[1])
print(M[2][1])
print([row[1] for row in M])           # each second item in the list
print([row[1] for row in M if row[1] % 2 == 0])
print([M[i][i] for i in [0, 1, 2]])   # Collect a diagonal from matrix

print(list(range(4)))
print(list(range(-6,7,2)))  # the first is start, second is end and the last one is step
print([[x ** 2, x ** 3] for x in range(4)])
G = (sum(row) for row in M)
print(next(G))   # the summary of first line
print(next(G))   # the summary of second line
print(next(G))  # the summary of third line

print([ord(x) for x in 'spaam'])  #list
print({ord(x) for x in 'spaam'})   #set
print({x: ord(x) for x in 'spaam'})  #map
print((ord(x) for x in 'spaam'))   #object?