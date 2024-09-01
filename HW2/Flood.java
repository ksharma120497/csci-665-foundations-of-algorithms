package HW2;

import java.util.*;

public class Flood {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read inputs
        int n = scanner.nextInt(); // number of cracks
        int threshold = scanner.nextInt(); // threshold for flooding
        int drainRate = scanner.nextInt(); // drain rate of the village
        int[] time = new int[n];
        int[] cracks = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = scanner.nextInt();
            cracks[i] = scanner.nextInt();
        }

        // Output the result
        String[] result = fixCracks(n, threshold, drainRate, time, cracks);
        for (String line : result) {
            System.out.println(line);
        }
    }

    public static String[] fixCracks(int n, int threshold, int drainRate, int[] time, int[] cracks) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        int currentTime = time[0];
        int totalFloodwaters = 0;
        int waterInQueue = cracks[0];
        int maxFloodWater = Integer.MIN_VALUE;
        pq.add(new int[]{time[0], cracks[0]});
        int idx = 1;

        while(idx < n && !pq.isEmpty()) {
            System.out.println("idx = " + idx + "time[idx] =" + time[idx] + "currentTime =" + currentTime);
            System.out.println(Arrays.toString(pq.peek()));

            if(time[idx] == currentTime) {
                pq.add(new int[]{time[idx], cracks[idx]});
                waterInQueue += cracks[idx];
                idx += 1;
                System.out.println("waterInQueue = " + waterInQueue);
                continue;
            }

            else {
                System.out.println();
                int[] maxCrack = pq.poll();
                waterInQueue -= maxCrack[1];
                System.out.println("waterInQueue = " + waterInQueue);
                currentTime += 1;
                int currTime = 0;
                if (currentTime != 0) {
                    for (int i = currentTime; i < idx; i++) {
                        System.out.println("i = " + i + " ");
                        currTime += currentTime - time[i] - 1;
                    }
                }

                System.out.println("currTime =" + currTime);
                totalFloodwaters += currentTime != 1 ? waterInQueue + currTime - drainRate : waterInQueue-drainRate;
                System.out.println("totalFloodwaters = " + totalFloodwaters);

                if (totalFloodwaters >= threshold) {
                    return new String[]{"FLOOD", String.valueOf(currentTime), String.valueOf(totalFloodwaters)};
                }
            }
            maxFloodWater = Math.max(maxFloodWater, totalFloodwaters);
            System.out.println("maxFloodWater = " + maxFloodWater);
        }
        return new String[]{"SAFE", Integer.toString(maxFloodWater)};
    }
}