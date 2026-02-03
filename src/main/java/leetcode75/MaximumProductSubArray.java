package leetcode75;

/**
Problem : 152
Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Note that the product of an array with a single element is the value of that element

Constraints:-

a.) 1 <= nums.length <= 2 * 10^4
b.) -10 <= nums[i] <= 10
c.) The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 * */

public class MaximumProductSubArray {

    public static void main(String ar[]) {
        MaximumProductSubArray unit = new MaximumProductSubArray();
        //int arr[] = {2, 3, -2, 4};
        //int arr[] = {-2, 0, -1};
        int arr[] = {2,-5,-2,-4,3};
        System.out.print("Maximum product fetched is " + unit.maxProduct(arr));
    }

    public int maxProduct(int[] nums) {

        int result = Integer.MIN_VALUE;

        if (nums.length == 1) {
            return nums[0];
        }

        Pair productArr[] = new Pair[nums.length];
        Pair startPair = new Pair(nums[0], nums[0]);
        productArr[0] = startPair;

        result = updateResult(result, nums[0]);

        return processToComputeResult(productArr, nums, result);
    }

    private int processToComputeResult(Pair[] productArr, int[] nums, int result) {
        for (int i = 1; i < nums.length; i++) {

            int min = fetchMin(nums[i], nums[i] * productArr[i-1].min, nums[i] * productArr[i-1].max);
            int max = fetchMax(nums[i], nums[i] * productArr[i-1].min, nums[i] * productArr[i-1].max);

            Pair pairNum = new Pair(min, max);
            productArr[i] = pairNum;
            result = updateResult(result, max);
        }
        return result;
    }

    private int fetchMin(int numOne, int numTwo, int numThree) {
        if (numOne <= numTwo) {
            if (numOne <= numThree) {
                return numOne;
            } else {
                return numThree;
            }
        } else {
            return numTwo <= numThree ? numTwo : numThree;
        }
    }

    private int fetchMax(int numOne, int numTwo, int numThree) {
        if (numOne >= numTwo) {
            if (numOne >= numThree) {
                return numOne;
            } else {
                return numThree;
            }
        } else {
            return numTwo >= numThree ? numTwo : numThree;
        }
    }

    private int updateResult(int result, int num) {
        return num > result ? num : result;
    }

    static class Pair {
        private int min;
        private int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
