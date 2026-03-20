package agoda;

import java.util.*;

/**
Problem : 46
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order

Constraints:-

a.) 1 <= nums.length <= 6
b.) -10 <= nums[i] <= 10
c.) All the integers of nums are unique.

Time Complexity = O(n * n!), where n being number of nodes in the array
Space Complexity = O(n), where n being number of nodes in the array
 * */

public class Permutations {

    public static void main(String ar[]) {
        Permutations unit = new Permutations();
        int nums[] = {1, 2, 3};
        List<List<Integer>> result = unit.permute(nums);

        result.stream().forEach(x -> {
            x.stream().forEach(y ->
            System.out.print(y));
            System.out.println();
    });
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 1) {
            List<Integer> intermittentResult = new ArrayList<>();
            intermittentResult.add(nums[0]);
            result.add(intermittentResult);
            return result;
        }
        Set<Integer> set = new LinkedHashSet<>();
        processToPreparePermutations(nums, set, result);
        return result;
    }

    private void processToPreparePermutations(int[] nums, Set<Integer> set, List<List<Integer>> result) {
        for (int i = 0; i < nums.length; i++) {
            set.add(i);
            process(nums, set, 1, result);
            set.remove(i);
        }
    }

    private void process(int[] nums, Set<Integer> set, int depth, List<List<Integer>> result) {
        if (depth < nums.length) {
            for (Integer index : fetchPossibleIndexes(set, nums)) {
                set.add(index);
                process(nums, set, depth + 1, result);
                set.remove(index);
            }
        } else if (depth == nums.length) {
            List<Integer> intermittentResult = new ArrayList<>();
            set.stream().forEach(x -> intermittentResult.add(nums[x]));
            result.add(intermittentResult);
        }
    }

    private List<Integer> fetchPossibleIndexes(Set<Integer> set, int[] nums) {
        List<Integer> possibleIndexes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(i)) {
                possibleIndexes.add(i);
            }
        }
        return possibleIndexes;
    }
}
