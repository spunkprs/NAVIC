package leetcode75;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
Problem : 276
You are painting a fence of n posts with k different colors. You must paint the posts following these rules:

Every post must be painted exactly one color.
There cannot be three or more consecutive posts with the same color.
Given the two integers n and k, return the number of ways you can paint the fence

Constraints:-

a.) 1 <= n <= 50
b.) 1 <= k <= 10^5

The testcases are generated such that the answer is in the range [0, 2^31 - 1] for the given n and k.

Time Complexity = O(K ^ N) [Exponential time, can be improved]
Space Complexity = O(N)
 * */

public class PaintFence {

    public static void main(String ar[]) {
        PaintFence unit = new PaintFence();

        int n = 3;
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

        /*for (int i = 1; i <= k; i++) {
            //sum += process(parentIndex + 1, i, count, map, n, k);
            //sum += processOne(parentIndex + 1, i, count, map, n, k);
            int value = processOne(parentIndex + 1, i, count, map, n, k);
            sum = value * k;
        }*/

        int value = processOne(parentIndex + 1, 1, count, map, n, k);
        sum = value * k;
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

    /**
    Need to fix this method to get optimised time complexity
     * */
    private int processOne(int childFenceIndex, int chosenColor, int count, Map<Pair, Integer> map, int totalFence, int maxColors) {
        if (childFenceIndex <= totalFence) {
            Pair parentNode = new Pair(childFenceIndex, count);

            if (!map.containsKey(parentNode)) {
                if (childFenceIndex == totalFence) {
                    parentNode.count = 1;
                    map.put(parentNode, 1);
                } else {
                    int sum = 0;
                    int index = 1;
                    while (index <= maxColors) {
                        if (count < 2) {
                            if (chosenColor == index) {
                                int value = processOne(childFenceIndex + 1, index, count + 1, map, totalFence, maxColors);
                                sum += value;
                                index++;
                            } else {
                                int value = processOne(childFenceIndex + 1, index, 1, map, totalFence, maxColors);
                                sum += value * (maxColors - 1);
                                index = maxColors + 1;
                            }
                        } else if (chosenColor == index && count == 2) {
                            index++;
                        } else if (chosenColor != index && count == 2) {
                            int value = processOne(childFenceIndex + 1, index, 1, map, totalFence, maxColors);
                            sum += value * (maxColors - 1);
                            index = maxColors + 1;
                        }
                    }
                    map.put(parentNode, sum);
                }
            }
            return map.get(parentNode);
        }
        return 0;
    }

    private int fetchNextColor(int chosenColor, int maxColors) {
        if (chosenColor == maxColors && chosenColor - 1 >= 1) {
            return chosenColor - 1;
        } else if (chosenColor == 1 && chosenColor + 1 <= maxColors) {
            return chosenColor + 1;
        } else {
            return chosenColor + 1;
        }
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
