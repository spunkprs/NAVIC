package arrays;

/*
Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
Return the sum of the three integers.
*
* */

import java.util.Arrays;

public class ThreeSumClosest {

    /*
    Time complexity against Brute force approach as expected is O(pow(n, 3)) but the efficient solution would be to reduce it to O(pow(n, 2))
    * */
    public static void main(String ar[]) {
        ThreeSumClosest unit = new ThreeSumClosest();

        int nums[] = {-1,2,1,-4};
        int target = 1;
        System.out.println("Sum that is closest to the target " + target + " is " + unit.threeSumClosest(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        //Sort the array first in ascending order
        Arrays.sort(nums);

        //Execute processing
        return processToFindThreeSumClosest(nums, target);
    }

    private int processToFindThreeSumClosest(int[] nums, int target) {
        int minDifference = Integer.MAX_VALUE;
        boolean haltProcessing = false;
        int sumOfTripletResult = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            if (!haltProcessing) {
                while (j != k) {
                    int sumOfTriplet = nums[i] + nums[j] + nums[k];
                    int difference = Math.abs(target - sumOfTriplet);

                    if (minDifference == Integer.MAX_VALUE) {
                        minDifference = difference;
                        sumOfTripletResult = sumOfTriplet;
                    }

                    if (difference < minDifference) {
                        minDifference = difference;
                        sumOfTripletResult = sumOfTriplet;
                    }

                    if (sumOfTriplet < target) {
                        j++;
                    } else if (sumOfTriplet > target) {
                        k--;
                    } else {
                        haltProcessing = true;
                        break;
                    }
                }
            }
            if (haltProcessing) {
                break;
            }
        }
        return sumOfTripletResult;
    }

}
