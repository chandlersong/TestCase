import unittest
from unittest import TestCase

from numpy.matlib import randn
from pandas import DataFrame


class TestDataFrameExample(TestCase):
    def setUp(self):
        self.example = DataFrame(randn(5, 2), index=range(0, 10, 2), columns=list('AB'))

    def test_extract_new_class(self):
        print(self.example)
        # the third row.
        print(self.example.iloc[[2]])
        # the index which is 2
        print(self.example.loc[[2]])

    def test_change_index_column(self):
        print("print origin")
        print(self.example)
        print(self.example.index)
        # index to columns
        # self.example['index1'] = self.example.index
        self.example.reset_index(level=0, inplace=True)
        print("print index to column")
        print(self.example)
        print(self.example.index)
        # columns to index
        print("print column to index")
        print(self.example.set_index(['index']))
        print(self.example.set_index(['index'], append=True))
        self.example.set_index(['index'], inplace=True)
        print(self.example)
        print(self.example.index)


if __name__ == '__main__':
    unittest.main()
