import math
import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import t,f


class LinearRegression:
    def __init__(self, df, column_x, column_y):
        """
        :param df: it should be a DataFrame
        """
        self.x = df.iloc[:, column_x - 1]
        self.y = df.iloc[:, column_y - 1]
        self.y_return = None
        self.beta1 = np.nan
        self.beta0 = np.nan
        self.sst = np.nan
        self.ssr = np.nan
        self.sse = np.nan
        self.r_square = np.nan
        self.std_e = np.nan
        self.f_value = np.nan

    def calculate_f_value(self):
        if not math.isnan(self.f_value):
            return self.f_value

        msr = self.calculate_ssr() / 1
        mse = self.calculate_sse() / (self.x.count() - 2)
        self.f_value = msr / mse
        return self.f_value

    def do_f_verification(self,alpha=0.05):
        f_value = self.calculate_f_value()
        f_function = f(1,self.x.count() - 2)
        return f_value  > f_function.ppf(0.05)

    def calculate_correlation_coefficient(self):
        return self.x.corr(self.y)

    def is_linear_regression(self, alpha=0.05):
        n = self.x.count()
        r = self.calculate_correlation_coefficient()
        t_value = (r * math.sqrt(n - 2)) / (math.sqrt(1 - r ** 2))
        t_distribute = t(n - 2)
        p_value = t_distribute.sf(t_value)

        return not (p_value < t_distribute.ppf(alpha / 2) or p_value > t_distribute.isf(alpha / 2))

    def calculate_Beta0(self):

        if not math.isnan(self.beta0):
            return self.beta0

        self.beta0 = self.y.mean() - self.calculate_Beta1() * self.x.mean()
        return self.beta0

    def calculate_Beta1(self):

        if not math.isnan(self.beta1):
            return self.beta1

        x_mean = self.x.mean()
        y_mean = self.y.mean()
        self.belta1 = (((self.x - x_mean) * (self.y - y_mean)).sum()) / (((self.x - x_mean) ** 2).sum())
        return self.belta1

    def calculate_y_return(self):
        if self.y_return is not None:
            return self.y_return
        self.y_return = self.x * self.calculate_Beta1() + self.calculate_Beta0()
        return self.y_return

    def calculate_sst(self):
        if not math.isnan(self.sst):
            return self.sst
        self.sst = ((self.y - self.y.mean()) ** 2).sum()
        return self.sst

    def calculate_ssr(self):
        if not math.isnan(self.ssr):
            return self.ssr

        y_return = self.calculate_y_return()

        self.ssr = ((y_return - self.y.mean()) ** 2).sum()
        return self.ssr

    def calculate_sse(self):
        if not math.isnan(self.sse):
            return self.sse

        y_return = self.calculate_y_return()

        self.sse = ((self.y - y_return) ** 2).sum()
        return self.sse

    def calculate_r_square(self):
        if not math.isnan(self.r_square):
            return self.r_square
        self.r_square = self.calculate_ssr() / self.calculate_sst()
        return self.r_square

    def calculate_std_e(self):
        if not math.isnan(self.std_e):
            return self.std_e
        n = self.x.count()
        self.std_e = math.sqrt(self.calculate_sse() / (n - 2))
        return self.std_e

    def plot(self, x_axis_text='x', y_axis_text='y'):
        plt.plot(self.x, self.y, 'ro')

        x_axis_max = plt.gca().get_xlim()[1]
        beta1 = self.calculate_Beta1()
        beta0 = self.calculate_Beta0()
        x_max = self.x.max()
        x_value = [0, x_axis_max]
        y_value = [self.calculate_Beta0(), beta1 * x_axis_max + beta0]
        plt.plot(x_value, y_value)

        plt.ylabel(x_axis_text)
        plt.xlabel(y_axis_text)
        plt.show()
