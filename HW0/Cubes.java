

/*
    @Author Kapil Sharma ks4643

    This program finds the perfect cubes from 0 to a particular number given as input1

    This program calculates all the cubes till that value and stores it in array
    Then we loop on that array and print all the number that are less than or equal to that value


    Time Complexity: O(n)

 */
import java.util.Scanner;

public class Cubes {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        int[] cubes = new int[value+1];
        for(int i=0; i<=value; i++){
            cubes[i] = i*i*i;
        }

        for(int i=0; i<cubes.length; i++){
            if(cubes[i]<=value)
                System.out.println(cubes[i]);
        }

    }
}
