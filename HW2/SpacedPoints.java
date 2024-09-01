

/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519

 * Time Complexity O(N)
 *
 * */

import java.util.ArrayList;
import java.util.Scanner;

public class SpacedPoints {
    public static void main(String args[]){
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Read the number of input points
        int numberOfPoints = scanner.nextInt();

        // Create an ArrayList to store input points
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();

        // Read input points and add them to the ArrayList
        for(int i=0; i<numberOfPoints; i++){
            int xCoordinate = scanner.nextInt();
            int yCoordinate = scanner.nextInt();
            ArrayList<Integer> point = new ArrayList<>();
            point.add(xCoordinate);
            point.add(yCoordinate);
            points.add(point);
        }

        // Calculate the maximum spacing along the x and y axes
        int maxXSpacing = calculateMaxSpacing(numberOfPoints, points, 0, 1);
        int maxYSpacing = calculateMaxSpacing(numberOfPoints, points, 1, 0);

        // Determine the maximum spacing among x and y axes
        int totalMaxSpacing = Math.max(maxXSpacing, maxYSpacing);
        int maxSpacing = totalMaxSpacing > 1 ? totalMaxSpacing - 1 : totalMaxSpacing;
        System.out.println(maxSpacing);
    }

    // Method to calculate the maximum spacing
    public static int calculateMaxSpacing(int numberOfPoints, ArrayList<ArrayList<Integer>> points, int xAxisIndex, int yAxisIndex){
        // Create an ArrayList to store output points
        ArrayList<ArrayList<Integer>> outputPoints = new ArrayList<>();

        // Create a 2D array to store sorted input points
        Integer[][] sortedInput = new Integer[numberOfPoints][2];

        // Create arrays to store counts and temporary values
        int[] b = new int[numberOfPoints];
        int[] count = new int[numberOfPoints];

        // Initialize count array and outputPoints
        for(int i=0; i<numberOfPoints; i++){
            count[i] = 0;
            outputPoints.add(new ArrayList<>());
        }

        // Count occurrences of x coordinates
        for(int i=0; i<numberOfPoints; i++){
            count[points.get(i).get(xAxisIndex)]++;
        }

        // Calculate cumulative counts
        for(int i=1; i<numberOfPoints; i++){
            count[i] = count[i] + count[i-1];
        }

        // Rearrange the input points based on x coordinates
        for(int i=numberOfPoints-1; i>=0; i--){
            int k = count[points.get(i).get(xAxisIndex)] - 1;
            b[k] = points.get(i).get(xAxisIndex);
            sortedInput[k] = points.get(i).toArray(new Integer[2]);
            count[points.get(i).get(xAxisIndex)]--;
        }

        // Process sorted points to find maximum spacing
        for(int i=0; i<sortedInput.length; i++){
            if(sortedInput[i].length > 0) {
                int x = sortedInput[i][yAxisIndex];
                int y = sortedInput[i][xAxisIndex];
                ArrayList<Integer> a = outputPoints.get(x);

                if(a.size() > 2) {
                    Integer previousY = a.get(a.size() - 2);
                    Integer diff = a.get(a.size() - 1);
                    a.remove(a.size() - 1);
                    if (y - previousY == diff) {
                        a.add(y);
                        a.add(diff);
                    } else {
                        a.add(diff);
                        a.add(-1);
                    }
                } else if(a.size() == 1) {
                    Integer previousY = a.get(0);
                    a.add(y);
                    a.add(y - previousY);
                } else {
                    a.add(y);
                }
            }
        }

        // Find the maximum spacing
        int maxSpacing = 0;
        for(ArrayList<Integer> a: outputPoints){
            if(a.size() > maxSpacing && a.get(a.size()-1) != -1) {
                maxSpacing = a.size();
            }
        }
        return maxSpacing;
    }
}
