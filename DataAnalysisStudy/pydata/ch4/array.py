import numpy as np

'''
array basic
'''
data = np.array([
    [0.9526, -0, 246, -0.8856],
    [0.5639, 0.2379, 0.9104]
])

print(data)
print(data.shape)
print(data.dtype)
print(data.ndim)

data2 = [[1, 2, 3, 4], [5, 6, 7, 8]]
arr1 = np.array(data2)

print(arr1.ndim)
print(arr1.shape)
print(arr1.dtype)

print(np.zeros(10))
print(np.zeros((3, 6)))
print(np.empty((5, 6)))
print(np.empty((2, 3, 2)))

print(np.array(data2, dtype=np.float64))
print(np.array(data2, dtype=np.int32))

baseArray = [1, 2, 3, 4, 5, 6, 7]
arr_orign = np.array(baseArray)
print(arr_orign.dtype)
print(arr_orign.astype(np.float64).dtype)

arr_float = np.array([3.7, -1.2, -2.6, 0.5, 12.9, 10.1])
print(arr_float.astype(np.int32))

'''
array operate
'''

arr_operat1 = np.array([
    [1, 2, 3],
    [4, 5, 6]
])

arr_operat2 = np.array([
    [1, 2, 3],
    [4, 5]
])

print(arr_operat1 * arr_operat1)
# print(arr_operat1 * arr_operat2) it will casuse error. the shape must be same

# if an array has been turned into a np array, it should have only one copy
arr_core = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
arr_np = np.array(arr_core)
arr_slice = arr_np[5:8]
arr_slice[1] = 12345
arr_slice_copy = arr_np[1:3].copy()
arr_slice_copy[1] = 67890
print(arr_core)
print(arr_np)
print(arr_slice)
print(arr_slice_copy)

arr2d = np.array([
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
])
print(arr2d[0, 2] == arr2d[0, 2])

arr3d = np.array([
    [[1, 2, 3], [4, 5, 6]],
    [[7, 8, 9], [10, 11, 12]]
])
print(arr3d[0])

old_value = arr3d[0].copy()
arr3d[0] = 88  # change all the value of 3d to 88
print(arr3d)
arr3d[0] = old_value
print(arr3d)

arr = np.arange(10)
print(arr)
print(arr[1:6])
print(arr2d[:2])
print(arr2d[:2, 1:])

names = np.array(['Bob', 'Joe', 'Will', 'Bob', 'Will', 'Joe', 'Joe'])
data = np.random.randn(7, 4)
print(names)
print(data)

print(names == "Bob")  # find all element is Bob
print(data[names == "Bob"])  # it will print line 1 and line 4, these line is where the true
print(data[names == "Bob", 2:])

mask = (names == 'Bob') | (names == 'Will')
print(mask)
print(data[mask])

arr = np.empty((8, 4))  # create a array with empty value

for i in range(8):
    arr[i] = i

print(arr)
print(arr[[4, 3, 0, 6]])
print(arr[[-1, -2]])

arr = np.arange(32).reshape((8, 4))
print(arr)
print(arr[[1, 5, 7, 2], [0, 3, 1, 2]]) # find out line 1 column 0
print(arr[[1, 5, 7, 2]][:,[0, 3, 1, 2]]) # find out line 1 and [:][0],[:][3],[:][2],[:][1]
print(np.ix_([1, 5, 7, 2],[0, 3, 1, 2]))
print(arr[np.ix_([1, 5, 7, 2],[0, 3, 1, 2])])

arr = np.arange(15).reshape((3, 5))
print(arr)
print(arr.T)

arr= np.arange(16).reshape((2,2,4))
print(arr)
print(arr.transpose((1,0,2)))
print(arr.T)
print(arr.swapaxes(1,2))