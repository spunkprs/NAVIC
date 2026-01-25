package leetcode75.morganstanley;

import java.util.HashMap;
import java.util.Map;

/**

Problem : 325
Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is not one, return 0 instead


Constraints:-

a.) 1 <= nums.length <= 2 * 10^5
b.) -10^4 <= nums[i] <= 10^4
c.) -10^9 <= k <= 10^9


 * */

public class MaximumSizeSubArraySumEqualsK {

    public static void main(String ar[]) {
        MaximumSizeSubArraySumEqualsK unit = new MaximumSizeSubArraySumEqualsK();
        int nums[] = {1,-1,5,-2,3};
        int k = 3;

        System.out.println("Maximum length of sub array having sum = " + k + " is " + unit.maxSubArrayLen(nums, k));
    }

    private int[] preparePrefixSumArray(int nums[]) {
        int result[] = new int[nums.length + 1];
        result[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            result[i + 1] = result[i] + nums[i];
        }
        return result;
    }


    public int maxSubArrayLen(int[] nums, int k) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap();
        int prefixArraySum[] = preparePrefixSumArray(nums);

        for (int i = 0; i < prefixArraySum.length; i++) {
            if (!map.containsKey(prefixArraySum[i])) {
                map.put(prefixArraySum[i], i);
            }
        }


        for (int i = 1; i < prefixArraySum.length; i++) {
            int sum = prefixArraySum[i] - k;
            if (map.containsKey(sum)) {
                int distance = i - map.get(sum);
                maxLength = distance >= maxLength ? distance : maxLength;
            }
        }

        return maxLength;
    }


}
