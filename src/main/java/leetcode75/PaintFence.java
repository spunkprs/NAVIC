package leetcode75;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaintFence {

    public static void main(String ar[]) {
        PaintFence unit = new PaintFence();

        int n = 7;
        int k = 2;

        System.out.print("Number of ways to paint fences " + n + " with " + k + " different colors is :: " + unit.numWays(n, k));
    }

    public int numWays(int n, int k) {
        if (n == 1) {
            return k;
        } else if (n > 2 && k == 1) {
            return 0;
        }
        return processToComputeNumberOfWays(n, k);
    }

    private int processToComputeNumberOfWays(int n, int k) {
        Map<Pair, Integer> map = new HashMap<>();
        int parentIndex = 0;
        int count = 1;
        int sum = 0;

        for (int i = 1; i <= k; i++) {
            sum += process(parentIndex + 1, i, count, map, n, k);
        }
        return sum;
    }

    private int process(int childFenceIndex, int chosenColor, int count, Map<Pair, Integer> map, int totalFence, int maxColors) {
        if (childFenceIndex <= totalFence) {
            Pair parentNode = new Pair(childFenceIndex, count);

            if (!map.containsKey(parentNode)) {
                if (childFenceIndex == totalFence) {
                    map.put(parentNode, 1);
                } else {
                    int sum = 0;
                    for (int i = 1; i <= maxColors; i++) {
                        if (chosenColor == i && count < 2) {
                            sum += process(childFenceIndex + 1, i, count + 1, map, totalFence, maxColors);
                        } else if (chosenColor != i) {
                            sum += process(childFenceIndex + 1, i, 1, map, totalFence, maxColors);
                        }
                    }
                    map.put(parentNode, sum);
                }
            }
            return map.get(parentNode);
        }
        return 0;
    }

    class Pair {
        int index;
        int count;

        public Pair(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return index == pair.index && count == pair.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, count);
        }
    }
}
