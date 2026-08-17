package microsoft.arrays;

/**
Problem : 673
Level : Medium

Link : https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/?envType=company&envId=amazon&favoriteSlug=amazon-thirty-days

Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

Constraints:-

a.) 1 <= nums.length <= 2000
b.) -10^6 <= nums[i] <= 10^6
c.) The answer is guaranteed to fit inside a 32-bit integer.

Time Complexity = O(N^2)
Space Complexity = O(N)

Information : This question is still partially solved  !!
 * */

public class NumberOfLongestIncreasingSubsequence {

    private boolean flag = false;

    public static void main(String ar[]) {
        NumberOfLongestIncreasingSubsequence unit = new NumberOfLongestIncreasingSubsequence();
        int arr[] = {2, 4, 6, 5, 8, 2};
        //int arr[] = {1,2,4,3,5,4,7,2};

        //int arr[] = {2, 2, 2, 2, 2};
        System.out.print("Number of LIS is " + unit.findNumberOfLIS(arr));
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int helperLengthArr[] = new int[nums.length];
        int helperCountArr[] = new int[nums.length];
        int result = 0;

        processToFindNumberOfLISApproachOne(nums, helperLengthArr, helperCountArr);
        if (!flag) {
            for (int i = 0; i < helperCountArr.length; i++) {
                result += helperCountArr[i];
            }
        } else {
            int maxLength = 0;
            for (int i = 0; i < helperLengthArr.length; i++) {
                if (helperLengthArr[i] > maxLength) {
                    maxLength = helperLengthArr[i];
                }
            }

            for (int i = 0; i < helperLengthArr.length; i++) {
                if (helperLengthArr[i] == maxLength) {
                    result += helperCountArr[i];
                }
            }
        }
        return result;
    }

    private void processToFindNumberOfLISApproachOne(int[] nums, int helperLengthArr[], int helperCountArr[]) {

        for (int j = nums.length - 1; j >= 0; j--) {
            if (j == nums.length - 1) {
                helperLengthArr[j] = 1;
                helperCountArr[j] = 1;
            } else {
                for (int i = j + 1; i < nums.length; i++) {
                    if (nums[j] < nums[i]) {
                        flag = true;
                        int existingLength = helperLengthArr[j];
                        int existingCount = helperCountArr[j];
                        if (existingCount == 0) {
                            helperLengthArr[j] = helperLengthArr[i] + 1;
                            helperCountArr[j] = helperCountArr[i];
                        } else {
                            if (existingLength < helperLengthArr[i] + 1) {
                                helperLengthArr[j] = helperLengthArr[i] + 1;
                                helperCountArr[j] = helperCountArr[i];
                            } else if (existingLength == helperLengthArr[i] + 1) {
                                helperCountArr[j] = 1 + helperCountArr[j];
                            }
                        }
                    } else {
                        if (helperLengthArr[j] == 0) {
                            helperLengthArr[j] = 1;
                            helperCountArr[j] = 1;
                        }
                    }
                }
            }
        }
    }
}
