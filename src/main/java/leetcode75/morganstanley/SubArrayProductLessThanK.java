package leetcode75.morganstanley;

/**
Problem : 713
Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k

Constraints:-

a.) 1 <= nums.length <= 3 * 104
b.) 1 <= nums[i] <= 1000
c.) 0 <= k <= 106

Time Complexity = O(N), where N being number of elements in the array
Space Complexity = O(1)
 * */

public class SubArrayProductLessThanK {

    public static void main(String ar[]) {
        SubArrayProductLessThanK unit = new SubArrayProductLessThanK();
        //int nums[] = {10, 5, 2, 6};
        //int k = 100;

        int nums[] = {1, 2, 3, 4, 5};
        int k = 1;

        System.out.println("Number of substrings whose product is lesser than k = " + k + " is " + unit.numSubarrayProductLessThanK(nums, k));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0;
        int j = 0;

        int productTillNow = 1;
        int count = 0;
        boolean flag = false;

        if ( k == 0) {
            return 0;
        }

        while (j < nums.length && i < nums.length && productTillNow != 0) {
            if (!flag) {
                productTillNow *= nums[j];
            }

            if (productTillNow < k) {
                count += j - i + 1;
                j++;
                flag = false;
            } else {
                while (productTillNow >= k && i < nums.length) {
                    productTillNow /= nums[i];
                    i++;
                    flag = true;
                }
            }
        }
        return count;
    }

}
