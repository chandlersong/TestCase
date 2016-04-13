from . import Statements

if True:
    def func():
        print("true")
else:
    def func():
        print("false")
func()
othername = func
othername()
func.attr = "abc"  # Attach attributes

import builtins

print(dir(builtins))


def f1(a, b, c): print(a, b, c)


f1(1, 2, 3)  # 1 2 3
f1(a=1, b=2, c=3)  # 1 2 3
f1(a=1, c=3, b=2)  # 1 2 3


# Default
def f2(a, b=2, c=3):
    print(a, b, c)


f2(1)  # 1 2 3
f2(1, 5)  # 1 5 3
f2(1, c=5)  # 1 2 5


def f3(*args):
    print(args)


# Headers: Collecting arguments
f3()  # ()
f3(1)  # (1,)
f3(1, 2, 3, 4, 5)  # (1, 2, 3, 4, 5)


def f4(a, *pargs, **kargs): print(a, pargs, kargs)


f4(1, 2, 3, x=1, y=2)  # 1 (2, 3) {'x': 1, 'y': 2}
f4(1, 2, 3, 4, x=1, y=2)  # 1 (2, 3, 4) {'x': 1, 'y': 2}


def f5(a, b, c, d): print(a, b, c, d)


args = (1, 2)
args += (3, 4)
f5(*args)  # 1 (2, 3, 4) {}

args = {'a': 1, 'b': 2, 'c': 3, 'd': 4}
# ** unpack the dictionary
f5(**args)  # 1 2 3 4


def tracer(func, *pargs, **kargs):  # Accept arbitrary arguments
    print('calling:', func.__name__)
    return func(*pargs, **kargs)  # Pass along arbitrary arguments


def func(a, b, c, d):
    return a + b + c + d


print(tracer(func, 1, 2, c=3, d=4))


def kwonly(a, *b, c):
    print(a, b, c)


kwonly(1, 2, c=3)  # 1 (2,) 3
kwonly(a=1, c=3)  # 1 () 3
# kwonly(1, 2, 3)  ypeError: kwonly() missing 1 required keyword-only argument: 'c'


import sys

print(sys.getrecursionlimit())  # get how deep the function stack


def gensquares(N):
    for i in range(N):
        yield i ** 2  # yield is like return a array. which each i**2 is it's element


for i in gensquares(5):  # Resume the function
    print(i, end=' : ')  # Print last yielded value

x = gensquares(3)
print(x)
print(next(x))
print(x.send(8))
print(next(x))


print((x ** 2 for x in range(5))) # Generator expression: make an iterable
