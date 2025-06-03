package arrays.shortestworddistance;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
*
Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between the occurrence of these two words in the list.

Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.



Example 1:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1
Example 2:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
Output: 3
* */

public class ShortestWordDistanceThree {

    private Map<String, List<Integer>> map = new HashMap();

    /*
     * Following are the detailing around time && space complexity
     * a.) Brute force approach would give O(pow(n, 2)) time complexity && O(1) space complexity
     * b.) Time Complexity = O(n)
     * c.) Space Complexity = O(n)
     * */

    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < wordsDict.length; i++) {
            insertWordIntoMap(map, wordsDict[i], i);
        }

        if (!word1.equals(word2)) {
            List<Integer> wordOneIndexList = this.map.get(word1);
            List<Integer> wordTwoIndexList = this.map.get(word2);

            int indexOne = 0;
            int indexTwo = 0;

            while (indexOne < wordOneIndexList.size() && indexTwo < wordTwoIndexList.size()) {
                minDistance = updateMinDistance(wordOneIndexList.get(indexOne), wordTwoIndexList.get(indexTwo), minDistance);
                if (wordOneIndexList.get(indexOne) < wordTwoIndexList.get(indexTwo)) {
                    indexOne++;
                } else {
                    indexTwo++;
                }
            }
        } else {
            List<Integer> wordIndexList = this.map.get(word1);
            int index = 0;
            while (index < wordIndexList.size() - 1) {
                minDistance = updateMinDistance(wordIndexList.get(index), wordIndexList.get(index + 1), minDistance);
                index++;
            }
        }
        return minDistance;
    }

    private int updateMinDistance(int indexWordOne, int indexWordTwo, int minDistance) {
        int distance = Math.abs(indexWordOne - indexWordTwo);
        return distance < minDistance ? distance : minDistance;
    }

    private void insertWordIntoMap(Map<String, List<Integer>> map, String word, int index) {
        if (!this.map.containsKey(word)) {
            List<Integer> indexList = new ArrayList();
            indexList.add(index);
            map.put(word, indexList);
        } else {
            map.get(word).add(index);
        }
    }

}
