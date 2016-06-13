import math
from scipy.stats import norm,t
from intervals import FloatInterval


def calculate_mean_confidence_interval_large(series, confidence_interval=0.90):
    mean = series.mean()
    s = math.sqrt(series.var())
    count = series.count()
    z = norm.isf((1-confidence_interval)/2)
    delta = round(z * (s / math.sqrt(count)), 1)
    return FloatInterval.closed(mean - delta, mean + delta)


def calculate_mean_confidence_interval_small(series, confidence_interval=0.95):
    mean = series.mean()
    s = math.sqrt(series.var())
    count = series.count()
    rv = t(count-1)
    z = rv.isf((1-confidence_interval)/2)
    delta = round(z * (s / math.sqrt(count)), 2)
    return FloatInterval.closed(mean - delta, mean + delta)
