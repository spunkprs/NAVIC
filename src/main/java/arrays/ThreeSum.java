package arrays;

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.
* */


import java.util.*;

public class ThreeSum {
    /*
    * Dividing the problem into three cases :-
    * Case 1: When one of the number is zero && remaining two are complement of each other
    * Case 2 : When one number is > 0 && remaining two are less than zero
    * Case 3 : When one number is < 0 && remaining two are > than zero
    * */

    public static void main(String ar[]) {
        int nums[] = {-1,0,1,2,-1,-4, 0, 0, 8, 0, -8};

        ThreeSum obj = new ThreeSum();

        List<List<Integer>> result = obj.threeSum(nums);
        System.out.println("Three nums whose sum is equal to zero ::" + result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<Triplet> resultSet = new HashSet<>();
        Map<Integer, Integer> map = prepareMap(nums);

        //Case 1 :
        for (Integer num : nums) {
            if (num != 0) {
                int complementNum = num * -1;
                if (map.containsKey(complementNum) && map.containsKey(0)) {
                    Triplet triplet = new Triplet(num, complementNum, 0);
                    resultSet.add(triplet);
                }
            }
        }

        //Case 2 && 3 :
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] != 0 && nums[j] != 0) {
                    if (nums[i] > 0) {
                        if (nums[j] < 0 && Math.abs(nums[j]) < nums[i]) {
                            int pendingNum = (nums[i] + nums[j]) * -1;
                            collectTriplets(map, nums[i], nums[j], pendingNum, resultSet);
                        }
                    } else {
                        if (nums[j] > 0 && Math.abs(nums[i]) > nums[j]) {
                            int pendingNum = (nums[i] + nums[j]) * -1;
                            collectTriplets(map, nums[i], nums[j], pendingNum, resultSet);
                        }
                    }
                }
            }
        }
        return prepareResult(resultSet, map);
    }

    private List<List<Integer>> prepareResult(Set<Triplet> resultSet, Map<Integer, Integer> map) {
        List<List<Integer>> result = new ArrayList<>();
        if (map.containsKey(0) && map.get(0) >= 3 ) {
            List<Integer> resultList = new ArrayList<>();
            resultList.add(0);
            resultList.add(0);
            resultList.add(0);
            result.add(resultList);
        }
        resultSet.forEach(triplet -> {
            List<Integer> resultList = new ArrayList<>();
            resultList.add(triplet.numOne);
            resultList.add(triplet.numTwo);
            resultList.add(triplet.numThree);
            result.add(resultList);
        });
        return result;
    }

    private void collectTriplets(Map<Integer, Integer> map, int numOne, int numTwo,
                                 int pendingNum, Set<Triplet> resultSet) {
        if (map.containsKey(pendingNum)) {
            if (pendingNum == numTwo && map.get(pendingNum) > 1) {
                resultSet.add(new Triplet(numOne, numTwo, numTwo));
            } else if (pendingNum != numTwo) {
                resultSet.add(new Triplet(numOne, numTwo, pendingNum));
            }
        }
    }

    private Map<Integer, Integer> prepareMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        return map;
    }

    static class Triplet {
        int numOne;
        int numTwo;
        int numThree;
        int factor;

        public Triplet(int numOne, int numTwo, int numThree) {
            this.numOne = numOne;
            this.numTwo = numTwo;
            this.numThree = numThree;
            this.factor = ((this.numOne == 0 ? 1 : this.numOne) * (this.numTwo == 0 ? 1 :
                    this.numTwo) * (this.numThree == 0 ? 1 : this.numThree));
        }

        @Override
        public boolean equals(Object o) {
            Triplet triplet = (Triplet) o;
            return this.factor == triplet.factor;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.factor);
        }
    }
}
