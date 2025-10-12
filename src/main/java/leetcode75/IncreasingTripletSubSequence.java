package leetcode75;

import java.util.TreeSet;

/**
 Given an integer array nums, return true if there exists a triple of indices (i, j, k)
 such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false

Source : LeetCode
Level : Medium

Time Complexity = O(pow(n , 2)) --> O(n * log(n))
Space Complexity = O(n)

 * */

public class IncreasingTripletSubSequence {

    public static void main(String ar[]) {
        IncreasingTripletSubSequence unit = new IncreasingTripletSubSequence();
        int nums[] = {2,1,5,0,4,6};

        System.out.print("Does the increasing triplet subsequence exist for the provided input " +
                unit.increasingTripletApproach(nums));
    }

    public boolean increasingTripletApproach(int[] nums) {

        if (nums.length <= 2) {
            return false;
        } else {
            return processToFindIncreasingTripletExistence(nums);
        }
    }

    private boolean processToFindIncreasingTripletExistence(int[] nums) {
        /*boolean interimStorage[] = phaseOne(nums);
        return phaseTwo(interimStorage, nums);*/

        boolean interimStorage[] = phaseOneApproachTwo(nums);
        return phaseTwoApproachTwo(interimStorage, nums);
    }

    private boolean phaseTwo(boolean[] interimStorage, int[] nums) {
        boolean result = false;
            for (int j = nums.length - 3; j >= 0; j--) {
                for (int i = j + 1; i < nums.length; i++) {
                    if (interimStorage[i] && nums[j] < nums[i]) {
                        result = true;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        return result;
    }

    private boolean phaseTwoApproachTwo(boolean[] interimStorage, int[] nums) {
        boolean result = false;
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < interimStorage.length; i++) {
            if (interimStorage[i]) {
                treeSet.add(i);
            }
        }
        for (int j = nums.length - 3; j >= 0; j--) {
            for (int i = j + 1; i < nums.length; i++) {
                Integer num = treeSet.higher(j);
                if (num != null && interimStorage[num] && nums[j] < nums[num]) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean[] phaseOne(int[] nums) {
        boolean interimStorage[] = new boolean[nums.length];
        interimStorage[nums.length - 1] = false;

        for (int j = nums.length - 2; j >= 0; j--) {
            for (int i = j + 1; i < nums.length; i++) {
                if (interimStorage[i] && nums[j] < nums[i]) {
                    interimStorage[j] = true;
                    break;
                } else if (nums[j] < nums[i]) {
                    interimStorage[j] = true;
                    break;
                }
            }
        }
        return interimStorage;
    }

    private boolean[] phaseOneApproachTwo(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        boolean interimStorage[] = new boolean[nums.length];
        interimStorage[nums.length - 1] = false;
        treeSet.add(nums[nums.length - 1]);

        for (int j = nums.length - 2; j >= 0; j--) {
            for (int i = j + 1; i < nums.length; i++) {
                Integer num = treeSet.higher(nums[j]);

                if (interimStorage[i] && num != null) {
                    interimStorage[j] = true;
                    break;
                } else if (num != null) {
                    interimStorage[j] = true;
                    break;
                }
            }
            treeSet.add(nums[j]);
        }
        return interimStorage;
    }
}
