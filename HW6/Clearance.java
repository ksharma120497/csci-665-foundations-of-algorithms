import java.util.*;

/**
 * Represents a Node in the computer network graph.
 *
 * @author Kapil Sharma ks4643
 * @author Dharma Teja ds3519
 *
 */
class Node {
    int computerNo;
    int clearanceLevel;
    boolean linked;

    /**
     * Constructs a Node with the given computer number and clearance level.
     *
     * @param computerNo     The number of the computer.
     * @param clearanceLevel The clearance level of the computer.
     */
    public Node(int computerNo, int clearanceLevel) {
        this.computerNo = computerNo;
        this.clearanceLevel = clearanceLevel;
        this.linked = false;
    }
}

/**
 * Represents an Edge in the computer network graph.
 */
class Edge {
    int source;
    int destination;
    int weight;

    /**
     * Constructs an Edge between two computers with the given source, destination, and weight.
     *
     * @param source      The source computer.
     * @param destination The destination computer.
     * @param weight      The weight of the edge.
     */
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

/**
 * Simulates the selection of computers with different clearance levels such that all
 * computers are connected with the minimum total cost.
 */
public class Clearance {
    // List of edges in the graph
    public static List<Edge> edges = new ArrayList<>();
    // Map to store nodes based on their clearance levels
    public static Map<Integer, Map<Integer, Node>> nodeConnection = new HashMap<>();
    // Map to store linked edges
    public static Map<Integer, Node> linkedEdge = new HashMap<>();
    // Flag to track if the first connection is being made
    public static boolean isFirstConnection = true;
    // Variable to store the total cost of connecting all computers
    public static int totalCost = 0;

    /**
     * Determines if all nodes of a given clearance level can be selected with the minimum cost.
     *
     * @param aConnection The nodes of a specific clearance level.
     * @return 1 if all nodes are selected; otherwise, 0.
     */
    public static int nodesAndClearanceLevel(Map<Integer, Node> aConnection) {
        int allNodesSelected = 0;
        int numberOfSelectedEdge = 0;

        for (Edge edge : edges) {
            if (isFirstConnection) {
                if (aConnection.get(edge.source) != null && aConnection.get(edge.destination) != null) {
                    aConnection.get(edge.source).linked = true;
                    aConnection.get(edge.destination).linked = true;
                    linkedEdge.put(edge.source, aConnection.get(edge.source));
                    linkedEdge.put(edge.destination, aConnection.get(edge.destination));
                    totalCost = edge.weight;
                    isFirstConnection = false;
                    aConnection.remove(edge.source);
                    aConnection.remove(edge.destination);
                    allNodesSelected = nodesAndClearanceLevel(aConnection);
                    numberOfSelectedEdge = 2;
                }
            } else if (aConnection.get(edge.source) != null && linkedEdge.get(edge.destination) != null &&
                    linkedEdge.get(edge.source) == null) {
                aConnection.get(edge.source).linked = true;
                linkedEdge.put(edge.source, aConnection.get(edge.source));
                totalCost += edge.weight;
                numberOfSelectedEdge += 1;
                aConnection.remove(edge.source);
                allNodesSelected = nodesAndClearanceLevel(aConnection);
                numberOfSelectedEdge -= aConnection.size();
                if (aConnection.size() <= numberOfSelectedEdge) {
                    allNodesSelected = 1;
                    break;
                }
            } else if (aConnection.get(edge.destination) != null && linkedEdge.get(edge.source) != null && linkedEdge
                    .get(edge.destination) == null) {
                aConnection.get(edge.destination).linked = true;
                linkedEdge.put(edge.destination, aConnection.get(edge.destination));
                totalCost += edge.weight;
                numberOfSelectedEdge++;
                aConnection.remove(edge.destination);
                allNodesSelected = nodesAndClearanceLevel(aConnection);
                numberOfSelectedEdge -= aConnection.size();
                if (aConnection.size() <= numberOfSelectedEdge) {
                    allNodesSelected = 1;
                    break;
                }
            }

            if (aConnection.size() <= numberOfSelectedEdge) {
                allNodesSelected = 1;
                break;
            }
        }

        return allNodesSelected;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of computers
        int m = sc.nextInt(); // Number of connections

        // Input node details
        for (int i = 0; i < n; i++) {
            Node node = new Node(i, sc.nextInt());
            if (!nodeConnection.containsKey(node.clearanceLevel)) {
                nodeConnection.put(node.clearanceLevel, new HashMap<>());
            }
            nodeConnection.get(node.clearanceLevel).put(node.computerNo, node);
        }

        // Input connection details
        for (int i = 0; i < m; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int weight = sc.nextInt();
            Edge edge = new Edge(source, destination, weight);
            edges.add(edge);
        }

        // Sort edges based on weight
        Collections.sort(edges, (o1, o2) -> o1.weight - o2.weight);

        // Check if all nodes can be selected with minimum cost
        for (int i = 1; i < 4; i++) {
            if (nodesAndClearanceLevel(nodeConnection.get(i)) == 0) {
                System.out.println(-1);
                return;
            }
        }
        // Output the total cost
        System.out.println(totalCost);
    }
}