package HW3; /**
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

public class Angles {

    public static int binarySearchCount(ArrayList<int[]> arr, int[] target) {
        int low = 0;
        int high = arr.size() - 1;
        int count = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            int[] midVal = arr.get(mid);

            if (midVal[0] == target[0] && midVal[1] == target[1]) {
                count++;
                int l = mid - 1;
                while (l >= low && arr.get(l)[0] == target[0] && arr.get(l)[1] == target[1]) {
                    count++;
                    l--;
                }
                int r = mid + 1;
                while (r <= high && arr.get(r)[0] == target[0] && arr.get(r)[1] == target[1]) {
                    count++;
                    r++;
                }
                return count;
            } else if (midVal[0] < target[0] && midVal[1] < target[1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return 0;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    public static List<List<Integer>> calculateSlopes(List<int[]> points) {
        List<List<Integer>> slopes = new ArrayList<>(points.size());
        for (int i = 0; i < points.size(); i++) {
            slopes.add(new ArrayList<>());
            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    int dy = points.get(j)[1] - points.get(i)[1];
                    int dx = points.get(j)[0] - points.get(i)[0];
                    int g = gcd(Math.abs(dx), Math.abs(dy));
                    if (g == 0) {
                        slopes.get(i).add(0);
                        slopes.get(i).add(0);
                    } else {
                        slopes.get(i).add(dx / g);
                        slopes.get(i).add(dy / g);
                    }
                }
            }
            Collections.sort(slopes.get(i));
        }
        return slopes;
    }

    public static int check(List<int[]> a) {
        ArrayList<int[]>[] slopes = new ArrayList[a.size()];
        for (int i = 0; i < a.size(); i++) {
            slopes[i] = new ArrayList<>();
            for (int[] p2 : a) {
                if (!Arrays.equals(a.get(i), p2)) {
                    int dy = p2[1] - a.get(i)[1];
                    int dx = p2[0] - a.get(i)[0];
                    int g = gcd(Math.abs(dx), Math.abs(dy));
                    int sdx = g == 0 ? 0 : dx / g;
                    int sdy = g == 0 ? 0 : dy / g;
                    slopes[i].add(new int[]{sdx, sdy});
                }
            }
            slopes[i].sort((o1, o2) -> {
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            });
        }

        int count = 0;
        for (ArrayList<int[]> s : slopes) {
            for (int[] slope : s) {
                count += binarySearchCount(s, new int[]{ slope[0], -slope[1] });
                count += binarySearchCount(s, new int[]{ -slope[0], slope[1] });
            }
        }

        return count / 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new int[]{x, y});
        }
        System.out.println(check(points));
        scanner.close();
    }
}
