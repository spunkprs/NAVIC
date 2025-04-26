package arrays.slidingwindow;


/*
*
* Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character
* in this substring is greater than or equal to k.

If no such substring exists, return 0.

Example 1:

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times
*
* */

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithAtLeastKRepeatingCharacters {

    public static void main(String ar[]) {
        LongestSubstringWithAtLeastKRepeatingCharacters unit = new LongestSubstringWithAtLeastKRepeatingCharacters();
        //String input = "pqrrqtpabbbaa";
        String input = "zzzzzzzzzzaaaaaaaaabbbbbbbbhbhbhbhbhbhbhicbcbcibcbccccccccccbbbbbbbbaaaaaaaaafffaahhhhhiaahiiiiiiiiifeeeeeeeeee";
        int k = 10;
        //int k = 3;
        System.out.print("Length of longest repeating substring for i/p " + input + " is : " + unit.longestSubstring(input, k));
    }

    public int longestSubstring(String s, int k) {
        char arr[] = s.toCharArray();
        if (s.length() == 1 && k == 1) {
            return 1;
        } else if (s.length() < k) {
            return 0;
        } else {
            int numberOfUniqueCharactersInTheString = fetchNumberOfUniqueCharacters(arr);
            int lengthOfLongestSubString = 0;
            int charMap[];
            for (int i = 1; i <= numberOfUniqueCharactersInTheString; i++) {
                charMap = new int[26];
                lengthOfLongestSubString = Math.max(lengthOfLongestSubString, processToFindLongestSubString(arr, charMap, i, k));
            }
            return lengthOfLongestSubString;
        }
    }

    private int processToFindLongestSubString(char[] arr, int[] charFrequencyMap, int allowedDistinctCharacters, int allowedFrequency) {
        int leftIndex = 0;
        int rightIndex = 0;
        Set<Character> set = new HashSet<>();
        int lengthOfLongestSubString = 0;
        int count = 0;
        while (rightIndex < arr.length) {
            if (set.size() < allowedDistinctCharacters) {
                set.add(arr[rightIndex]);
                charFrequencyMap[arr[rightIndex] - 'a'] = charFrequencyMap[arr[rightIndex] - 'a'] + 1;
                if (charFrequencyMap[arr[rightIndex] - 'a'] == allowedFrequency) {
                    count++;
                }
                if (set.size() == allowedDistinctCharacters && count == allowedDistinctCharacters) {
                    lengthOfLongestSubString = Math.max(lengthOfLongestSubString, rightIndex - leftIndex + 1);
                }
                rightIndex++;
            } else if (set.size() == allowedDistinctCharacters) {
                if (set.contains(arr[rightIndex])) {
                    charFrequencyMap[arr[rightIndex] - 'a'] = charFrequencyMap[arr[rightIndex] - 'a'] + 1;
                    if (charFrequencyMap[arr[rightIndex] - 'a'] == allowedFrequency) {
                        count++;
                    }
                    if (count == allowedDistinctCharacters) {
                        lengthOfLongestSubString = Math.max(lengthOfLongestSubString, rightIndex - leftIndex + 1);
                    }
                    rightIndex++;
                } else {
                    set = new HashSet<>();
                    leftIndex = rightIndex;
                    charFrequencyMap = new int[26];
                    count = 0;
                }
            }
        }
        return lengthOfLongestSubString;
    }

    private int fetchNumberOfUniqueCharacters(char[] arr) {
        Set<Character> ipSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            ipSet.add(arr[i]);
        }
        return ipSet.size();
    }
}
