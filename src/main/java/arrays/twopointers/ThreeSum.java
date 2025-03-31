package arrays.twopointers;

import java.util.*;

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.
* */

public class ThreeSum {

    /*
     * Solved the question using two pointer approach
     * Time complexity against Brute force approach as expected is O(pow(n, 3)) but the efficient solution would be to reduce it to O(pow(n, 2))
     * && tc for the same is O(pow(n, 2)) only
     * */

    public static void main(String ar[]) {
        ThreeSum threeSum = new ThreeSum();
        int nums[] = {-3,-2,-1,0,1,2,3};
        threeSum.threeSum(nums);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        //Sorting the provided array first in ascending order
        Arrays.sort(nums);
        List<Triplet> triplets = new ArrayList<>();
        prepareUniqueTripletsHavingSumEqualToZero(nums, triplets);
        return prepareFinalResult(triplets);
    }

    private List<List<Integer>> prepareFinalResult(List<Triplet> triplets) {
        List<List<Integer>> result = new ArrayList<>();
        for (Triplet triplet : triplets) {
            List<Integer> listOfTriplets = new ArrayList<>();
            listOfTriplets.add(triplet.numOne);
            listOfTriplets.add(triplet.numTwo);
            listOfTriplets.add(triplet.numThree);
            result.add(listOfTriplets);
        }
        return result;
    }

    private void prepareUniqueTripletsHavingSumEqualToZero(int nums[], List<Triplet> triplets) {
        for (int i = 0; i < nums.length - 2; i++) {
            //No point in iterating over the array when the first number in the triplet is > 0
            if (nums[i] > 0) {
                break;
            }

            //To avoid unnecessary duplicate addition against first number in the triplet
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int numberToBeSearched = -1 * nums[i];
            int leftIndex = i + 1;
            int rightIndex = nums.length - 1;

                while (leftIndex < rightIndex) {
                    if (nums[leftIndex] + nums[rightIndex] == numberToBeSearched) {
                        Triplet triplet = new Triplet(nums[i], nums[leftIndex], nums[rightIndex]);
                        triplets.add(triplet);
                        leftIndex++;
                        rightIndex--;

                        //To avoid unnecessary duplicate addition against second number in the triplet
                        while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex - 1]) {
                            leftIndex++;
                        }

                        //To avoid unnecessary duplicate addition against third number in the triplet
                        while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex + 1]) {
                            rightIndex--;
                        }
                    } else if (nums[leftIndex] + nums[rightIndex] < numberToBeSearched) {
                        leftIndex++;
                    } else {
                        rightIndex--;
                    }
                }
        }
    }

    static class Triplet {
        int numOne;
        int numTwo;
        int numThree;

        public Triplet(int numOne, int numTwo, int numThree) {
            this.numOne = numOne;
            this.numTwo = numTwo;
            this.numThree = numThree;
        }
    }

}
