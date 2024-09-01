def knapsack(B, array, totalCategories):
    S = []
    for _ in range(totalCategories + 1):
        S.append([0] * (B + 1))

    lastSelectedCategoryValue = [tuple()] * totalCategories

    for cost, value, category in array:
        if lastSelectedCategoryValue[category-1] == () or lastSelectedCategoryValue[category-1][1] != cost or \
                lastSelectedCategoryValue[category-1][1] < value:
            for v in range(1, B + 1):
                S[category][v] = max(S[category][v], S[category-1][v])
                if cost <= v:
                    S[category][v] = max(S[category][v], S[category-1][v-cost] + value)
            lastSelectedCategoryValue[category-1] = (cost, value)

    return S[totalCategories][B]


def findingBestObject(array):
    array.sort(key=lambda x: (x[2], x[0]))
    totalCategories = max(array, key=lambda x: x[2])[2]
    return array, totalCategories


def takeInput():
    n = int(input())
    B = int(input())
    array = []

    for _ in range(n):
        values = input().split()
        tempArray = []
        for val in values:
            tempArray.append(int(val))
        array.append(tempArray)

    return n, B, array


if __name__ == '__main__':
    n, B, items = takeInput()
    items, totalCategories = findingBestObject(items)
    print(knapsack(B, items, totalCategories))