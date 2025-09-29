import java.util.HashSet;
import java.util.Set;


/**

Given a string s, find the length of the longest substring without duplicate characters.


 Example 1:

 Input: s = "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: s = "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: s = "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:--
1.) 0 <= s.length <= 5 * pow(10,4)
2.) s consists of English letters, digits, symbols and spaces.


 * */


public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstringOne(String s) {
        if (s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        } else {
            return processToFindSubstringWithoutRepeatingCharacters(s);
        }
    }

    private int processToFindSubstringWithoutRepeatingCharacters(String s) {
        int maxLength = 0;
        char arr[] = s.toCharArray();
        Set<Character> hashSet = new HashSet<>();

        int leftIndex = 0;
        int rightIndex = 1;

        hashSet.add(arr[0]);

        while (rightIndex < arr.length) {
            if (!hashSet.contains(arr[rightIndex])) {
                hashSet.add(arr[rightIndex]);
                maxLength = hashSet.size() > maxLength ? hashSet.size() : maxLength;
            } else {
                while (leftIndex != rightIndex && (rightIndex - leftIndex + 1) != hashSet.size()) {
                    hashSet.remove(arr[leftIndex]);
                    leftIndex++;
                    hashSet.add(arr[rightIndex]);
                }
                maxLength = hashSet.size() > maxLength ? hashSet.size() : maxLength;
            }
            rightIndex++;
        }
        return maxLength;
    }
}
