import unittest
from unittest import TestCase

import numpy as np
from numpy import newaxis


class ArrayTest(TestCase):
    def test_stack_list(self):
        a = np.floor(10 * np.random.random((2, 2)))
        print("a:")
        print(a)
        b = np.floor(10 * np.random.random((2, 2)))
        print("b:")
        print(b)
        print("vstack:")
        print(np.vstack((a, b)))
        print("hstack:")
        print(np.hstack((a, b)))
        print("column_stack:")
        print(np.column_stack((a,b)))

        print("a[:, newaxis]")
        print(a[:, newaxis])

        print("np.column_stack((a[:, newaxis], b[:, newaxis]))")
        print(np.column_stack((a[:, newaxis], b[:, newaxis])))

        print("np.vstack((a[:,newaxis],b[:,newaxis]))")
        print(np.vstack((a[:,newaxis],b[:,newaxis])))

    def test_array_test(self):
        empty_array = np.empty(0)

        print("empty array:")
        print(empty_array)

        new_array = np.append(empty_array, [1])

        print("new array:")
        print(new_array)
        print(new_array.dtype)
        print("empty array:")
        print(empty_array)

        new_array = np.append(new_array, [[2,2],3])
        print("new array:")
        print(new_array)
        print(new_array.dtype)

    def test_array_creation(self):
        print("np.array([['a', 'b'], ['c', 'd']])")
        print(np.array([['a','b'],['c','d']]))
        print("np.array(['a',['a', 'b'], ['c', 'd']],dtype=object)")
        print(np.array(["a",['a', 'b'], ['c', 'd']],dtype=object))
if __name__ == '__main__':
    unittest.main()