
import java.util.*;

/**
 * Computes the number of edges in the meta-graph of a directed graph using Kosaraju's algorithm.
 *
 * @author Kapil Sharma ks4643
 * @author Dharma Teja ds3519
 */
public class MetaEdges {

    /**
     * Depth-first search to explore the graph and record the finish order of nodes.
     *
     * @param graph The directed graph.
     * @param start The starting node for the DFS.
     * @param visited Array to track visited nodes.
     * @param finishOrder List to store the finish order of nodes.
     * @param leaders Array to store leader node for each component.
     * @param currentLeader The leader node for the current DFS iteration.
     */
    private static void dfs(Map<Integer, List<Integer>> graph, int start, boolean[] visited, List<Integer> finishOrder, int[] leaders, int currentLeader) {
        visited[start] = true;
        leaders[start] = currentLeader;
        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, finishOrder, leaders, currentLeader);
            }
        }
        finishOrder.add(start);
    }

    /**
     * Reverses the direction of edges in the graph.
     *
     * @param graph The original directed graph.
     * @return The reversed graph.
     */
    private static Map<Integer, List<Integer>> reverseGraph(Map<Integer, List<Integer>> graph) {
        Map<Integer, List<Integer>> reversedGraph = new HashMap<>();
        for (int node : graph.keySet()) {
            reversedGraph.put(node, new ArrayList<>());
        }
        for (int node : graph.keySet()) {
            for (int neighbor : graph.get(node)) {
                reversedGraph.get(neighbor).add(node);
            }
        }
        return reversedGraph;
    }

    /**
     * Finds strongly connected components in the directed graph using Kosaraju's algorithm.
     *
     * @param graph The directed graph.
     * @param nodeCount The total number of nodes in the graph.
     * @return A list of strongly connected components.
     */
    private static List<List<Integer>> findStronglyConnectedComponents(Map<Integer, List<Integer>> graph, int nodeCount) {
        boolean[] visited = new boolean[nodeCount];
        List<Integer> finishOrder = new ArrayList<>();
        int[] leaders = new int[nodeCount];

        for (int node = 0; node < nodeCount; node++) {
            if (!visited[node]) {
                dfs(graph, node, visited, finishOrder, leaders, node);
            }
        }

        Map<Integer, List<Integer>> reversedGraph = reverseGraph(graph);

        Arrays.fill(visited, false);
        List<List<Integer>> components = new ArrayList<>();
        while (!finishOrder.isEmpty()) {
            int node = finishOrder.remove(finishOrder.size() - 1);
            if (!visited[node]) {
                List<Integer> componentFinishOrder = new ArrayList<>();
                dfs(reversedGraph, node, visited, componentFinishOrder, leaders, node);
                components.add(componentFinishOrder);
            }
        }

        return components;
    }

    /**
     * Builds the meta-graph of the strongly connected components.
     *
     * @param components The list of strongly connected components.
     * @param graph The original directed graph.
     * @return The meta-graph represented as a map of component indices to sets of neighboring component indices.
     */
    private static Map<Integer, Set<Integer>> buildMetaGraph(List<List<Integer>> components, Map<Integer, List<Integer>> graph) {
        Map<Integer, Set<Integer>> metaGraph = new HashMap<>();
        Map<Integer, Integer> componentIndex = new HashMap<>();
        for (int i = 0; i < components.size(); i++) {
            metaGraph.put(i, new HashSet<>());
            for (int node : components.get(i)) {
                componentIndex.put(node, i);
            }
        }

        for (List<Integer> component : components) {
            for (int node : component) {
                int nodeIndex = componentIndex.get(node);
                for (int neighbor : graph.get(node)) {
                    int neighborIndex = componentIndex.get(neighbor);
                    if (nodeIndex != neighborIndex) {
                        metaGraph.get(nodeIndex).add(neighborIndex);
                    }
                }
            }
        }

        return metaGraph;
    }

    /**
     * Counts the total number of edges in the meta-graph.
     *
     * @param metaGraph The meta-graph represented as a map of component indices to sets of neighboring component indices.
     * @return The total number of edges in the meta-graph.
     */
    private static int countMetaGraphEdges(Map<Integer, Set<Integer>> metaGraph) {
        int count = 0;
        for (Set<Integer> neighbors : metaGraph.values()) {
            count += neighbors.size();
        }
        return count;
    }

    /**
     * Computes the total number of edges in the meta-graph of the given directed graph.
     *
     * @param nodeCount The total number of nodes in the graph.
     * @param adjacencyInfo The adjacency information of the graph.
     * @return The total number of edges in the meta-graph.
     */
    private static int computeMetaGraphEdges(int nodeCount, List<List<Integer>> adjacencyInfo) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < adjacencyInfo.size(); i++) {
            graph.put(i, adjacencyInfo.get(i));
        }

        List<List<Integer>> components = findStronglyConnectedComponents(graph, nodeCount);

        Map<Integer, Set<Integer>> metaGraph = buildMetaGraph(components, graph);

        return countMetaGraphEdges(metaGraph);
    }

    /**
     * Main method to read input and compute the number of edges in the meta-graph.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeCount = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> adjacencyInfo = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            List<Integer> neighbors = new ArrayList<>();
            String[] tokens = scanner.nextLine().split(" ");
            for (String token : tokens) {
                int neighbor = Integer.parseInt(token);
                if (neighbor != -1) {
                    neighbors.add(neighbor);
                }
            }
            adjacencyInfo.add(neighbors);
        }

        int metaGraphEdges = computeMetaGraphEdges(nodeCount, adjacencyInfo);
        System.out.println(metaGraphEdges);
    }
}