package arrays.slidingwindow;

/*
*
* Given a string s and an integer k, find the length of the longest substring in s,
where all characters are identical, after replacing, at most, k characters with any other lowercase English character.

Constraints:
1.) 1 ≤ s.length ≤ pow(10, 3)
2.) s consists of only lowercase English characters
3.) 0 ≤ k ≤ s.length
*
* Links that I have referred to for solving this problem :-
* a.) https://www.youtube.com/watch?v=gqXU1UyA8pk
* b.) https://www.youtube.com/watch?v=_eNhaDCr6P0
* c.) Usage of Optional --> https://medium.com/javarevisited/4-reasons-why-you-should-use-java-optional-or-not-4e44d51a9645
* */

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    /*
    * Space Complexity = O(26) --> As only lower english characters are allowed in the i/p string
    * Time Complexity = O(n * 26) --> Linear time is self explanatory in itself but * 26 is there because we are checking the highest value against each alphabet which can go upto
    * 26 in the worst case
    * */

    public static void main(String ar[]) {
        LongestRepeatingCharacterReplacement unit = new LongestRepeatingCharacterReplacement();
        String inputWord = "aabccbb";
        //String inputWord = "pqqrt";
        //String inputWord = "lmno";
        int k = 2;
        //int k = 0;
        System.out.print("Length of longest substring where all characters are identical :: " + unit.longestRepeatingCharacterReplacement(inputWord, k));
    }

    public int longestRepeatingCharacterReplacement(String s, int k) {
        char arr[] = s.toCharArray();
        if (arr.length == 1) {
            return 1;
        }
        return processToFindLongestRepeatingCharacterReplacement(arr, k);
    }

    private int processToFindLongestRepeatingCharacterReplacement(char[] arr, int k) {
        int lengthOfLongestRepeatingSustring = 0;
        Map<Character, Integer> map = new HashMap<>();
        int leftIndex = 0;
        int rightIndex = 0;

        pushElementInTheMap(map, arr[rightIndex]);

        while (rightIndex < arr.length - 1) {
            pushElementInTheMap(map, arr[rightIndex + 1]);
            int distance = rightIndex + 1 - leftIndex + 1;
            int hf = highestFrequency(map);

            if (distance - hf <= k) {
                lengthOfLongestRepeatingSustring = distance > lengthOfLongestRepeatingSustring ? distance : lengthOfLongestRepeatingSustring;
                rightIndex++;
            } else {
                if (map.containsKey(arr[leftIndex])) {
                    map.put(arr[leftIndex], map.get(arr[leftIndex]) - 1);
                    leftIndex++;
                    rightIndex++;
                }
            }
        }
        return lengthOfLongestRepeatingSustring;
    }

    private int highestFrequency(Map<Character, Integer> map) {
        return map.values().stream().max(Integer::compareTo).get();
    }

    private void pushElementInTheMap(Map<Character, Integer> map, char character) {
        if (!map.containsKey(character)) {
            map.put(character, 1);
        } else {
            map.put(character, map.get(character) + 1);
        }
    }
}
