package microsoft.arrays;

import java.util.*;

/**
Problem : 40
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations

Constraints:-

a.) 1 <= candidates.length <= 100
b.) 1 <= candidates[i] <= 50
c.) 1 <= target <= 30

--> Important thing to notice here is how I sort the array first just so that duplicate combinations can be avoided later <------
 * */

public class CombinationSum2 {

    public static void main(String ar[]) {
        CombinationSum2 unit = new CombinationSum2();
        //int candidates[] = {10,1,2,7,6,1,5};
        //int target = 8;

        //int candidates[] = {2,5,2,1,2};
        //int target = 5;

        int candidates[] = {1,1,2,3};
        int target = 3;

        List<List<Integer>> finalList = unit.combinationSum2(candidates, target);
        for (int i = 0; i < finalList.size(); i++) {
            List<Integer> internalList = finalList.get(i);
            for (Integer num : internalList) {
                System.out.print(num);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private List<List<Integer>> resultantList = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        processToPrepareCombinationSum(candidates, target);
        return resultantList;
    }

    private void processToPrepareCombinationSum(int candidates[], int target) {
        Set<Pair> pairSet = new HashSet<>();
        List<Integer> intermittentResult = new ArrayList<>();

        for (int i = 0; i < candidates.length; i++) {
            String pathTillNow = String.valueOf(candidates[i]);
            Pair pair = new Pair(candidates[i], 0, pathTillNow);
            if (!pairSet.contains(pair) && pair.sum < target) {
                pairSet.add(pair);
                intermittentResult.add(candidates[i]);
                process(i, candidates[i], pairSet, pair.depth, candidates, target, intermittentResult, pathTillNow);
                intermittentResult.remove(intermittentResult.size() - 1);
            } else if (!pairSet.contains(pair) && pair.sum == target) {
                pairSet.add(pair);
                intermittentResult.add(candidates[i]);
                resultantList.add(new ArrayList<>(intermittentResult));
                break;
            } else if (!pairSet.contains(pair) && pair.sum > target) {
                break;
            }
        }
    }

    private void process(int parentIndex, int sumAchievedTillNow, Set<Pair> pairSet, int parentDepth, int candidates[], int target,
                         List<Integer> intermittentResult, String pathTillNow) {
        for (int i = parentIndex + 1; i < candidates.length; i++) {
            String intermittentPath = String.valueOf(candidates[i]);
            Pair pair = new Pair(candidates[i] + sumAchievedTillNow, parentDepth + 1, pathTillNow + intermittentPath);

            if (!pairSet.contains(pair) && pair.sum < target) {
                pairSet.add(pair);
                intermittentResult.add(candidates[i]);
                process(i, pair.sum, pairSet, pair.depth, candidates, target, intermittentResult, pair.path);
                intermittentResult.remove(intermittentResult.size() - 1);
            } else if (!pairSet.contains(pair) && pair.sum == target) {
                pairSet.add(pair);
                intermittentResult.add(candidates[i]);
                resultantList.add(new ArrayList<>(intermittentResult));
                intermittentResult.remove(intermittentResult.size() - 1);
                break;
            } else if (!pairSet.contains(pair) && pair.sum > target) {
                break;
            }
        }
    }

    static class Pair {
        private int sum;
        private int depth;
        private String path;

        public Pair(int sum, int depth, String path) {
            this.sum = sum;
            this.depth = depth;
            this.path = path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            String path = pair.path;
            return sum == pair.sum && depth == pair.depth && this.path.equals(path);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum, depth, path);
        }
    }
}
