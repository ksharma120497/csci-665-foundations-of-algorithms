import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519
 * Sub class implements a solution for a dynamic programming problem.
 */
public class Sub {

    /**
     * Finds the maximum of four integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @param c the third integer
     * @param d the fourth integer
     * @return the maximum of the four integers
     */
    public static int findMax(int a, int b, int c, int d) {
        return Math.max(Math.max(Math.max(a, b), c), d);
    }

    /**
     * The main method reads input values, processes them, and prints the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // Read the total number of jobs
        int totalNumberOfJobs = scanner.nextInt();

        // Create a 2D array to store job information: cost, value, and category
        int[][] jobInfo = new int[totalNumberOfJobs][3];

        // Read job information for each job and store it in the jobInfo array
        for (int i = 0; i < totalNumberOfJobs; i++) {
            jobInfo[i][0] = scanner.nextInt(); // cost
            jobInfo[i][1] = scanner.nextInt(); // value
            jobInfo[i][2] = scanner.nextInt(); // category
        }

        // Sort the jobInfo array based on the finish time of each job
        Arrays.sort(jobInfo, Comparator.comparingInt((int[] arr) -> arr[1]));

        // Calculate the finish time of the last job
        int finishTime = jobInfo[totalNumberOfJobs - 1][1];

        // Initialize arrays to store dynamic programming values for each category
        int[] dp01 = new int[finishTime + 1]; // category 0, no job selected
        int[] dp02 = new int[finishTime + 1]; // category 0, job selected
        int[] dp11 = new int[finishTime + 1]; // category 1, no job selected
        int[] dp12 = new int[finishTime + 1]; // category 1, job selected

        // Initialize the dynamic programming values for the first job
        if (jobInfo[0][2] == 0)
            dp01[jobInfo[0][1]] = 1;
        else
            dp11[jobInfo[0][1]] = 1;

        // Calculate dynamic programming values for each job
        for (int i = 1; i < totalNumberOfJobs; i++) {
            // Fill in the dynamic programming arrays for time intervals without jobs
            for (int j = jobInfo[i - 1][1] + 1; j <= jobInfo[i][1]; j++) {
                dp01[j] = dp01[j - 1];
                dp02[j] = dp02[j - 1];
                dp11[j] = dp11[j - 1];
                dp12[j] = dp12[j - 1];
            }
            // Update dynamic programming values for the current job
            if (jobInfo[i][2] == 0) {
                dp01[jobInfo[i][1]] = Math.max(1 + Math.max(dp11[jobInfo[i][0]], dp12[jobInfo[i][0]]), dp01[jobInfo[i][1]]);
                dp02[jobInfo[i][1]] = Math.max(1 + dp01[jobInfo[i][0]], dp02[jobInfo[i][1]]);
            } else {
                dp11[jobInfo[i][1]] = Math.max(1 + Math.max(dp01[jobInfo[i][0]], dp02[jobInfo[i][0]]), dp11[jobInfo[i][1]]);
                dp12[jobInfo[i][1]] = Math.max(1 + dp11[jobInfo[i][0]], dp12[jobInfo[i][1]]);
            }
        }

        // Print the maximum value obtained from dynamic programming
        System.out.println(findMax(dp01[finishTime], dp02[finishTime], dp11[finishTime], dp12[finishTime]));
    }
}
