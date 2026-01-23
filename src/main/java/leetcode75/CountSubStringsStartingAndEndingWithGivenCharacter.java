package leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
Problem : 3084
You are given a string s and a character c. Return the total number of substrings of s that start and end with c.

Constraints:-

a.) 1 <= s.length <= 105
b.) s and c consist only of lowercase English letters

Time Complexity = O(N), where N being number of elements in the array
Space Complexity = O(N)
 * */

public class CountSubStringsStartingAndEndingWithGivenCharacter {

    public static void main(String ar[]) {
        CountSubStringsStartingAndEndingWithGivenCharacter unit = new CountSubStringsStartingAndEndingWithGivenCharacter();
    }

    public long countSubstrings(String s, char c) {
        char arr[] = s.toCharArray();
        List<Integer> positions = new ArrayList();
        long count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                positions.add(i);
            }
        }

        int startIndex = 0;
        int lastIndex = positions.size() - 1;

        count+= positions.size();
        while (startIndex < lastIndex) {
            long diff = lastIndex - (startIndex + 1) + 1;
            count += diff;
            startIndex++;
        }
        return count;
    }
}
