import unittest
from unittest import TestCase

from numpy.matlib import randn
from pandas import DataFrame


class TestDataFrameExample(TestCase):

    def setUp(self):
        self.example = DataFrame(randn(5,2),index=range(0,10,2),columns=list('AB'))


    def test_extract_new_class(self):
        print(self.example)
        # the third row.
        print(self.example.iloc[[2]])
        # the index which is 2
        print(self.example.loc[[2]])



if __name__ == '__main__':
    unittest.main()