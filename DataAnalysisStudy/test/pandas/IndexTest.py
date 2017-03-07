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

    def test_create_emtpy_multi_index_df(self):
        my_index = pd.MultiIndex(levels=[[], [], []],
                                 labels=[[], [], []],
                                 names=[u'one', u'two', u'three'])
        my_columns = [u'alpha', u'beta']

        df = pd.DataFrame(index=my_index, columns=my_columns)
        print("empty dataFrame")
        print(df)
        df.loc[('five', 'one','test'), :] = np.random.randn(2)
        df.loc[('five', 'one', 'test2'), :] = np.random.randn(2)
        df.loc[('five', 'two','test'), :] = np.random.randn(2)
        df.loc[('one', 'one','test'), :] = np.random.randn(2)
        df.loc[('one', 'two','test'), :] = np.random.randn(2)
        df.loc[('three', 'ten','test'), :] = np.random.randn(2)
        df.loc[('three', 'eight','test'), :] = np.random.randn(2)

        print(df)




if __name__ == '__main__':
    unittest.main()
