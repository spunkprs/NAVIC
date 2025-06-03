package arrays.slidingwindow;

/**
*
*
You are given a 0-indexed string s consisting of only lowercase English letters, and an integer count.
A substring of s is said to be an equal count substring if, for each unique letter in the substring,
it appears exactly count times in the substring.

Return the number of equal count substrings in s.

A substring is a contiguous non-empty sequence of characters within a string.
*
Example 1:

Input: s = "aaabcbbcc", count = 3
Output: 3
Explanation:
The substring that starts at index 0 and ends at index 2 is "aaa".
The letter 'a' in the substring appears exactly 3 times.
The substring that starts at index 3 and ends at index 8 is "bcbbcc".
The letters 'b' and 'c' in the substring appear exactly 3 times.
The substring that starts at index 0 and ends at index 8 is "aaabcbbcc".
The letters 'a', 'b', and 'c' in the substring appear exactly 3 times.
Example 2:

Input: s = "abcd", count = 2
Output: 0
Explanation:
The number of times each letter appears in s is less than count.
Therefore, no substrings in s are equal count substrings, so return 0.
Example 3:

Input: s = "a", count = 5
Output: 0
Explanation:
The number of times each letter appears in s is less than count.
Therefore, no substrings in s are equal count substrings, so return 0

Constraints:

1 <= s.length <= 3 * pow(10,4)
1 <= count <= 3 * pow(10,4)
s consists only of lowercase English letters.
*
*
* Time Complexity = O(n * 26) ~ O(n)
* Space Complexity = O(26) ~ constant space
*
* */


import java.util.HashSet;
import java.util.Set;

public class NumberOfEqualCountSubStrings {

    public static void main(String ar[]) {
        NumberOfEqualCountSubStrings unit = new NumberOfEqualCountSubStrings();
        //String inputString = "aaabcbbcc";
        String inputString = "kiegykd";
        int count = 1;
        //int count = 3;
        System.out.print("Number of equal count substrings for i/p " + inputString + " where count is " + count + " is :: " +
                unit.equalCountSubstrings(inputString, count));
    }

    public int equalCountSubstrings(String s, int count) {
        int result = 0;
        if (count > s.length()) {
            return 0;
        } else {
            //Find number of distinct characters
            char arr[] = s.toCharArray();
            int distinctCharacters = findDistinctCharacters(arr);

            //Fetching count of valid substring against count of distinct characters from 1 to n{distinctCharacters}
            for (int i = 1; i <= distinctCharacters; i++) {
                result +=processToFindEqualCountSubstrings(arr, count, i);
            }
        }
        return result;
    }

    private int processToFindEqualCountSubstrings(char[] arr, int allowedCount, int numberOfDistinctCharacters) {
        int leftIndex = 0;
        int rightIndex = 0;
        Set<Character> set = new HashSet<>();
        int charFrequencyMap[] = new int[26];
        int count = 0;
        int countOfSubStrings = 0;

        while (rightIndex < arr.length) {
            if (!set.contains(arr[rightIndex])) {
                if (set.size() < numberOfDistinctCharacters) {
                    set.add(arr[rightIndex]);
                    charFrequencyMap[arr[rightIndex] - 'a'] = charFrequencyMap[arr[rightIndex] - 'a'] + 1;
                    count = postAdditionSteps(charFrequencyMap, rightIndex, arr, allowedCount, count);
                    if (count == numberOfDistinctCharacters) {
                        countOfSubStrings++;
                    }
                    rightIndex++;
                } else if (set.size() == numberOfDistinctCharacters) {
                    while (set.size() == numberOfDistinctCharacters) {
                        if (charFrequencyMap[arr[leftIndex] - 'a'] == 1) {
                            set.remove(arr[leftIndex]);
                        }
                        if (charFrequencyMap[arr[leftIndex] - 'a'] == allowedCount) {
                            count--;
                        }
                        charFrequencyMap[arr[leftIndex] - 'a'] = charFrequencyMap[arr[leftIndex] - 'a'] - 1;
                        leftIndex++;
                    }
                }
            } else {
                if (set.size() == numberOfDistinctCharacters) {
                    if (charFrequencyMap[arr[rightIndex] - 'a'] < allowedCount) {
                        charFrequencyMap[arr[rightIndex] - 'a'] = charFrequencyMap[arr[rightIndex] - 'a'] + 1;
                        count = postAdditionSteps(charFrequencyMap, rightIndex, arr, allowedCount, count);
                        if (count == numberOfDistinctCharacters) {
                            countOfSubStrings++;
                        }
                        rightIndex++;
                    } else if (charFrequencyMap[arr[rightIndex] - 'a'] == allowedCount) {
                        while (charFrequencyMap[arr[rightIndex] - 'a'] + 1 > allowedCount) {
                            count--;
                            if (charFrequencyMap[arr[leftIndex] - 'a'] == 1) {
                                set.remove(arr[leftIndex]);
                            }
                            charFrequencyMap[arr[leftIndex] - 'a'] = charFrequencyMap[arr[leftIndex] - 'a'] - 1;
                            leftIndex++;
                        }
                    }
                } else if (set.size() < numberOfDistinctCharacters) {
                    if (charFrequencyMap[arr[rightIndex] - 'a'] < allowedCount) {
                        charFrequencyMap[arr[rightIndex] - 'a'] = charFrequencyMap[arr[rightIndex] - 'a'] + 1;
                        count = postAdditionSteps(charFrequencyMap, rightIndex, arr, allowedCount, count);
                        rightIndex++;
                    } else if (charFrequencyMap[arr[rightIndex] - 'a'] == allowedCount) {
                        while (charFrequencyMap[arr[rightIndex] - 'a'] + 1 > allowedCount) {
                            count--;
                            if (charFrequencyMap[arr[leftIndex] - 'a'] == 1) {
                                set.remove(arr[leftIndex]);
                            }
                            charFrequencyMap[arr[leftIndex] - 'a'] = charFrequencyMap[arr[leftIndex] - 'a'] - 1;
                            leftIndex++;
                        }
                    }
                }
            }
        }
        return countOfSubStrings;
    }

    private int postAdditionSteps(int[] charFrequencyMap, int rightIndex, char[] arr, int allowedCount, int count) {
        if (charFrequencyMap[arr[rightIndex] - 'a'] == allowedCount) {
            return count + 1;
        }
        return count;
    }

    private int findDistinctCharacters(char arr[]) {
        Set<Character> set = new HashSet<>();
        for (Character c : arr) {
            set.add(c);
        }
        return set.size();
    }

}
