try:
    x = 'spam'[99]
except IndexError:
    print('except run')
else:
    print('else run')
finally:
    print('finally run')
print('after run')

try:
    x = 'spam'[3]
except IndexError:
    print('except run')
else:
    print('else run')
finally:
    print('finally run')
print('after run')

'''
try:
    1 / 0
except Exception as E:
    raise TypeError('Bad') from E
'''