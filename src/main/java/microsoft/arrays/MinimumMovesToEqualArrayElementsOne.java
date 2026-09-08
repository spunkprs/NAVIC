package microsoft.arrays;

import java.util.Arrays;

/**
Problem : 453
Link : https://leetcode.com/problems/minimum-moves-to-equal-array-elements/description/
Level : Medium

Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment n - 1 elements of the array by 1.

Constraints:-

a.) n == nums.length
b.) 1 <= nums.length <= 10^5
c.) -10^9 <= nums[i] <= 10^9
d.) The answer is guaranteed to fit in a 32-bit integer.
 * */

public class MinimumMovesToEqualArrayElementsOne {

    public static void main(String ar[]) {
        MinimumMovesToEqualArrayElementsOne unit = new MinimumMovesToEqualArrayElementsOne();
        int elements[] = {-8, 16, 12, 10};
        System.out.print("Minimum moves to equal array elements is " + unit.minMoves(elements));
    }

    public int minMoves(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            result += nums[i] - nums[0];
        }
        return result;
    }
}
