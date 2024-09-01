import java.util.*;

/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519
 * Curator class implements a solution for the knapsack problem with multiple categories.
 */
public class Curator {

    /**
     * Solves the knapsack problem with multiple categories.
     *
     * @param B               the capacity of the knapsack
     * @param array           a 2D array containing the items with their costs, values, and categories
     * @param totalCategories the total number of categories
     * @return the maximum value that can be obtained by filling the knapsack
     */
    public static int knapsack(int B, int[][] array, int totalCategories) {
        // Initialize a 2D array to store the maximum values
        int[][] S = new int[totalCategories + 1][B + 1];
        for (int i = 0; i <= totalCategories; i++) {
            Arrays.fill(S[i], 0);
        }

        // Initialize a 2D array to store the last selected item's cost and value for each category
        int[][] lastSelectedCategoryValue = new int[totalCategories][2];

        // Iterate over each item in the array
        for (int[] item : array) {
            int cost = item[0];
            int value = item[1];
            int category = item[2];

            // Check if the current item is selected based on the last selected item in the same category
            if (lastSelectedCategoryValue[category - 1][0] == 0 ||
                    lastSelectedCategoryValue[category - 1][0] != cost ||
                    lastSelectedCategoryValue[category - 1][1] < value) {
                // Update the maximum value for each capacity in the knapsack
                for (int v = 1; v <= B; v++) {
                    S[category][v] = Math.max(S[category][v], S[category - 1][v]);
                    if (cost <= v) {
                        S[category][v] = Math.max(S[category][v], S[category - 1][v - cost] + value);
                    }
                }
                // Update the last selected item's cost and value for the current category
                lastSelectedCategoryValue[category - 1][0] = cost;
                lastSelectedCategoryValue[category - 1][1] = value;
            }
        }

        // Return the maximum value that can be obtained by filling the knapsack
        return S[totalCategories][B];
    }

    /**
     * The main method reads input values, sorts items based on category and cost, and calls the knapsack method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a scanner object to read input from standard input
        Scanner scanner = new Scanner(System.in);

        // Read the number of items and the capacity of the knapsack
        int n = scanner.nextInt();
        int B = scanner.nextInt();

        // Create a 2D array to store the items with their costs, values, and categories
        int[][] items = new int[n][3];

        // Read the items and store them in the items array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                items[i][j] = scanner.nextInt();
            }
        }

        // Sort the items based on category and cost
        Arrays.sort(items, (a, b) -> a[2] != b[2] ? Integer.compare(a[2], b[2]) : Integer.compare(a[0], b[0]));

        // Calculate the total number of categories
        int totalCategories = Arrays.stream(items).mapToInt(a -> a[2]).max().orElse(0);

        // Call the knapsack method and print the result
        System.out.println(knapsack(B, items, totalCategories));
    }
}