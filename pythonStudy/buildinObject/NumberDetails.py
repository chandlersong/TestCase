# number format
num = 1/3.0
print(num)
print('%e' % num)
print('%4.2f' % num)
print('{0:4.2f}'.format(num))

print(repr("spam"))
print(str("spam"))

print(1 == 2 < 3 )  # equal 1 ==2 and 2 < 3

print(10/4)
print(10//4)

import math
print(math.floor(2.5))
print(math.floor(-2.5))
print(math.trunc(2.5))
print(math.trunc(-2.5))
print((5 / 2, 5 /-2))
print(math.pi)
print(math.e)
print(math.sqrt(9))
print(9 ** .5)
print(math.pow(9,.5))

x=1
print(x << 2) # Shift left 2 bits: 0100
print(x | 2) # Bitwise OR (either bit=1): 0011
print(x & 2)  # Bitwise AND (both bits=1): 0001

import random
print(random.choice(['Life of Brian', 'Holy Grail', 'Meaning of Life']))
print(random.choice(['Life of Brian', 'Holy Grail', 'Meaning of Life']))

from decimal import Decimal
print(Decimal('0.1') + Decimal('0.1') + Decimal('0.1') - Decimal('0.3'))

import decimal
print(decimal.Decimal('1.00') / decimal.Decimal('3.00'))
with decimal.localcontext() as ctx:
    ctx.prec = 2
    decimal.Decimal('1.00') / decimal.Decimal('3.00')
    print(decimal.Decimal('1.00') / decimal.Decimal('3.00'))

from fractions import Fraction
x = Fraction(1, 3)
y = Fraction(2,7)
print(x)
print(y)
print(x+y)
print(y-x)
print(Fraction('.25'))
f = 2.5
a = Fraction(*f.as_integer_ratio())  # the *  is special syntax that expands a tuple into individual arguments
print(a)

#set
x = set('abcde')
y = set('bdxyz')
print(x)
print(x - y)   # Difference
print(y - x)
print(x | y)  # Union
print(x & y)  # Intersection
print(x ^ y) # Symmetric difference (XOR)
print((x > y, x < y))

L = [1, 2, 1, 3, 2, 4, 5]
print(set(L))
L = list(set(L))  # Remove duplicates
print(L)