class MaxHeap:
    def __init__(self):
        self.heap = []
        self.size = 0
        self.heapSum = 0

    def insert(self, value):
        self.heap.append(value)
        self.shiftUp(len(self.heap) - 1)
        self.size += 1
        self.heapSum += value

    def delete(self):
        if len(self.heap) == 0:
            return None
        if len(self.heap) == 1:
            self.size -= 1
            x = self.heap.pop()
            self.heapSum -= x
            return x

        deleted_value = self.heap[0]
        self.heap[0] = self.heap.pop()
        self.shiftDown(0)
        self.size -= 1
        self.heapSum -= deleted_value
        return deleted_value

    def shiftUp(self, index):
        parent = (index - 1) // 2
        if index <= 0:
            return
        if self.heap[index] > self.heap[parent]:
            self.heap[index], self.heap[parent] = self.heap[parent], self.heap[index]
            self.shiftUp(parent)

    def shiftDown(self, index):
        leftChild = 2 * index + 1
        rightChild = 2 * index + 2
        maxi = index

        if leftChild < len(self.heap) and self.heap[leftChild] > self.heap[maxi]:
            maxi = leftChild

        if rightChild < len(self.heap) and self.heap[rightChild] > self.heap[maxi]:
            maxi = rightChild

        if maxi != index:
            self.heap[index], self.heap[maxi] = self.heap[maxi], self.heap[index]
            self.shiftDown(maxi)

    def incrementOne(self):
        for i in range(self.size):
            self.heap[i] += 1
            self.heapSum += 1

    def display(self):
        print("Max-Heap: " + str(self.heap) + " size: " + str(self.size))

class instance:
    def __init__(self):
        self.cracks = []
        self.previousDeposit = []
        self.size = 0
        self.sum = 0

    def display(self):
        for i in range(self.size):
            print(str(self.cracks) + " sum: " + str(self.sum) + " size: " + str(self.size))

def main():
    n = int(input().strip())
    threshhold = int(input().strip())
    capacity = int(input().strip())
    maxTime = 0
    rows = []
    maxi = 0
    for i in range(n):
        row = list(map(int, input().strip().split()))
        rows.append(row)
        if row[0] > maxTime:
            maxTime = row[0]

    arr = []
    for i in range(maxTime + 1):
        ins = instance()
        arr.append(ins)

    for i in range(n):
        arr[rows[i][0]].cracks.append(rows[i][1])
        arr[rows[i][0]].size += 1
        arr[rows[i][0]].sum += rows[i][1]

    for i in range(maxTime + 1):
        arr[i].display()

    prevSum = 0
    heap = MaxHeap()

    index = 0
    cnt = 0
    prevSum = 0

    for i in range(maxTime + 1):
        heap.incrementOne()
        for j in range(arr[i].size):
            heap.insert(arr[i].cracks[j])
        deletedItem = heap.delete()
        ans = prevSum + heap.heapSum
        prevSum = ans - capacity
        if prevSum < 0:
            prevSum = 0
        if prevSum > maxi:
            maxi = prevSum
        if prevSum >= threshhold:
            break

        heap.display()
    while heap.size != 0:
        heap.incrementOne()
        heap.delete()
        ans = prevSum + heap.heapSum
        prevSum = ans - capacity
        if prevSum < 0:
            prevSum = 0
        if prevSum > maxi:
            maxi = prevSum
        if prevSum >= threshhold:
            break

    print("maxi: " + str(maxi))

if __name__ == '__main__':
    main()