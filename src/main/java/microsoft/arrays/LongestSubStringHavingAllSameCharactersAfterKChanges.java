package microsoft.arrays;

import java.util.Arrays;

/**
 Given a string s of length n consisting only of uppercase English letters ('A'–'Z') and an integer k (0 < k < n),
 you can change at most k characters in the string. find the length of the longest substring that can be made of the
 same character after performing at most k changes.

 Input : k = 2, s = ABABA
 Output : 5
 We can get maximum length by replacing 2 B's with A's

 Input : k = 4, s = HHHHHH
 Output : 6
 We get maximum length 6 without any replacement

 Source : GFG

 Link : https://www.geeksforgeeks.org/dsa/maximum-length-substring-having-all-same-characters-after-k-changes/

Space Complexity = O(N), where N being number of elements in the array
Time Complexity = O(26 * N) ~ O(N)

 * */

public class LongestSubStringHavingAllSameCharactersAfterKChanges {

    public static void main(String ar[]) {
        LongestSubStringHavingAllSameCharactersAfterKChanges unit = new LongestSubStringHavingAllSameCharactersAfterKChanges();
        //String str = "ABABA";
        //int k = 2;

        String str = "HHHHHH";
        int k = 4;

        System.out.println("Length of longest substring having all same characters after k changes is " + unit.longestSubString(str, k));

    }

    public int longestSubString(String str, int k) {
        if (str.isEmpty()) {
            return 0;
        } else {
           return processToComputeLongestSubString(str, k);
        }
    }

    private int processToComputeLongestSubString(String str, int k) {
        char arr[] = str.toCharArray();
        int frequency[] = new int[26];

        int highestFrequencySoFar = 0;
        int result = 0;

        int leftPointer = 0;
        int rightPointer = 0;

        while (rightPointer < str.length()) {
            if (rightPointer == 0) {
                highestFrequencySoFar = 1;
                int index = arr[rightPointer] - 65;
                frequency[index]++;
                result = 1;
            } else {
                int length = rightPointer - leftPointer + 1;
                int index = arr[rightPointer] - 65;
                frequency[index]++;
                highestFrequencySoFar = updateHighestFrequency(highestFrequencySoFar, frequency[index]);
                if (length - highestFrequencySoFar > k) {
                    while (length - highestFrequencySoFar > k) {
                        frequency[arr[leftPointer] - 65]--;
                        leftPointer++;
                        length = rightPointer - leftPointer + 1;
                        highestFrequencySoFar = updateHighestFrequency(highestFrequencySoFar, findHighestFrequency(frequency));
                        result = Math.max(length, result);
                    }
                } else {
                    result = Math.max(length, result);
                }
            }
            rightPointer++;
        }
        return result;
    }

    private int findHighestFrequency(int[] frequency) {
        return Arrays.stream(frequency).max().getAsInt();
    }

    private int updateHighestFrequency(int highestFrequencySoFar, int num) {
        return num > highestFrequencySoFar ? num : highestFrequencySoFar;
    }
}
