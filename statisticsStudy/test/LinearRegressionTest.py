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
        self.target = LinearRegression(self.test_data, 3, 2)

    def test_calculate_correlation_coefficient(self):
        self.assertEqual(0.93062731871456383, self.target.calculate_correlation_coefficient())

    def test_is_linear_regression(self):
        self.assertTrue(self.target.is_linear_regression())

    def test_beta_value(self):
        self.assertEqual(5.1308547938580595, self.target.calculate_Beta1())
        self.assertEqual(274.55023576351073, self.target.calculate_Beta0())

    def test_plot(self):
        self.target.plot()


if __name__ == '__main__':
    unittest.main()
