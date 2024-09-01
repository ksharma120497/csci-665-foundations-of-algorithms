/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519
 * Time complexity O(log n) or o(n)
 * Space Complexity O(n)
 *
 * The program finds whether there is a three-day interval that is
 * more than half of the total snowfall
 *
 * We are using binary search to do that and divide the array based on the
 * value of the mid
 *
 * */

import java.util.Scanner;

public class Snowfall {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numberOfInputs = scanner.nextInt();
        int[] arr = new int[numberOfInputs];
        for(int i=0; i<numberOfInputs; i++){
            arr[i] = scanner.nextInt();
        }
        System.out.println(snowfall(arr));
    }

    public static String snowfall(int arr[]){
        int right = arr.length-1;
        int left = 0;
        int mid= (left+right)/2 ;
        int target = arr[right]/2;
        int m = mid > 1 ? mid-2: 0;
        if(arr[mid+1] - arr[m] > target) // check if find the answer in our first mid value
            return "YES";

        while(right-left > 2){ // check if the difference is not more than two so that it doesnt overlap
             mid = (left+right)/2;
            if(arr[mid]> target){
                if (right - left == 3)
                    right = mid+1;
                else
                    right = mid;
            }
            else
                left = mid;
        };

        int l = left > 0 ? left-1 : 0;
        if(arr[right+1] - arr[left] > target) // consider one three array window
            return "YES";
        if(arr[right] - arr[l] > target) // another array window
            return "YES";
        return "NO";
    }
}
