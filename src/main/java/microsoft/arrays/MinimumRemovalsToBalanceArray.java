package microsoft.arrays;

import java.util.Arrays;

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
