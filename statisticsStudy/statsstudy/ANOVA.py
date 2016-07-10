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
        self.total_mean =  self.statistics_info['sum'].sum() /  self.statistics_info['count'].sum()
        self.between_group_ss = (
            self.statistics_info['count'] * (self.statistics_info['mean'] - self.total_mean) ** 2).sum()
        self.ms_between_group = self.between_group_ss / (self.k - 1)
        self.inside_group_ss = self.statistics_info['sumdiff'].sum()
        self.ms_inside_group = self.inside_group_ss / (self.n - self.k)
        self.f = self.ms_between_group / self.ms_inside_group
        self.f_distribute = f(self.k - 1, self.n - self.k)
        self.p_value = self.f_distribute.sf(self.f)
        self.t_distribute = t(self.n - self.k)

    def printInfo(self,alpha = 0.05):
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

    def get_f_crit(self, alpha = 0.05):
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
    def __init__(self,data):
        self.r = data.shape[1]
        self.k = data.shape[0]
        self.calculate_error_value(data)
        columnAnalysis = SingleAnalysisVariance(data)
        self.c_ss =columnAnalysis.between_group_ss
        self.c_ms = columnAnalysis.between_group_ss / (self.r - 1)
        self.c_f =  self.c_ms / self.e_ms
        self.c_statistics_info = columnAnalysis.statistics_info
        self.c_f_distribute = f(self.r - 1, (self.r - 1)*(self.k - 1))
        self.c_p_value = self.c_f_distribute.sf(self.c_f)

        lineAnalysis = SingleAnalysisVariance(data.T)
        self.l_ss = lineAnalysis.between_group_ss
        self.l_ms = lineAnalysis.between_group_ss / (self.k - 1)
        self.l_f = self.l_ms / self.e_ms
        self.l_statistics_info = lineAnalysis.statistics_info
        self.l_f_distribute = f(self.k - 1, (self.r - 1) * (self.k - 1))
        self.l_p_value = self.l_f_distribute.sf(self.l_f)


    def printInfo(self,alpha = 0.05):
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

    def get_c_f_crit(self, alpha = 0.05):
        return self.c_f_distribute.isf(alpha)

    def calculate_error_value(self,data):
        total_mean = data.sum().sum() /  data.notnull().sum().sum()
        c_mean=data.apply(lambda c: np.repeat(c.mean(), len(data)))
        lentemp = data.T
        l_mean=lentemp.apply(lambda c: np.repeat(c.mean(), len(lentemp))).T
        self.e_ss = ((data-c_mean-l_mean+total_mean)**2).sum().sum()
        self.e_ms =  self.e_ss/(( self.r-1)* ( self.k -1))


class TwoFactorRepeatAnalysisVariance:
    def __init__(self, data,repeat):
        self.t = repeat
