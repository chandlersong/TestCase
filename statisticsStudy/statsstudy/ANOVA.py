from pandas import DataFrame
from scipy.stats import f, t
import math


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
