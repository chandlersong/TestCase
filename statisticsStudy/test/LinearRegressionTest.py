from unittest import TestCase
import pandas as pd
import unittest
import math
from statsstudy.LinearRegressionAnalysis import LinearRegression

class TestHowToUseTools(TestCase):
    def test_pandas_corr(self):
        data = pd.read_excel("test_line_regression.xlsx")
        print(data.corr())
        print(data.corr().iloc[1,2])


class TestLinearRegression(TestCase):
    def setUp(self):
        self.test_data = pd.read_excel("test_line_regression.xlsx")
        self.target = LinearRegression(self.test_data, 3, 2)

    def test_calculate_correlation_coefficient(self):
        self.assertEqual(0.93062731871456372, self.target.calculate_correlation_coefficient())

    def test_is_linear_regression(self):
        self.assertTrue(self.target.is_linear_regression())

    def test_beta_value(self):
        self.assertEqual(5.1308547938580595, self.target.calculate_Beta1())
        self.assertEqual(274.55023576351073, self.target.calculate_Beta0())

    def test_plot(self):
        # self.target.plot()
        pass

    def test_fit(self):
        self.assertEqual(17598834.799999997, self.target.calculate_sst())
        self.assertEqual(15241773.690037491, self.target.calculate_ssr())
        self.assertEqual(2357061.1099625197, self.target.calculate_sse())
        self.assertEqual(0.86606720633785894, self.target.calculate_r_square())
        self.assertEqual(361.86715717561634, self.target.calculate_std_e())
        self.assertEqual(116.3957630377421,self.target.calculate_f_value())
        self.assertEqual(True, self.target.do_f_verification())
        pass


if __name__ == '__main__':
    unittest.main()
