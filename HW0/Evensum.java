

/*
    @Author Kapil Sharma ks4643

    This program sums all the even number that are given as inputs

    The program reads the inputs in a loop and checks whether that input1 is even or not
    If that number is even then it is added to a sum and then that sum is displayed

    Time Complexity: O(n)

 */
import java.util.Scanner;

public class Evensum {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int numberOfInput = sc.nextInt();
        sc.nextLine();
        int sum = 0;
        String valueString="0";

        for(int i=0; i<numberOfInput; i++){
            if(sc.hasNextLine()) {
                valueString = sc.nextLine();
            }
            int value = Integer.parseInt(valueString);
            if(value % 2 == 0)
                sum=sum+value;
        }
        System.out.println(sum);
    }
}
//        for(int i=0; i<numberOfInputs; i++){
//            System.out.println("Array A"+ Arrays.toString(groupA[i]));
//        }
//        for(int i=0; i<numberOfInputs; i++){
//            System.out.println("Array B"+ Arrays.toString(groupB[i]));
//        }
//        for(int i=0; i<numberOfInputs; i++){
//            System.out.println("Inverse Array A"+ Arrays.toString(inverseGroupA[i]));
//        }
//        for(int i=0; i<numberOfInputs; i++){
//            System.out.println("Inverse Array B"+ Arrays.toString(inverseGroupB[i]));
//        }