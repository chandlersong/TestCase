import unittest
from unittest import TestCase

import numpy as np
import pandas as pd


class TestMulitIndex(TestCase):
    def test_hello_world(self):
        arrays = [['bar', 'bar', 'baz', 'baz', 'foo', 'foo', 'qux', 'qux'],
                  ['one', 'two', 'one', 'two', 'one', 'two', 'one', 'two']]
        tuples = list(zip(*arrays))
        index = pd.MultiIndex.from_tuples(tuples, names=['first', 'second'])

        s = pd.Series(np.random.randn(8), index=index)
        print(s)
        df = pd.DataFrame(np.random.randn(8, 4), index=arrays)
        print(df)

        df.loc[('five','one'),:] = [1,2,3,4]
        print(df)


if __name__ == '__main__':
    unittest.main()
