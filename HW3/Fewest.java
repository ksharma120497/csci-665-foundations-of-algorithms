/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519
 *
 * This program finds the fewest number of elements whose sum is required to reach the target.
 *
 * Time Complexity: O(N)
 * We are using the k select algorithm for this, which runs in an expected time complexity of O(n).
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fewest {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        // Input the number of values and the target sum
        int numberOfValues = scanner.nextInt();
        int target = scanner.nextInt();

        // Initialize an ArrayList to store the input values
        ArrayList<Integer> arrayList = new ArrayList<>();

        // Input the values into the ArrayList
        for(int i=0; i<numberOfValues; i++){
            int value = scanner.nextInt();
            arrayList.add(value);
        }

        // Output the fewest number of elements required to reach the target sum
        System.out.println(select(arrayList, target+1, 0));
    }

    // Method to select the fewest number of elements to reach the target sum
    public static int select(ArrayList<Integer> list, int target, int numberOfElements){
        ArrayList<Integer> l;
        ArrayList<Integer> e;
        ArrayList<Integer> g;

        // Loop until the list is not empty
        while (list.size() != 0) {
            l = new ArrayList<>();
            e = new ArrayList<>();
            g = new ArrayList<>();

            // Select the pivot element
            int pivot = list.get(0);

            // Partition the list into three parts: less than, equal to, and greater than the pivot
            for (int element : list) {
                if (element < pivot)
                    l.add(element);
                else if (element == pivot)
                    e.add(element);
                else
                    g.add(element);
            }

            // Check if the sum of elements greater than the pivot exceeds the target
            if (findSum(g) > target) {
                list = g; // If so, update the list to contain only elements greater than the pivot
                continue;
            } else {
                target -= findSum(g); // Otherwise, subtract the sum of elements greater than the pivot from the target
                numberOfElements += g.size(); // Increment the number of elements by the count of elements greater than the pivot
            }

            // Check if the sum of elements equal to the pivot exceeds the target
            if (findSum(e) > target) {
                numberOfElements += (int) Math.ceil(target / (float) e.get(0)); // If so, calculate the number of elements needed
                break;
            }
            else {
                target -= findSum(e); // Otherwise, subtract the sum of elements equal to the pivot from the target
                numberOfElements += e.size(); // Increment the number of elements by the count of elements equal to the pivot
                list = l; // Update the list to contain only elements less than the pivot
            }
        }

        return numberOfElements; // Return the fewest number of elements required to reach the target sum
    }

    // Method to calculate the sum of elements in a list
    public static int findSum(List<Integer> list){
        int sum = 0;
        for(Integer element: list) sum+=element; // Iterate through the list and sum up the elements
        return sum; // Return the total sum
    }
}
