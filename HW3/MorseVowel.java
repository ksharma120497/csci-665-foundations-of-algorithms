/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519
 *
 * The purpose of this program is to calculate the number of ways a Morse code string can be represented.
 * It uses a dynamic programming approach to solve smaller subproblems that eventually lead to larger subproblems.
 *
 * Time Complexity: O(N)
 */

import java.util.Scanner;

public class MorseVowel {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        // Input the length of the Morse code string
        int lengthOfString = scanner.nextInt();
        // Input the Morse code string
        String morseCode = scanner.next();
        // Array to store the number of ways to represent Morse code substrings
        int[] dp = new int[lengthOfString];

        // Iterate through each character in the Morse code string
        for(int i=0; i<lengthOfString; i++){
            // Check if the substring from index i to i+1 is a vowel
            if(i>=0) {
                if (isVowel(morseCode.substring(i,i+1))) {
                    if(i==0) {
                        dp[i]++; // If it's the first character, increment the count by 1
                        continue;
                    }
                    else
                        dp[i]+=dp[i-1]; // Otherwise, add the count from the previous character
                }
            }
            // Check if the substring from index i-1 to i+1 is a vowel
            if(i>=1) {
                if (isVowel(morseCode.substring(i - 1, i + 1))) {
                    if (i == 1) {
                        dp[i]++; // If it's the second character, increment the count by 1
                        continue;
                    } else
                        dp[i] += dp[i - 2]; // Otherwise, add the count from two characters back
                }
            }
            // Check if the substring from index i-2 to i+1 is a vowel
            if(i>=2) {
                if (isVowel(morseCode.substring(i - 2, i + 1))){
                    if (i == 2) {
                        dp[i]++; // If it's the third character, increment the count by 1
                        continue;
                    } else
                        dp[i] += dp[i - 3]; // Otherwise, add the count from three characters back
                }
            }
        }
        // Output the number of ways to represent the entire Morse code string
        System.out.println(dp[lengthOfString-1]);
    }

    // Method to check if a Morse code represents a vowel
    public static boolean isVowel(String code){
        return code.equals(".") || code.equals("..") || code.equals(".-") || code.equals("..-") || code.equals("---");
    }
}
