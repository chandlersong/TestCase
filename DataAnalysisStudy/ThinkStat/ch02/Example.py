import ThinkStat.utils.thinkstats2 as thinkstats2
import ThinkStat.utils.thinkplot as thinkplot

hist = thinkstats2.Hist([1, 2, 2, 3, 5])
print(hist)

thinkplot.Hist(hist)
thinkplot.Show(xlabel='value', ylabel='frequency')
