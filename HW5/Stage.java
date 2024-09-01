import java.util.Scanner;

/**
 * The Stage class calculates the minimum possible height difference on the stage when selected from two lines.
 * It uses dynamic programming to efficiently find the optimal solution.
 *
 * @author Kapil Sharma ks4643
 * @author Dharma Teja ds3519
 */
public class Stage {
    /**
     * Main method to execute the program.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        // Read the lengths of the two lines
        int aLength = scanner.nextInt();
        int bLength = scanner.nextInt();

        // Initialize arrays to store the heights of the two lines
        int[] lineA = new int[aLength+1];
        int[] lineB = new int[bLength+1];

        // Initialize 3D array for dynamic programming
        int[][][] dp = new int[aLength+1][bLength+1][2];

        // Read heights of line A
        for(int i=1; i<=aLength; i++){
            lineA[i] = scanner.nextInt();
        }

        // Read heights of line B
        for(int i=1; i<=bLength; i++){
            lineB[i] = scanner.nextInt();
        }

        // Base cases for dynamic programming
        dp[0][0][0]= 0;
        dp[0][0][1]= 0;
        dp[1][0][0]= 0;
        dp[1][0][1]= 0;
        dp[0][1][0]= 0;
        dp[0][1][1]= 0;

        // Populate the dynamic programming table
        for(int i=2; i<=aLength; i++){
            dp[i][0][0] = dp[i-1][0][0] + Math.abs(lineA[i]-lineA[i-1]);
            dp[i][0][1] = dp[i-1][0][1] + Math.abs(lineA[i]-lineA[i-1]);
        }

        for(int i=2; i<=bLength; i++){
            dp[0][i][0] = dp[0][i-1][0] + Math.abs(lineB[i]-lineB[i-1]);
            dp[0][i][1] = dp[0][i-1][1] + Math.abs(lineB[i]-lineB[i-1]);
        }

        for(int i=1; i<=aLength; i++){
            for(int j=1; j<=bLength; j++){
                dp[i][j][0]=Math.min(Math.abs(lineB[j]-lineB[j-1])+dp[i][j-1][0],Math.abs(lineA[i]-lineB[j])+dp[i][j-1][1]);
                dp[i][j][1]=Math.min(Math.abs(lineA[i]-lineB[j])+dp[i-1][j][0],Math.abs(lineA[i-1]-lineA[i])+dp[i-1][j][1]);
            }
        }

        // Output the minimum possible height difference
        System.out.println(Math.min(dp[aLength][bLength][0], dp[aLength][bLength][1]));
    }
}
