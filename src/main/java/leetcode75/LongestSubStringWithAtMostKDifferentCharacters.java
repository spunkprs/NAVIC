package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
Problem : 340
Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters

Constraints:-

a.) 1 <= s.length <= 5 * pow(10,4)
b.) 0 <= k <= 50

Level : Medium
Time Complexity = O(n)
Space Complexity = O(n)
 * */

public class LongestSubStringWithAtMostKDifferentCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        if (k == 0) {
            return 0;
        } else {
            return processToFetchLongestSubString(s, k);
        }
    }

    private int processToFetchLongestSubString(String s, int k) {
        Map<Character, Integer> map = new HashMap();
        char arr[] = s.toCharArray();

        int leftIndex = 0;
        int rightIndex = 0;

        int length = Integer.MIN_VALUE;

        while (rightIndex < arr.length) {
            if (map.size() < k) {
                length = updateLength(length, leftIndex, rightIndex);
                if (!map.containsKey(arr[rightIndex])) {
                    map.put(arr[rightIndex], 1);
                } else {
                    map.put(arr[rightIndex], map.get(arr[rightIndex]) + 1);
                }
                rightIndex++;
            } else if (map.size() == k) {
                if (!map.containsKey(arr[rightIndex])) {
                    while (map.size() == k) {
                        int count = map.get(arr[leftIndex]);
                        if (count == 1) {
                            map.remove(arr[leftIndex]);
                        } else {
                            map.put(arr[leftIndex], map.get(arr[leftIndex]) - 1);
                        }
                        leftIndex++;
                    }
                    map.put(arr[rightIndex], 1);
                    length = updateLength(length, leftIndex, rightIndex);
                } else {
                    map.put(arr[rightIndex], map.get(arr[rightIndex]) + 1);
                    length = updateLength(length, leftIndex, rightIndex);
                }
                rightIndex++;
            }
        }
        return length;
    }

    private int updateLength(int length, int leftIndex, int rightIndex) {
        return rightIndex - leftIndex + 1 > length ? rightIndex - leftIndex + 1 : length;
    }
}
