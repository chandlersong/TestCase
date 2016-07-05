import unittest
import pandas
import statisticsstudy.sampler as sampler
from scipy import stats
from intervals import FloatInterval


class MeanTest(unittest.TestCase):
    def testCalculateMeanConfidenceIntervalLarge(self):
        data = pandas.read_excel('mean_large.xlsx').age
        expected = FloatInterval.closed(37.4, 41.6)
        self.assertEqual(expected, sampler.calculate_mean_confidence_interval_large(data))

    def testCalculateMeanConfidenceIntervalSmall(self):
        data = pandas.read_excel('mean_smaller.xlsx').age
        expected = FloatInterval.closed(1476.8, 1503.2)
        self.assertEqual(expected, sampler.calculate_mean_confidence_interval_small(data))

class PercentageTest(unittest.TestCase):
    def testCalculatePercentageConfidenceIntervalLarge(self):
        expected = FloatInterval.closed(0.5565, 0.7435)
        self.assertEqual(expected, sampler.calculate_percent_confidence_interval_large(0.65,100))


class VarTest(unittest.TestCase):
    def testCalculatePercentageConfidenceIntervalLarge(self):
        data = pandas.read_excel('var_test.xlsx').weight
        expected = FloatInterval.closed(56.83, 180.39)
        self.assertEqual(expected, sampler.calculate_Var_confidence_interval_large(data))
        
if __name__ == '__main__':
    unittest.main()
