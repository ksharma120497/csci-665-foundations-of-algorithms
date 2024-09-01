import java.util.Scanner;

/**
 * The Ski class determines the longest path you can follow down the mountain,
 * such that each successive cell in your path has an altitude lower than the previous cell.
 * It implements an algorithm with dynamic programming to find the longest downhill path.
 *
 * @author Kapil Sharma ks4643
 * @author Dharma Teja ds3519
 */
public class Ski {
    /** Array representing the directions of movement: right, down, left, up, and diagonally */
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},{-1,-1},{1,1},{-1,1},{1,-1}};

    /**
     * Recursively finds the length of the longest downhill path starting from the specified cell.
     * Uses memoization to avoid redundant calculations.
     *
     * @param currRow the current row index
     * @param currCol the current column index
     * @param altitudeValues the array of altitude values representing the mountain
     * @param maxPathLengthForAltitudes memoization array to store the length of the longest path for each cell
     * @return the length of the longest downhill path starting from the current cell
     */
    private static int findMaxPathForAllAltitudes(int currRow, int currCol, int[][] altitudeValues, int[][] maxPathLengthForAltitudes) {
        // If the longest path for the current cell has not been calculated yet
        if (maxPathLengthForAltitudes[currRow][currCol] == 0) {
            // Explore all directions from the current cell
            for (int[] direction : DIRECTIONS) {
                int newRow = currRow + direction[0], newCol = currCol + direction[1];
                // Check if the new cell is within the bounds and has a lower altitude
                if (newRow >= 0 && newRow < altitudeValues.length && newCol >= 0 && newCol < altitudeValues[0].length &&
                        altitudeValues[newRow][newCol] < altitudeValues[currRow][currCol]) {
                    // Recursively find the longest path from the new cell and update the current longest path
                    maxPathLengthForAltitudes[currRow][currCol] = Math.max(maxPathLengthForAltitudes[currRow][currCol],
                            findMaxPathForAllAltitudes(newRow, newCol, altitudeValues, maxPathLengthForAltitudes));
                }
            }
            // Increment the longest path for the current cell
            maxPathLengthForAltitudes[currRow][currCol] += 1;
        }
        // Return the length of the longest downhill path for the current cell
        return maxPathLengthForAltitudes[currRow][currCol];
    }

    /**
     * Reads altitude values from standard input and computes the longest downhill path on the mountain.
     *
     * @param args command line arguments (not used)
     * @throws Exception if an error occurs while reading input
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] altitudeValues = new int[rows][columns];
        // Read altitude values from input
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                altitudeValues[i][j] = scanner.nextInt();
            }
        }

        // Initialize memoization array
        int[][] maxPathLengthForAltitudes = new int[altitudeValues.length][altitudeValues[0].length];

        int longestPath = 0;
        // Iterate over all cells to find the longest downhill path
        for (int row = 0; row < altitudeValues.length; row++) {
            for (int col = 0; col < altitudeValues[0].length; col++) {
                longestPath = Math.max(longestPath, findMaxPathForAllAltitudes(row, col, altitudeValues, maxPathLengthForAltitudes));
            }
        }

        // Print the length of the longest downhill path
        System.out.println(longestPath - 1); // Subtracting 1 to exclude the starting cell from the path length
    }
}
