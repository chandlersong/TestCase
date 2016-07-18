import math
from scipy.stats import t


class LinearRegression:
    def __init__(self, df):
        """
        :param df: it should be a DataFrame
        """
        self.data = df

    def calculate_correlation_coefficient(self, column1, column2):
        corr_df = self.data.corr()
        return corr_df.iloc[column1 - 1, column2 - 1]

    def is_linear_regression(self, column1, column2,alpha=0.05):
        n = self.data.shape[0]
        r = self.calculate_correlation_coefficient(column1,column2)
        t_value = (r*math.sqrt(n-2))/(math.sqrt(1-r**2))
        t_distribute = t(n-2)
        p_value = t_distribute.sf(t_value)

        return not (p_value < t_distribute.ppf(alpha/2) or p_value > t_distribute.isf(alpha/2))
