package leetcode.random;

import java.util.*;

public class GroupAnagrams {

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
