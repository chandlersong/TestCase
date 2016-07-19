import math
from scipy.stats import t


class LinearRegression:
    def __init__(self, df,column_x,column_y):
        """
        :param df: it should be a DataFrame
        """
        self.x = df.iloc[:,column_x-1]
        self.y = df.iloc[:, column_y - 1]

    def calculate_correlation_coefficient(self):
        return  self.x.corr(self.y)

    def is_linear_regression(self, alpha=0.05):
        n = self.x.count()
        r = self.calculate_correlation_coefficient()
        t_value = (r*math.sqrt(n-2))/(math.sqrt(1-r**2))
        t_distribute = t(n-2)
        p_value = t_distribute.sf(t_value)

        return not (p_value < t_distribute.ppf(alpha/2) or p_value > t_distribute.isf(alpha/2))
