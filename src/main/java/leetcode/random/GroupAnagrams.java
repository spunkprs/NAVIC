package leetcode.random;

import java.util.*;

/**
 Given an array of strings strs, group the anagrams together. You can return the answer in any order.

 Example 1:

 Input: strs = ["eat","tea","tan","ate","nat","bat"]

 Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

 Explanation:

 There is no string in strs that can be rearranged to form "bat".
 The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.


 Constraints:

 1.) 1 <= strs.length <= pow(10,4)
 2.) 0 <= strs[i].length <= 100
 3.) strs[i] consists of lowercase English letters.

 Source : Leetcode


 Time Complexity -->
 Considering each word has an average length of x and total words are y then O(xlogx * y) would be time complexity
 Space Complexity = O(y)
 * */

public class GroupAnagrams {

    public static void main(String ar[]) {
        GroupAnagrams unit = new GroupAnagrams();
        String words[] = {"eat","tea","tan","ate","nat","bat"};
        System.out.println("Grouped anagrams are : " + unit.groupAnagrams(words));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for (int i = 0; i < strs.length; i++) {
            String sortedWord = returnSortedVersion(strs[i]);
            if (!map.containsKey(sortedWord)) {
                List<Integer> indexes = new ArrayList<Integer>();
                indexes.add(i);
                map.put(sortedWord, indexes);
            } else {
                List<Integer> indexes = map.get(sortedWord);
                indexes.add(i);
            }
        }
        return prepareResult(strs, map);
    }

    private List<List<String>> prepareResult(String[] strs, Map<String, List<Integer>> map) {
        List<List<String>> result = new ArrayList<List<String>>();
        for (String word : map.keySet()) {
            List<String> anagrams = new ArrayList<String>();
            for (Integer index : map.get(word)) {
                anagrams.add(strs[index]);
            }
            result.add(anagrams);
        }
        return result;
    }

    private String returnSortedVersion(String word) {
        char arr[] = word.toCharArray();
        Arrays.sort(arr);
        String sortedWord = new String(arr);
        return sortedWord;
    }
}
