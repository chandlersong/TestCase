print("Meaning " 'of' " Life")   # Implicit concatenation, it will print Meaning of Life
print(('knight"s', "knight's"))

path = 'C:\new\text.dat'  # \n will be treated as \n
print(path)
path = 'C:\\new\\text.dat'
print(path)
path = r'C:\new\text.dat'  # a solution to display say pth
print(path)

mantra = """Always look # comment will be print
        aaa
        bbbb"""
print(mantra)

#  'abc'+9 don't work

myjob = "hacker"
for c in myjob: print(c, end=' ')

print("y" in myjob)
print("k" in myjob)

S = 'spam'
print(S[0])
print(( S[1:3], S[1:], S[:-1]))

print(S[:])
print(S[:] == S)
print(S[:] is S)

S = '1234567890'
print(S[0:10:3])     # the third parameter is step
print( S[::-1])         # revert
print( S[::-2])


print(int("42"), str(42),repr('42'))

print((ord('s'),chr(115)))

print(int('1101', 2)) # Convert binary to integer: built-in
print(bin(113))        # Convert integer to binary: built-in

# format
print('That is %d %s bird!' % (1, 'dead'))
print('That is {0} {1} bird!'.format(1, 'dead'))

S = 'xxxxSPAMxxxxSPAMxxxx'
print(S.replace('SPAM', 'EGGS'))   # Replace all
print(S.replace('SPAM', 'EGGS', 1))       # Replace one
print('SPAM'.join(['eggs', 'sausage', 'ham', 'toast']))

print( '%(qty)d more %(food)s' % {'qty': 1, 'food': 'spam'})
print('{motto}, {0} and {food}'.format(42, motto=3.14, food=[1, 2]))

import sys
print('My {1[kind]} runs {0.platform}'.format(sys, {'kind': 'laptop'}))
print('My {map[kind]} runs {sys.platform}'.format(sys=sys, map={'kind': 'laptop'}))