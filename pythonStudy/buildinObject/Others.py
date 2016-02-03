X = set('spam')
Y = {'h', 'a', 'm'}
print(X,Y)
print(X & Y)  # Intersection
print(X | Y) # Union
print(X - Y) # Difference
print(X > Y) # Superset

print(list(set([1, 2, 1, 3, 1]))) # Filtering out duplicates (possibly reordered)
print(set('spam') - set('ham')) # Finding differences in collections
print(set('spam') == set('asmp')) # Order-neutral equality tests (== is False)

L=[]
if type(L) == type([]): # Type testing, if you must...
    print('yes')
if type(L) == list: # Using the type name
    print('yes')
if isinstance(L, list): # Object-oriented tests
    print('yes')