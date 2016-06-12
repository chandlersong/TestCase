import math
from scipy.stats import norm
from intervals import FloatInterval


def calculate_mean_confidence_interval(series, confidence_interval=0.10):
    mean = series.mean()
    s = math.sqrt(series.var())
    count = series.count()
    z = norm.ppf(1 - confidence_interval / 2)
    delta = round(z * (s / math.sqrt(count)), 1)
    return FloatInterval.closed(mean - delta, mean + delta)
