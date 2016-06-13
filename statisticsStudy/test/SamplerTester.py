import unittest
import pandas
import src.sampler as sampler
from scipy import stats
from intervals import FloatInterval



class MeanTest(unittest.TestCase):

    def testCalculateMeanConfidenceInterval(self):
        data = pandas.read_excel('mean_1.xlsx').age
        expected = FloatInterval.closed(37.4, 41.6)
        self.assertEqual(expected,sampler.calculate_mean_confidence_interval(data))


if __name__ == '__main__':
    unittest.main()
