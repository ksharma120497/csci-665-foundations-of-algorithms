def add_edge(graph, u, v):
    graph[u].append(v)
    graph[v].append(u)

def find_cycles(graph, v, visited, parent, length, cycles):
    visited[v] = True
    for neighbor in graph[v]:
        if not visited[neighbor]:
            find_cycles(graph, neighbor, visited, v, length + 1, cycles)
        elif neighbor != parent:
            # Cycle detected, store its length
            print("hello", )
            cycle_length = length - visited[neighbor] + 1
            cycles.add(cycle_length)
    visited[v] = length  # Store the depth of the node in the visited array

def get_cycle_lengths(n, cards):
    graph = [[] for _ in range(n + 1)]
    visited = [0] * (n + 1)  # Initialize visited with 0
    cycles = set()  # To store unique cycle lengths

    # Build the graph
    for u, v in cards:
        add_edge(graph, u, v)

    # Find and store cycle lengths
    for i in range(1, n + 1):
        if not visited[i]:
            find_cycles(graph, i, visited, -1, 1, cycles)

    return list(cycles)

# Example 1
n1 = 6
cards1 = [(1, 5), (1, 4), (2, 4), (6, 3), (3, 6), (5, 2)]
print(get_cycle_lengths(n1, cards1))

# Example 2
n2 = 6
cards2 = [(1, 5), (2, 6), (1, 4), (4, 5), (3, 6), (3, 2)]
print(get_cycle_lengths(n2, cards2))
n3 = 10
cards3 = [(1, 10), (5, 2), (9, 4), (7, 7), (10, 5), (6, 9), (3, 3), (8, 6), (4, 1), (2, 8)]
print(get_cycle_lengths(n3, cards3))

n4 = 20
cards4 = [(12, 3), (13, 7), (18, 8), (3, 1), (15, 9), (17, 2), (2, 20), (9, 18), (1, 13), (7, 14), (20, 19), (16, 11), (10, 16), (6, 12), (5, 10), (4, 17), (19, 5), (11, 6), (8, 4), (14, 15)]
print(get_cycle_lengths(n4, cards4))
n5 = 100
cards5 = [
    (67, 49), (21, 96), (37, 58), (39, 47), (84, 10), (52, 26), (94, 17), (16, 89), (19, 13), (70, 41),
    (62, 86), (68, 9), (84, 2), (10, 15), (14, 21), (73, 17), (31, 30), (92, 38), (76, 95), (85, 32),
    (74, 87), (99, 42), (79, 93), (27, 62), (69, 74), (97, 80), (36, 95), (3, 48), (29, 41), (60, 24),
    (49, 81), (28, 22), (20, 33), (71, 57), (53, 32), (23, 39), (77, 23), (85, 35), (11, 8), (26, 63),
    (72, 91), (59, 24), (91, 7), (81, 46), (90, 7), (80, 43), (38, 71), (61, 27), (56, 47), (88, 63),
    (82, 16), (90, 1), (73, 54), (56, 65), (22, 46), (44, 25), (99, 51), (44, 54), (18, 6), (9, 66),
    (75, 88), (97, 36), (86, 18), (11, 29), (12, 53), (93, 20), (45, 78), (4, 67), (69, 75), (57, 2),
    (50, 64), (34, 100), (15, 51), (87, 28), (30, 77), (14, 83), (6, 3), (70, 52), (83, 98), (58, 31),
    (78, 19), (60, 42), (43, 98), (48, 89), (65, 34), (76, 79), (94, 68), (61, 4), (55, 13), (40, 45),
    (100, 96), (12, 5), (37, 64), (33, 66), (92, 25), (82, 50), (5, 40), (35, 1), (8, 55), (72, 59)
]

# Use the get_cycle_lengths function to find the lengths of all cycles
cycle_lengths = get_cycle_lengths(n5, cards5)
print(cycle_lengths)