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
     * Time complexity against Brute force approach as expected is O(pow(n, 3)) but the efficient solution would be to reduce it to O(pow(n, 2)) && tc for the same is O(pow(n, 2)) only
     * */

    public static void main(String ar[]) {
        ThreeSum threeSum = new ThreeSum();
        int nums[] = {-3,-2,-1,0,1,2,3};
        threeSum.threeSum(nums);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        //Sorting the provided array first in ascending order
        Arrays.sort(nums);
        Set<Triplet> tripletSet = new HashSet<>();
        prepareUniqueTripletsHavingSumEqualToZero(nums, tripletSet);
        return prepareFinalResult(tripletSet);
    }

    private List<List<Integer>> prepareFinalResult(Set<Triplet> tripletSet) {
        List<List<Integer>> result = new ArrayList<>();
        for (Triplet triplet : tripletSet) {
            List<Integer> listOfTriplets = new ArrayList<>();
            listOfTriplets.add(triplet.numOne);
            listOfTriplets.add(triplet.numTwo);
            listOfTriplets.add(triplet.numThree);
            result.add(listOfTriplets);
        }
        return result;
    }

    private void prepareUniqueTripletsHavingSumEqualToZero(int nums[], Set<Triplet> tripletSet) {
        for (int i = 0; i < nums.length - 2; i++) {
            int numberToBeSearched = -1 * nums[i];
            int leftIndex = i + 1;
            int rightIndex = nums.length - 1;
            while (leftIndex < rightIndex) {
                if (nums[leftIndex] + nums[rightIndex] == numberToBeSearched) {
                    Triplet triplet = new Triplet(nums[i], nums[leftIndex], nums[rightIndex]);
                    if (!tripletSet.contains(triplet)) {
                        tripletSet.add(triplet);
                    }
                    leftIndex++;
                    rightIndex--;
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
        int factor;
        int countOfNonZeroDigits = 3;
        int modSum;

        public Triplet(int numOne, int numTwo, int numThree) {
            this.numOne = numOne;
            this.numTwo = numTwo;
            this.numThree = numThree;
            this.factor = ((this.numOne == 0 ? 1 : this.numOne) * (this.numTwo == 0 ? 1 :
                    this.numTwo) * (this.numThree == 0 ? 1 : this.numThree));
            this.modSum = Math.abs(this.numOne) + Math.abs(this.numTwo) + Math.abs(this.numThree);

            if (this.numOne == 0 || this.numTwo == 0 || this.numThree == 0) {
                countOfNonZeroDigits--;
            }
        }

        @Override
        public boolean equals(Object o) {
            Triplet triplet = (Triplet) o;
            return (this.factor == triplet.factor) && (this.countOfNonZeroDigits == triplet.countOfNonZeroDigits) && (this.modSum == triplet.modSum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.factor);
        }
    }

}
