package arrays.shortestworddistance;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.

Example 1:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
Output: 3


Example 2:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1
*
* */


public class ShortestWordDistance {

    public static void main(String ar[]) {
        ShortestWordDistance unit = new ShortestWordDistance();

        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println("Shortest distance between words makes && coding is : " + unit.shortestDistance(words, "coding", "makes"));
    }

    /**
    *
    * Following are the detailing around time && space complexity
    * a.) Brute force approach would give O(pow(n, 2)) time complexity && O(1) space complexity
    * b.) Time Complexity = O(n)
    * c.) Space Complexity = O(n)
    * */

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap();
        int minDistance = Integer.MAX_VALUE;

        int beginIndex = 0;
        int endIndex = wordsDict.length - 1;

        while (beginIndex < wordsDict.length && endIndex >= 0) {
            if (wordsDict[beginIndex].equalsIgnoreCase(word1)) {
                insertWordIntoMap(map, word1, beginIndex);
            }

            if (wordsDict[endIndex].equalsIgnoreCase(word2)) {
                insertWordIntoMap(map, word2, endIndex);
            }
            beginIndex++;
            endIndex--;
        }

        List<Integer> wordOneIndexList = map.get(word1);
        List<Integer> wordTwoIndexList = map.get(word2);

        int indexOne = 0;
        int indexTwo = wordTwoIndexList.size() - 1;

        while (indexOne < wordOneIndexList.size() && indexTwo >= 0) {
            minDistance = updateMinDistance(wordOneIndexList.get(indexOne), wordTwoIndexList.get(indexTwo), minDistance);
            if (wordOneIndexList.get(indexOne) < wordTwoIndexList.get(indexTwo)) {
                indexOne++;
            } else {
                indexTwo--;
            }
        }
        return minDistance;
    }

    private int updateMinDistance(int indexWordOne, int indexWordTwo, int minDistance) {
        int distance = Math.abs(indexWordOne - indexWordTwo);
        return distance < minDistance ? distance : minDistance;
    }

    private void insertWordIntoMap(Map<String, List<Integer>> map, String word, int index) {
        if (!map.containsKey(word)) {
            List<Integer> indexList = new ArrayList();
            indexList.add(index);
            map.put(word, indexList);
        } else {
            map.get(word).add(index);
        }
    }


}
