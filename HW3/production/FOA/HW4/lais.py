"""
Input specification: the first line contains the value m. The second line contains the value n. The third line contains the m integer values of the first sequence,
separated by a space. The fourth (and final) line contains the n integer values of the second sequence, separated by a space. You may assume that m and n are each no larger than 10,000.
You may assume that each input integer as well as the output value fit within a 32-bit integer.
"""

#submitted by dharma teja ds3519  and kapil sharma ks4643
input()
input()
l1 = list(map(int,input().split()))
l2 = list(map(int,input().split()))

# Moreover, for this problem, it is also required that the indices of the chosen elements
# are strictly increasing across both sequences. In other words, if ai and bj (or bi and aj ) are
# consecutive elements of X, it must be the case that i < j

if len(l1) < len(l2):
    l1, l2 = l2, l1
dp1 = [1 for i in range(len(l1))]
dp2 = [1 for i in range(len(l2))]
p1, p2 = 0, 0

for i in range(min(len(l1), len(l2))):  # even for dp1 odd for dp2  #starting with ele of l1
    # odd should check forl1 as prev ele is fixed for ele from l1
    for j in range(i):
        if l1[j] < l2[i]:
            dp2[i] = max(dp1[j] + 1, dp2[i])

    for j in range(i):
        if l2[j] < l1[i]:
            dp1[i] = max(dp2[j] + 1, dp1[i])
if len(l1) != len(l2):
    if len(l1) > len(l2):
        for i in range(len(l2),len(l1)):
            for j in range(len(l2)):
                if l2[j] < l1[i]:
                    dp1[i] = max(dp2[j] + 1, dp1[i])


dp1m,dp2m=max(dp1),max(dp2)

print(max(dp1m,dp2m,max(dp1),max(dp2)))

"""
Notes:
above I am starting from an 1 but it is valid only for one list as if i start from ls1 then 0th element from second seq dp might have 
value 2 

for 2nd d[p questioon wok ewith indesx
"""


