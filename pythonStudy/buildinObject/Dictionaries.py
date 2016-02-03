D = {'food': 'Spam', 'quantity': 4, 'color': 'pink'}
print(D['food'])
D['quantity'] += 1
print(D)

D1 = {}       # define varilable
D1['name'] = 'chandler'
D1['age'] = 13
print(D1)

bob1 = dict(name='Bob', job='dev', age=40)
print(bob1)
bob2 = dict(zip(['name', 'job', 'age'], ['Bob', 'dev', 40]))
print(bob2)

bob1 = 0   # release resource

# print(D['error']) it will throw error
# two solution the get key not exist
print(D.get('x', 'default value'))
print(D['x'] if 'x' in D else 0)

# list keys
DKey = list(D.keys())
print(DKey)

#loop value
for key in DKey:
    print(key,'=>',D[key])

