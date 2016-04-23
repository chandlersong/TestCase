import numpy as np
import matplotlib.pyplot as plt
from numpy.random import randn

arr = np.arange(10)
print(np.sqrt(arr))
print(np.exp(arr))

x = np.random.randn(8)
y = np.random.randn(8)
print(x)
print(y)
print(np.maximum(x, y))

arr = np.random.randn(7) * 5
print(np.modf(arr))

points = np.arange(-5, 5, 0.01)
xs, ys = np.meshgrid(points, points)
print(ys)
print(xs)

z = np.sqrt(xs ** 2 + ys ** 2)
plt.imshow(z, cmap=plt.cm.gray)
plt.colorbar()
plt.title("Image Slot of $\sqrt{x^2 +  y ^2}$ for a grid of values")

xarr = np.array([1.1, 1.2, 1.3, 1.4, 1.5])
yarr = np.array([2.1, 2.2, 2.3, 2.4, 2.5])
cond = np.array([True, False, True, True, False])

print([
          (x if c else y) for x, y, c in zip(xarr, yarr, cond)
          ])

print(np.where(cond, xarr, yarr))
arr = np.random.randn(4, 4)
print(arr)
print(np.where(arr > 0, 2, -2))
print(np.where(arr > 0, 2, arr))

arr = np.array([
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
])
print(arr)
print(arr.mean())
print(np.mean(arr))
print(arr.mean(axis=1))
print(arr[0].mean())
print(arr.sum(axis=0))
print(arr[0].sum())

arr = np.random.randn(100)
print(arr)
print((arr > 0).sum())
bools = np.array([False, False, True, False])
print(bools.any())
print(bools.all())

arr = randn(8)
print(arr)
arr.sort()
print(arr)

arr = randn(5, 3)
print(arr)
arr.sort(1)
print(arr)
arr.sort(0)
print(arr)

names = np.array(['Bob', 'Joe', 'Will', 'Bob', 'Will', 'Joe', 'Joe'])
print(np.unique(names))

values = np.array([6, 0, 0, 3, 2, 5, 6])
print(np.in1d(values, [2, 3, 6]))
