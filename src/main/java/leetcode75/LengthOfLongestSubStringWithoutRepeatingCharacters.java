package leetcode75;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubStringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        char arr[] = s.toCharArray();

        if (arr.length > 0) {
            return processToFindLongestSubstringWithoutRepeatingCharacters(arr);
        }
        return 0;
    }

    private int processToFindLongestSubstringWithoutRepeatingCharacters(char arr[]) {
        int leftIndex = 0;
        int rightIndex = 0;
        int maxLength = Integer.MIN_VALUE;

        Map<Character, Integer> map = new HashMap();

        while (rightIndex < arr.length) {
            if (rightIndex == 0) {
                map.put(arr[rightIndex], 1);
                maxLength = updateMaxLength(maxLength, leftIndex, rightIndex);
            } else {
                if (!map.containsKey(arr[rightIndex])) {
                    map.put(arr[rightIndex], 1);
                    maxLength = updateMaxLength(maxLength, leftIndex, rightIndex);
                } else {
                    while (map.containsKey(arr[rightIndex])) {
                        map.remove(arr[leftIndex]);
                        leftIndex++;
                    }
                    map.put(arr[rightIndex], 1);
                    maxLength = updateMaxLength(maxLength, leftIndex, rightIndex);
                }
            }
            rightIndex++;
        }
        return maxLength;
    }

    private int updateMaxLength(int maxLength, int leftIndex, int rightIndex) {
        return rightIndex - leftIndex + 1 > maxLength ? rightIndex - leftIndex + 1 : maxLength;
    }
}
