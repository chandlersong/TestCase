import math
from scipy.stats import norm, t, chi2
from intervals import FloatInterval


def calculate_mean_confidence_interval_large(series, confidence_interval=0.90):
    mean = series.mean()
    s = math.sqrt(series.var())
    count = series.count()
    z = norm.isf((1 - confidence_interval) / 2)
    delta = round(z * (s / math.sqrt(count)), 1)
    return FloatInterval.closed(mean - delta, mean + delta)


def calculate_mean_confidence_interval_small(series, confidence_interval=0.95):
    mean = series.mean()
    s = math.sqrt(series.var())
    count = series.count()
    rv = t(count - 1)
    z = rv.isf((1 - confidence_interval) / 2)
    delta = round(z * (s / math.sqrt(count)), 2)
    return FloatInterval.closed(mean - delta, mean + delta)


def calculate_percent_confidence_interval_large(p, n, confidence_interval=0.95):
    z = norm.isf((1 - confidence_interval) / 2)
    delta = round(z * math.sqrt(((p * (1 - p)) / n)), 4)
    return FloatInterval.closed(p - delta, p + delta)


def calculate_Var_confidence_interval_large(series, confidence_interval=0.95):
    count = series.count()
    var = series.var()
    upper = (count - 1) * var
    rv = chi2(count - 1)
    alpha = 1 - confidence_interval
    return FloatInterval.closed(round(upper / rv.isf(alpha / 2), 2), round(upper / rv.isf(1 - alpha / 2), 2))

