from unittest import TestCase
from pandas import DataFrame
import math
from statsstudy.ANOVA import TwoFactorIsolatedAnalysisVariance


class TestTwoFactorIsolatedAnalysisVariance(TestCase):
    def test_calculate_error_value(self):
        data = DataFrame({
            "item1": [365, 345, 358, 288],
            "item2": [350, 368, 323, 280],
            "item3": [343, 363, 353, 298],
            "item4": [340, 330, 343, 260],
            "item5": [323, 333, 308, 298]
        })
        target = TwoFactorIsolatedAnalysisVariance(data)
        self.assertEqual(2872.70, round(target.e_ss, 2))
        self.assertEqual(239.39169999999999, round(target.e_ms, 4))
