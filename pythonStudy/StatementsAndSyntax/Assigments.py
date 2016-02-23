nudge = 1
wink  = 2
A, B = nudge, wink         # Tuple assignment
print(A, B)
[C, D] = [nudge, wink]   # List assignment
print(C, D)

(a, b, c) = "ABC" # Assign string of characters to tuple
print(a, c)

seq = [1,2,3,4,5]
a,*b = seq
print(a)
print(b)

