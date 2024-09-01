import java.util.Scanner;

/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519
 * LAIS2 class implements a solution for the Longest Alternating Increasing Subsequence (LAIS) problem.
 * It calculates the length of the longest alternating increasing subsequence for two given arrays.
 */
public class LAIS2 {

    /**
     * The main method reads input values, calculates the length of the longest alternating increasing subsequence,
     * and prints the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        // Read the size of the two arrays
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Read the elements of the first array
        int[] input1 = new int[n];
        for (int i=0; i<n; i++){
            input1[i] = scanner.nextInt();
        }

        // Read the elements of the second array
        int[] input2 = new int[m];
        for (int i=0; i<m; i++){
            input2[i] = scanner.nextInt();
        }

        // Initialize dynamic programming arrays
        int[][] dp1 = new int[m][n];
        int[][] dp2 = new int[n][m];

        // Initialize all elements of dp1 and dp2 to 1
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dp1[i][j] = 1;
                dp2[j][i] = 1;
            }
        }

        int answer = 0;
        // Calculate the length of the longest alternating increasing subsequence
        for(int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate the length of the longest increasing subsequence ending at index j in input1
                int max1 = 0;
                for (int k = 0; k < j+1; k++) {
                    if (input2[i] > input1[k]) {
                        if (i == 0) {
                            max1 = Math.max(max1, 1);
                        } else {
                            int curr = dp2[k][i - 1];
                            if (curr > max1) {
                                max1 = curr;
                            }
                        }
                    }
                }
                dp1[i][j] = 1 + max1;
                answer = Math.max(answer, dp1[i][j]);

                // Calculate the length of the longest decreasing subsequence ending at index i in input2
                int max2 = 0;
                for (int k = 0; k < i+1; k++) {
                    if (input2[k] < input1[j]) {
                        if (j == 0) {
                            max2 = Math.max(max2, 1);
                        } else {
                            int curr = dp1[k][j - 1];
                            if (curr > max2) {
                                max2 = curr;
                            }
                        }
                    }
                }
                dp2[j][i] = 1 + max2;
                answer=Math.max(answer, dp2[j][i]);
            }
        }

        // Print the length of the longest alternating increasing subsequence
        System.out.println(answer);
    }
}
