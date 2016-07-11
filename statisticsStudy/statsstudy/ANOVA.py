from pandas import DataFrame
from scipy.stats import f, t
import math
import numpy as np
import pandas as pd


class SingleAnalysisVariance:
    def __init__(self, data):
        '''
        :param data:  the data to analysis, it'a a DataFrame
        '''
        self.n = data.notnull().sum().sum()
        self.k = data.shape[1]
        self.statistics_info = self._create_statistics_info(data)
        self.total_mean = self.statistics_info['sum'].sum() / self.statistics_info['count'].sum()
        self.between_group_ss = (
            self.statistics_info['count'] * (self.statistics_info['mean'] - self.total_mean) ** 2).sum()
        self.ms_between_group = self.between_group_ss / (self.k - 1)
        self.inside_group_ss = self.statistics_info['sumdiff'].sum()
        self.ms_inside_group = self.inside_group_ss / (self.n - self.k)
        self.f = self.ms_between_group / self.ms_inside_group
        self.f_distribute = f(self.k - 1, self.n - self.k)
        self.p_value = self.f_distribute.sf(self.f)
        self.t_distribute = t(self.n - self.k)

    def printInfo(self, alpha=0.05):
        print("n:" + str(self.n))
        print("k:" + str(self.k))
        print("total mean:" + str(self.total_mean))
        print("between group ss:" + str(self.between_group_ss))
        print("ms between group:" + str(self.ms_between_group))
        print("inside group ss:" + str(self.inside_group_ss))
        print("ms inside group:" + str(self.ms_inside_group))
        print("f:" + str(self.f))
        print("p value:" + str(self.p_value))
        print("f crit:" + str(self.get_f_crit(alpha)))

    def calculateLSD(self, alpha, n1, n2):
        '''

        :param alpha:
        :param n1:
        :param n2:
        :return:
        '''

        t_value = self.t_distribute.isf(alpha / 2)
        return t_value * math.sqrt(self.ms_inside_group * (1 / n1, 1 / n2))

    def get_f_crit(self, alpha=0.05):
        '''
        get the f crit base on alpha
        :param alpha:
        :return:
        '''
        return self.f_distribute.isf(alpha)

    def _create_statistics_info(self, data):
        '''
        :param data: the data to analysis, DataFrame
        :return: DataFrame
        '''
        statics_df = DataFrame()
        statics_df['mean'] = data.mean()
        statics_df['count'] = data.count()
        statics_df['sum'] = data.sum()
        statics_df['var'] = data.var()
        statics_df['sumdiff'] = data.var() * (statics_df['count'] - 1)
        return statics_df


class TwoFactorNonRepeatAnalysisVariance:
    def __init__(self, data):
        self.r = data.shape[1]
        self.k = data.shape[0]
        self.calculate_error_value(data)
        columnAnalysis = SingleAnalysisVariance(data)
        self.c_ss = columnAnalysis.between_group_ss
        self.c_ms = columnAnalysis.between_group_ss / (self.r - 1)
        self.c_f = self.c_ms / self.e_ms
        self.c_statistics_info = columnAnalysis.statistics_info
        self.c_f_distribute = f(self.r - 1, (self.r - 1) * (self.k - 1))
        self.c_p_value = self.c_f_distribute.sf(self.c_f)

        lineAnalysis = SingleAnalysisVariance(data.T)
        self.l_ss = lineAnalysis.between_group_ss
        self.l_ms = lineAnalysis.between_group_ss / (self.k - 1)
        self.l_f = self.l_ms / self.e_ms
        self.l_statistics_info = lineAnalysis.statistics_info
        self.l_f_distribute = f(self.k - 1, (self.r - 1) * (self.k - 1))
        self.l_p_value = self.l_f_distribute.sf(self.l_f)

    def printInfo(self, alpha=0.05):
        print("r:" + str(self.r))
        print("k:" + str(self.k))

        print("line ss:" + str(self.l_ss))
        print("line ms:" + str(self.l_ms))
        print("line f:" + str(self.l_f))
        print("line p value:" + str(self.l_p_value))
        print("line f crit:" + str(self.get_l_f_crit(alpha)))

        print("column between group ss:" + str(self.c_ss))
        print("column ms between group:" + str(self.c_ms))
        print("column f:" + str(self.c_f))
        print("column p value:" + str(self.c_p_value))
        print("columnne f crit:" + str(self.get_c_f_crit(alpha)))

        print("error between group ss:" + str(self.e_ss))
        print("error ms between group:" + str(self.e_ms))

    def get_l_f_crit(self, alpha=0.05):
        return self.l_f_distribute.isf(alpha)

    def get_c_f_crit(self, alpha=0.05):
        return self.c_f_distribute.isf(alpha)

    def calculate_error_value(self, data):
        total_mean = data.sum().sum() / data.notnull().sum().sum()
        c_mean = data.apply(lambda c: np.repeat(c.mean(), len(data)))
        lentemp = data.T
        l_mean = lentemp.apply(lambda c: np.repeat(c.mean(), len(lentemp))).T
        self.e_ss = ((data - c_mean - l_mean + total_mean) ** 2).sum().sum()
        self.e_ms = self.e_ss / ((self.r - 1) * (self.k - 1))


class TwoFactorRepeatAnalysisVariance:
    def __init__(self, data, repeat_times):
        self.t = repeat_times
        self.r = data.shape[1]
        self.s = data.index.drop_duplicates().size
        self.total_mean = data.sum().sum() / (self.r * self.s * self.t)
        self.corr_mean = data.groupby(level=0).mean()
        self.calculateErrorData(data)
        self.calculateSampleData(data)
        self.calculateColumnData(data)
        self.calculateCorrData(data)

    def printInfo(self, alpha=0.05):
        print("r:" + str(self.r))
        print("s:" + str(self.s))
        print("t:" + str(self.t))

        print("line ss:" + str(self.l_ss))
        print("line ms:" + str(self.l_ms))
        print("line f:" + str(self.l_f))
        print("line p value:" + str(self.l_p))
        print("line f crit:" + str(self.get_l_f_crit(alpha)))

        print("column between group ss:" + str(self.c_ss))
        print("column ms between group:" + str(self.c_ms))
        print("column f:" + str(self.c_f))
        print("column p value:" + str(self.c_p))
        print("columnne f crit:" + str(self.get_c_f_crit(alpha)))

        print("corr between group ss:" + str(self.corr_ss))
        print("corr ms between group:" + str(self.corr_ms))
        print("corr f:" + str(self.corr_f))
        print("corr p value:" + str(self.corr_p))
        print("corr f crit:" + str(self.get_corr_f_crit(alpha)))

        print("error between group ss:" + str(self.e_ss))
        print("error ms between group:" + str(self.e_ms))

    def calculateSampleData(self, data):
        self.l_mean = data.groupby(level=0).mean().T.mean()
        self.l_ss = ((self.l_mean - self.total_mean) ** 2).sum() * self.r * self.t
        self.l_ms = self.l_ss / (self.s - 1)
        self.l_f = self.l_ms / self.e_ms
        self.l_f_distribute = f(self.s - 1, self.r * self.s * (self.t - 1))
        self.l_p = self.l_f_distribute.sf(self.l_f)

    def get_l_f_crit(self, alpha=0.05):
        return self.l_f_distribute.isf(alpha)


    def calculateColumnData(self, data):
        self.c_mean = data.mean()
        self.c_ss = ((self.c_mean - self.total_mean) ** 2).sum() * self.s * self.t
        self.c_ms = self.c_ss / (self.r - 1)
        self.c_f = self.c_ms / self.e_ms
        self.c_f_distribute = f(self.r - 1, self.r * self.s * (self.t - 1))
        self.c_p = self.c_f_distribute.sf(self.c_f)

    def get_c_f_crit(self, alpha=0.05):
        return self.c_f_distribute.isf(alpha)

    def calculateCorrData(self, data):
        l_mean = pd.concat([self.l_mean] * self.r, axis=1, keys=self.corr_mean.columns)
        c_mean = pd.concat([self.c_mean] * self.s, axis=1, keys=self.corr_mean.index).T
        self.corr_ss = ((self.corr_mean - l_mean - c_mean + self.total_mean) ** 2).sum().sum() * self.t
        self.corr_ms = self.corr_ss / ((self.s - 1)*(self.r - 1))
        self.corr_f = self.corr_ms / self.e_ms
        self.corr_f_distribute = f((self.s - 1)*(self.r - 1), self.r * self.s * (self.t - 1))
        self.corr_p = self.corr_f_distribute.sf(self.corr_f)

    def get_corr_f_crit(self, alpha=0.05):
        return self.corr_f_distribute.isf(alpha)

    def calculateErrorData(self, data):
        self.e_mean = data.groupby(level=0).transform(lambda c: c.mean())
        self.e_ss = ((data - self.corr_mean) ** 2).sum().sum()
        self.e_ms = self.e_ss / (self.r * self.s * (self.t - 1))
