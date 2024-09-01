def dfs(graph, start, visited, finish, leaders, current_leader):
    visited[start] = True
    leaders[start] = current_leader
    for neighbor in graph[start]:
        if not visited[neighbor]:
            dfs(graph, neighbor, visited, finish, leaders, current_leader)
    finish.append(start)


def reverse_graph(graph):
    reversed_graph = {node: [] for node in graph}
    for node, neighbors in graph.items():
        for neighbor in neighbors:
            reversed_graph[neighbor].append(node)
    return reversed_graph


def kosaraju(graph, node_count):
    visited = [False] * node_count
    finish_order = []
    leaders = [None] * node_count

    for node in range(node_count):
        if not visited[node]:
            dfs(graph, node, visited, finish_order, leaders, node)

    reversed_graph = reverse_graph(graph)

    visited = [False] * node_count
    components = []
    while finish_order:
        node = finish_order.pop()
        if not visited[node]:
            component_finish_order = []
            dfs(reversed_graph, node, visited, component_finish_order, leaders, node)
            components.append(component_finish_order)

    return components, leaders


def build_meta_graph(components, graph):
    meta_graph = {i: set() for i, _ in enumerate(components)}
    component_index = {node: index for index, component in enumerate(components) for node in component}

    for component in components:
        for node in component:
            node_index = component_index[node]
            for neighbor in graph[node]:
                neighbor_index = component_index[neighbor]
                if node_index != neighbor_index:
                    meta_graph[node_index].add(neighbor_index)

    return meta_graph


def count_meta_graph_edges(meta_graph):
    return sum(len(neighbors) for neighbors in meta_graph.values())


def compute_meta_graph_edges(node_count, adjacency_info):
    graph = {node: neighbors for node, neighbors in enumerate(adjacency_info)}

    components, leaders = kosaraju(graph, node_count)

    meta_graph = build_meta_graph(components, graph)

    return count_meta_graph_edges(meta_graph)


def main():
    n = int(input().strip())
    adjacency_info = []

    for _ in range(n):
        neighbors = list(map(int, input().strip().split()))
        neighbors = [node for node in neighbors if node != -1]
        adjacency_info.append(neighbors)

    meta_graph_edges = compute_meta_graph_edges(n, adjacency_info)
    print(meta_graph_edges)

if __name__ == "__main__":
    main()