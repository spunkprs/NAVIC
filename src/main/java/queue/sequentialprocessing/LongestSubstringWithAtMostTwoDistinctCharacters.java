package queue.sequentialprocessing;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithAtMostTwoDistinctCharacters {

/*
*
Given a string s, return the length of the longest
substring that contains at most two distinct characters.

Example 1:

Input: s = "eceba"
Output: 3
Explanation: The substring is "ece" which its length is 3.

Example 2:

Input: s = "ccaabbb"
Output: 5
Explanation: The substring is "aabbb" which its length is 5.
*
* */

public static void main(String ar[]) {
    LongestSubstringWithAtMostTwoDistinctCharacters longestSubstringWithAtMostTwoDistinctCharacters = new LongestSubstringWithAtMostTwoDistinctCharacters();
    System.out.println(longestSubstringWithAtMostTwoDistinctCharacters.lengthOfLongestSubstringTwoDistinct("ababffzzeee"));
 }

 public int lengthOfLongestSubstringTwoDistinct(String s) {
        char arr[] = s.toCharArray();
        if (arr.length > 1) {
            return processToComputeLengthOfLongestSubstringWithTwoDistinctCharacters(arr);
        } else if (arr.length == 1){
            return 1;
        } else {
            return 0;
        }
}

    private int processToComputeLengthOfLongestSubstringWithTwoDistinctCharacters(char arr[]) {
        int lengthFinal = 0;
        int lengthTemp = 0;
        int indexOne = 0;
        int indexTwo = 1;

        Map<Character, Integer> map = new HashMap<>();
        Set<Integer> duplicacyCheckSet = new HashSet<>();
        populateMap(arr, indexOne, map, 0);
        duplicacyCheckSet.add(indexOne);

        while (indexOne < arr.length - 1 && indexTwo < arr.length) {
            if (!duplicacyCheckSet.contains(indexTwo)) {
                populateMap(arr, indexTwo, map, 0);
                duplicacyCheckSet.add(indexTwo);
            }

            if (map.size() == 2) {
                lengthTemp = indexTwo - indexOne + 1;
                lengthFinal = updateLength(lengthFinal, lengthTemp);
                indexTwo++;
            } else if(map.size() > 2) {
                populateMap(arr, indexOne, map, 1);
                indexOne++;
            } else {
                indexTwo++;
            }
        }

        return lengthFinal == 0 ? arr.length : lengthFinal;
    }

    private int updateLength(int lengthFinal, int lengthTemp) {
        return lengthTemp > lengthFinal ? lengthTemp : lengthFinal;
    }



    private void populateMap(char arr[], int index, Map<Character, Integer> map, int operationType) {
        if (operationType == 0) {
            if (map.containsKey(arr[index])) {
                map.put(arr[index], map.get(arr[index]) + 1);
            } else {
                map.put(arr[index], 1);
            }
        } else {
            if (map.get(arr[index]) > 1) {
                map.put(arr[index], map.get(arr[index]) - 1);
            } else {
                map.remove(arr[index]);
            }
        }
    }
}
