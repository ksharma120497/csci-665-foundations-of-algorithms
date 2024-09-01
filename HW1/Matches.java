/**
 * @Author Kapil Sharma ks4643
 * @Author Dharma Teja ds3519
 * This program finds if there exists more than 2set of stable matching pairs
 * Time Complexity O(N^2)
 * Space Complexity O(N^2)
 *
 * We are using Gale Shapely algorithm twice to give two pairs that are stable matches
 * We compare those matches and check if they have any common pairings
 * */

import java.util.*;

public class Matches {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of lines
        int numberOfInputs = scanner.nextInt();

        // Initialize lists for student and professor preferences, and their inverses
        List<List<Integer>> studentPrefs = new ArrayList<>();
        List<List<Integer>> professorPrefs = new ArrayList<>();
        List<List<Integer>> inverseProfessorPrefs = new ArrayList<>();
        List<List<Integer>> inverseStudentPrefs = new ArrayList<>();

        // Flag to track if two stable matching exist
        boolean isStableMatch = false;

        // Populate inverse professor and inverse student lists with default values
        for (int i = 0; i < numberOfInputs; i++) {
            inverseProfessorPrefs.add(new ArrayList<>(Collections.nCopies(numberOfInputs, -1)));
            inverseStudentPrefs.add(new ArrayList<>(Collections.nCopies(numberOfInputs, -1)));
        }

        // Input professor preferences and populate inverse professor list
        for (int i = 0; i < numberOfInputs; i++) {
            List<Integer> pref = new ArrayList<>();
            for (int j = 0; j < numberOfInputs; j++) {
                pref.add(scanner.nextInt());
                inverseProfessorPrefs.get(i).set(pref.get(j), j);
            }
            professorPrefs.add(pref);
        }

        // Input student preferences and populate inverse student list
        for (int i = 0; i < numberOfInputs; i++) {
            List<Integer> pref = new ArrayList<>();
            for (int j = 0; j < numberOfInputs; j++) {
                pref.add(scanner.nextInt());
                inverseStudentPrefs.get(i).set(pref.get(j), j);
            }
            studentPrefs.add(pref);
        }

        int[] professorAsker = applyGaleShapley(numberOfInputs, inverseStudentPrefs);
        int[] studentAsker = applyGaleShapley(numberOfInputs, inverseProfessorPrefs);


        //Check if there are any common pairings between the two arrays
        for(int i=0; i<numberOfInputs; i++){
            if(professorAsker[i]==studentAsker[i]){
                isStableMatch=true;
                break;
            }
        }

        // Output the result
        if (!isStableMatch) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    // Makes use of Gale Shapely algorithm with asker as input1
    public static int[] applyGaleShapley(int numberOfInputs, List<List<Integer>> inverseStudentProfessor) {
        List<Integer> professorStack = new ArrayList<>();
        int[] count = new int[numberOfInputs];
        int[] partnerStudent = new int[numberOfInputs];
        Arrays.fill(partnerStudent, -1);


        // Filling professor stack with all the professor
        for (int i = 0; i < numberOfInputs; i++) {
            professorStack.add(i);
            count[i] = 0;
        }


        while (!professorStack.isEmpty() && professorStack.get(0) < numberOfInputs) {
            int prof = professorStack.remove(0); // stack of professor
            int stu = count[prof];
            count[prof]++;
            if (partnerStudent[stu] == -1) {
                partnerStudent[stu] = prof;
            } else if (inverseStudentProfessor.get(stu).get(prof) < inverseStudentProfessor.get(stu).get(partnerStudent[stu])) {
                professorStack.add(partnerStudent[stu]);
                partnerStudent[stu] = prof;
            } else {
                professorStack.add(prof);
            }
        }

        return partnerStudent;
    }
}

