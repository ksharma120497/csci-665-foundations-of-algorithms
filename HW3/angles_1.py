def binary_search_count(arr, target):
    low, high = 0, len(arr) - 1
    count = 0

    while low <= high:
        mid = (low + high) // 2
        mid_val = arr[mid]
        print("hello",mid_val)
        if mid_val == target:
            count += 1
            l = mid - 1
            while l >= low and arr[l] == target:
                count += 1
                l -= 1
            # Move to the right to find additional occurrences
            r = mid + 1
            while r <= high and arr[r] == target:
                count += 1
                r += 1
            return count

        elif mid_val < target:
            low = mid + 1
        else:
            high = mid - 1

    return 0

def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

def check(a):
    slopes = [[] for _ in range(len(a))]
    for i, p1 in enumerate(a):
        for p2 in a:
            if p1 != p2:
                dy = p2[1] - p1[1]
                dx = p2[0] - p1[0]
                g=gcd(abs(dx), abs(dy))
                if g==0:
                    s=(0,0)
                else:
                    s=(dx // g, dy // g)

                slopes[i].append(s)
    for s in slopes:
        s.sort()
    count = 0
    print(slopes)
    for s in slopes:
        for (dy, dx) in s:
            count += binary_search_count(s, (dx, -dy))
            count += binary_search_count(s, (-dx, dy))
    return count//2

n = int(input())
points = []

for _ in range(n):
    x, y = map(int, input().split())
    points.append((x, y))

print(check(points))