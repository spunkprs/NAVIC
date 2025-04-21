package arrays.slidingwindow;

/*
*
* Given a string, str, return the length of the longest substring without repeating characters.

Constraints:

1
≤ str.length ≤
1
0
5
≤10
5

str consists of English letters, digits, and spaces.
*
* */

import java.util.HashSet;
import java.util.Set;

public class LongestSubStringWithoutRepeatingCharacters {

    public static void main(String ar[]) {
        LongestSubStringWithoutRepeatingCharacters unit = new LongestSubStringWithoutRepeatingCharacters();
        String input = "wpwkew";
        //String input = "bbb";
        System.out.print("length of longest substring without any repeating characters :: " + unit.findLongestSubstring(input));
    }

    public int findLongestSubstring(String str) {
        if (str.length() != 0) {
            if (str.length() == 1) {
                return 1;
            }
            return processToFindLengthOfLongestSubstring(str.toCharArray());
        }
        return 0;
    }

    private int processToFindLengthOfLongestSubstring(char[] arr) {
        Set<Character> map = new HashSet<>();
        int pointerOne = 0;
        int pointerTwo = 0;
        map.add(arr[pointerOne]);
        int lengthOfLongestSubString = 1;

        while (pointerTwo < arr.length - 1) {
            if (map.contains(arr[pointerTwo + 1])) {
                while(map.contains(arr[pointerTwo + 1])) {
                    map.remove(arr[pointerOne]);
                    pointerOne++;
                }
                pointerTwo++;
                map.add(arr[pointerTwo]);
                lengthOfLongestSubString = updateLengthofLongestSubstring(lengthOfLongestSubString, map);
            } else {
                map.add(arr[pointerTwo + 1]);
                pointerTwo++;
                lengthOfLongestSubString = updateLengthofLongestSubstring(lengthOfLongestSubString, map);
            }
        }

        return lengthOfLongestSubString;
    }

    private int updateLengthofLongestSubstring(int length, Set<Character> set) {
        return length > set.size() ? length : set.size();
    }
}
