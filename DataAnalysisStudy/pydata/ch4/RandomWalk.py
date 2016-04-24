import random
import numpy as np

position = 0
walk = [position]
steps = 1000

for i in range(steps):
    step = 1 if random.randint(0,1) else -1
    position +=step
    walk.append(position)

nsteps = 1000
draws = np.random.randint(0,2, size = nsteps)
print(draws)
steps = np.where(draws > 0, 1, -1)
print(steps)
walk = steps.cumsum()
print(walk)
print((np.abs(walk) >= 10).argmax())