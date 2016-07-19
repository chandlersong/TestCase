from unittest import TestCase
import pandas as pd
import unittest
from statsstudy.LinearRegressionAnalysis import LinearRegression

class TestHowToUseTools(TestCase):
    def test_pandas_corr(self):
        data = pd.read_excel("test_corr.xlsx")
        print(data.corr())
        print(data.corr().iloc[1,2])


class TestLinearRegression(TestCase):
    def setUp(self):
        self.test_data = pd.read_excel("test_corr.xlsx")
        self.example = LinearRegression(self.test_data,2,3)

    def test_calculate_correlation_coefficient(self):
        self.assertEqual(0.93062731871456394,self.example.calculate_correlation_coefficient())

    def test_is_linear_regression(self):
        self.assertTrue(self.example.is_linear_regression())

if __name__ == '__main__':
    unittest.main()
