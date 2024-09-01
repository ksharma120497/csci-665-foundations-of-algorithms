import java.util.*;

/**
 * The PickHalf class checks whether it is possible to select n/2 cards out of n cards
 * such that they form a sequence of numbers with each number occurring exactly once.
 * It utilizes graph theory and cycle detection to determine the feasibility.
 *
 * @author Kapil Sharma ks4643
 * @author Dharma Teja ds3519
 */
public class PickHalf {

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param graph The graph represented as a mapping of vertices to their neighbors.
     * @param u     The first vertex.
     * @param v     The second vertex.
     */
    public static void addEdge(Map<Integer, List<Integer>> graph, int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    /**
     * Gets the lengths of all cycles in the graph.
     *
     * @param n      The number of cards.
     * @param cards  The array representing the pairs of cards.
     * @return       A list containing the lengths of all cycles in the graph.
     */
    public static List<Integer> getCycleLengths(int n, int[][] cards) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int count = 0;
        Set<Integer> cycles = new HashSet<>();

        // Construct the graph and count the number of distinct pairs of cards
        for (int[] card : cards) {
            addEdge(graph, card[0], card[1]);
            if (card[0] != card[1]) {
                count++;
            }
        }

        // If the number of distinct pairs equals n - 1, return a cycle length of 2
        if (count == n - 1) {
            cycles.add(2);
            return new ArrayList<>(cycles);
        }

        // Perform BFS to detect cycles in the graph
        int[] visited = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (visited[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = 1;
                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                        if (visited[neighbor] == 0) {
                            queue.add(neighbor);
                            visited[neighbor] = visited[current] + 1;
                        } else if (visited[neighbor] != visited[current] - 1) {
                            int cycleLength = visited[current] - visited[neighbor] + 1;
                            cycles.add(cycleLength);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(cycles);
    }

    /**
     * Solves the PickHalf problem by reading input, determining the cycle lengths,
     * and checking if the lengths allow selecting n/2 cards.
     *
     * @return "YES" if it's possible to select n/2 cards with distinct numbers, "NO" otherwise.
     */
    public static String solvePickHalf() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] nArray = new int[n][2];
        for (int i = 0; i < n; i++) {
            nArray[i][0] = scanner.nextInt();
            nArray[i][1] = scanner.nextInt();
        }

        List<Integer> cycleLength = getCycleLengths(n, nArray);

        // Check if the cycle lengths allow selecting n/2 cards with distinct numbers
        for (Integer i : cycleLength) {
            if (Math.abs(i) % 2 != 0)
                return "NO";
        }
        return "YES";
    }

    /**
     * Main method to execute the program.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println(solvePickHalf());
    }
}
