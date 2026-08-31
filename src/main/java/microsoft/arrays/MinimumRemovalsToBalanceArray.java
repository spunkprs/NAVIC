package microsoft.arrays;

import java.util.Arrays;

/**
Problem : 3634
Link : https://leetcode.com/problems/minimum-removals-to-balance-array/description/?envType=company&envId=salesforce&favoriteSlug=salesforce-six-months

You are given an integer array nums and an integer k.

An array is considered balanced if the value of its maximum element is at most k times the minimum element.

You may remove any number of elements from nums without making it empty.

Return the minimum number of elements to remove so that the remaining array is balanced.

Note: An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always holds true.


Constraints:-

a.) 1 <= nums.length <= 10^5
b.) 1 <= nums[i] <= 10^9
c.) 1 <= k <= 10^5

Time Complexity = O(n * log(n))
Space Complexity = O(1), but memory used for sorting would be O(N) in the worst case
 * */

public class MinimumRemovalsToBalanceArray {

    public static void main(String ar[]) {
        MinimumRemovalsToBalanceArray unit = new MinimumRemovalsToBalanceArray();
        int arr[] = {1, 6, 2, 9};
        int k = 3;
        System.out.print("Minimum removals to balance array is " + unit.minRemoval(arr, k));
    }

    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = n;
        int right = 0;

        for (int left = 0; left < n; left++) {
            while (right < n && nums[right] <= (long) nums[left] * k) {
                right++;
            }
            ans = Math.min(ans, n - (right - left));
        }
        return ans;
    }
}
