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

if __name__ == '__main__':
    unittest.main()