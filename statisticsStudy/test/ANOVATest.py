from unittest import TestCase
from pandas import DataFrame
import math
import unittest
from statsstudy.ANOVA import TwoFactorNonRepeatAnalysisVariance,TwoFactorRepeatAnalysisVariance


class TestTwoFactorIsolatedAnalysisVariance(TestCase):
    def test_calculate_error_value(self):
        data = DataFrame({
            "item1": [365, 345, 358, 288],
            "item2": [350, 368, 323, 280],
            "item3": [343, 363, 353, 298],
            "item4": [340, 330, 343, 260],
            "item5": [323, 333, 308, 298]
        })
        target = TwoFactorNonRepeatAnalysisVariance(data)
        self.assertEqual(2872.70, round(target.e_ss, 2))
        self.assertEqual(239.39169999999999, round(target.e_ms, 4))


class TwoFactorRepeatAnalysisVarianceTest(TestCase):
    def test_base(self):
        data = DataFrame({
            "A1": [15, 15, 17, 19, 19, 16, 16, 18, 21],
            "A2": [17, 17, 17, 15, 15, 15, 19, 22, 22],
            "A3": [15, 17, 16, 18, 17, 16, 18, 18, 18],
            "A4": [18, 20, 20, 15, 16, 17, 17, 17, 17]
        }, index=['B1', 'B1', 'B1', 'B2', 'B2', 'B2', 'B3', 'B3', 'B3'])
        print(data.shape)
        target = TwoFactorRepeatAnalysisVariance(data,3)
        self.assertEqual(28.388889, round(target.l_ss, 6))
        self.assertEqual(14.194444, round(target.l_ms, 6))
        self.assertEqual(9.462963, round(target.l_f, 6))
        self.assertEqual(0.000933, round(target.l_p, 6))
        self.assertEqual(3.4028261, round(target.get_l_f_crit(), 7))
        self.assertEqual(2.083333, round(target.c_ss, 6))
        self.assertEqual(0.6944444, round(target.c_ms, 7))
        self.assertEqual(0.462963, round(target.c_f, 6))
        self.assertEqual(0.7107688, round(target.c_p, 7))
        self.assertEqual(3.0087866, round(target.get_c_f_crit(), 7))
        self.assertEqual(63.833333, round(target.corr_ss, 6))
        self.assertEqual(10.638889, round(target.corr_ms, 6))
        self.assertEqual(7.0925926, round(target.corr_f, 7))
        self.assertEqual(0.0001961, round(target.corr_p, 7))
        self.assertEqual(2.5081889999999998, round(target.get_corr_f_crit(), 6))
        self.assertEqual(36, round(target.e_ss, 6))
        self.assertEqual(1.5, round(target.e_ms, 6))
        print(target.printInfo())

if __name__ == '__main__':
    unittest.main()
