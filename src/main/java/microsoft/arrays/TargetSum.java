package microsoft.arrays;

import java.util.*;

/**
Problem : 494
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target

Constraints:-

a.) 1 <= nums.length <= 20
b.) 0 <= nums[i] <= 1000
c.) 0 <= sum(nums[i]) <= 1000
d.) -1000 <= target <= 1000

Without memoization time consumed would be O(K^N), where K being number of choices at each step && N being the depth of the tree
but with memoization in place T.C = (N * T), where T being target sum, space complexity would also be the same

Infact in these kind of cases time complexity can be derived by having a look at space complexity (Building Mental model to find T.C using S.C is very important here)
 * */

public class TargetSum {

    public static void main(String ar[]) {
        TargetSum unit = new TargetSum();
        int nums[] = {1,1,1,1,1};
        int target = 3;

        System.out.println("Number of ways target " + target + " can be achieved is " + unit.findTargetSumWays(nums, target));
    }

    public int findTargetSumWays(int[] nums, int target) {
        Map<Pair, Integer> map = new HashMap<>();
        return processToFindNumberOfWaysToReachTarget(nums, target, 0, -1, map);
    }

    private int processToFindNumberOfWaysToReachTarget(int[] nums, int target, int sum, int depth, Map<Pair, Integer> map) {
        Pair parentPair = new Pair(sum, depth);
        int childIndex = depth + 1;
        int intermittentResult = 0;

            List<Pair> pairs = new ArrayList<>();

            pairs.add(new Pair(sum + nums[childIndex] * -1, childIndex));
            pairs.add(new Pair(sum + nums[childIndex], childIndex));

            if (childIndex == nums.length - 1) {
                for (Pair p : pairs) {
                    if (p.sum == target) {
                        map.put(p, 1);
                        intermittentResult += map.get(p);
                    } else {
                        map.put(p, 0);
                        intermittentResult += map.get(p);
                    }
                }
            } else {
                for (Pair p : pairs) {
                    if (!map.containsKey(p)) {
                        intermittentResult += processToFindNumberOfWaysToReachTarget(nums, target, p.sum, p.depth, map);
                    } else {
                        intermittentResult += map.get(p);
                    }
                }
            }
        map.put(parentPair, intermittentResult);
        return map.get(parentPair);
    }

    static class Pair {
        private int sum;
        private int depth;

        public Pair(int sum, int depth) {
            this.sum = sum;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return sum == pair.sum && depth == pair.depth;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum, depth);
        }
    }
}
