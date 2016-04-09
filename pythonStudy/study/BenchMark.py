import time, sys

timer = time.clock if sys.platform[:3] == 'win' else time.time


def total(reps, func, *pargs, **kargs):
    """
    Total time to run func() reps times.
    Returns (total time, last result)
    """
    repslist = list(range(reps))  # Hoist out, equalize 2.x, 3.x
    start = timer()  # Or perf_counter/other in 3.3+
    for i in repslist:
        ret = func(*pargs, **kargs)
    elapsed = timer() - start
    return (elapsed, ret)


def bestof(reps, func, *pargs, **kargs):
    """
    Quickest func() among reps runs.
    Returns (best time, last result)
    """
    best = 2 ** 32  # 136 years seems large enough
    for i in range(reps):  # range usage not timed here
        start = timer()
        ret = func(*pargs, **kargs)
        elapsed = timer() - start  # Or call total() with reps=1
        if elapsed < best: best = elapsed  # Or add to list and take min()
    return (best, ret)


def bestoftotal(reps1, reps2, func, *pargs, **kargs):
    """
    Best of totals:
    (best of reps1 runs of (total of reps2 runs of func))
    """
    return bestof(reps1, total, reps2, func, *pargs, **kargs)


print(bestof(50, total, 1000, str.upper, 'spam'))
print(bestoftotal(50, 1000, str.upper, 'spam'))

import timeit
print( min(timeit.repeat(stmt="[x ** 2 for x in range(1000)]", number=1000, repeat=5)))